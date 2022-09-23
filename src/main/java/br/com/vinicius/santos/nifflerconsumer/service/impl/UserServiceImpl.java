package br.com.vinicius.santos.nifflerconsumer.service.impl;

import br.com.vinicius.santos.nifflerconsumer.service.StreamElementsService;
import br.com.vinicius.santos.nifflerconsumer.service.UserService;
import br.com.vinicius.santos.nifflerlib.model.StreamElementsModel;
import br.com.vinicius.santos.nifflerlib.model.UserModel;
import br.com.vinicius.santos.nifflerlib.model.entity.UserEntity;
import br.com.vinicius.santos.nifflerlib.repository.UserRepository;
import br.com.vinicius.santos.nifflerlib.constants.RabbitMqConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StreamElementsService streamElementsService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public UserEntity fetchUser(UserModel userModel) {

        Optional<UserEntity> userEntity = userRepository.findById(userModel.getId());

        Long userId = userModel.getId();
        String username = userModel.getUsername();
        String displayName = userModel.getDisplayName();
        Double pointsToAdd = userModel.getPointsToAdd();
        Double pointsAdded = userModel.getPointsAdded();

        return userEntity.orElseGet(() -> userRepository.save(new UserEntity(userId, username, displayName, pointsToAdd, pointsAdded)));

    }

    @Override
    public void updateUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public void sendToQueueToAddUserPoints() {
        List<UserEntity> eligibleUsers = this.userRepository.findAllEligibleUsers();
        String queueName = RabbitMqConstants.ADD_USER_POINTS;

        for (UserEntity userEntity : eligibleUsers) {
            this.rabbitTemplate.convertAndSend(queueName, userEntity);
        }
    }

    @Override
    public void addUserPoints(UserEntity userEntity) throws IOException {

        int pointsToAdd = getPointsToAdd(userEntity);

        StreamElementsModel streamElementsModel = streamElementsService.addPointsToUser(userEntity.getUsername(), pointsToAdd);

        double pointsAdded = formatDouble(userEntity.getPointsAdded() + streamElementsModel.getAmount());
        double pointsToAddUpdated = formatDouble(userEntity.getPointsToAdd() - pointsToAdd);
        userEntity.setPointsToAdd(pointsToAddUpdated);
        userEntity.setPointsAdded(pointsAdded);

        this.userRepository.save(userEntity);
    }

    private int getPointsToAdd(UserEntity userEntity) {

        return (int) Math.floor(userEntity.getPointsToAdd());
    }

    private Double formatDouble(Double number) {

        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatSymbols.setGroupingSeparator('.');

        DecimalFormat decimalFormat = new DecimalFormat("##.00", decimalFormatSymbols);

        return Double.parseDouble(decimalFormat.format(number));
    }
}
