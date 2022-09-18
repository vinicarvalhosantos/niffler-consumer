package br.com.vinicius.santos.nifflerconsumer.service.impl;

import br.com.vinicius.santos.nifflerconsumer.constant.PointsConstants;
import br.com.vinicius.santos.nifflerconsumer.service.LastUserMessageService;
import br.com.vinicius.santos.nifflerconsumer.service.UserService;
import br.com.vinicius.santos.nifflerconsumer.model.EmoteModel;
import br.com.vinicius.santos.nifflerconsumer.model.LastUserMessageModel;
import br.com.vinicius.santos.nifflerconsumer.model.UserModel;
import br.com.vinicius.santos.nifflerconsumer.model.entity.UserEntity;
import br.com.vinicius.santos.nifflerconsumer.model.entity.UserMessageEntity;
import br.com.vinicius.santos.nifflerconsumer.repository.UserMessageRepository;
import br.com.vinicius.santos.nifflerconsumer.service.UserMessageService;
import br.com.vinicius.santos.nifflerconsumer.util.EmoteUtils;
import br.com.vinicius.santos.nifflerlib.models.dto.UserMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Autowired
    private UserMessageRepository userMessageRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LastUserMessageService lastUserMessageService;

    @Override
    public void analyseMessage(UserMessageDto userMessageDto) {

        Long userId = userMessageDto.getUserId();
        String username = userMessageDto.getUsername();
        String displayName = userMessageDto.getDisplayName();
        String message = userMessageDto.getMessage();
        int messageLength = this.getMessageLength(userMessageDto);
        double pointsToAdd = 0.0;

        UserEntity userEntity = this.userService.fetchUser(new UserModel(userId, username, displayName));

        LastUserMessageModel lastUserMessageModel = this.lastUserMessageService.compareUserMessages(userEntity, message);

        double similarity = lastUserMessageModel.getSimilarityPercentage();

        if (isSpam(message) || similarity >= 0.7) {
            this.saveUserMessage(messageLength, pointsToAdd, userEntity, true);
            lastUserMessageModel.getLastUserMessageEntity().setLastMessage(message);
            this.lastUserMessageService.fetchLastUserMessage(lastUserMessageModel.getLastUserMessageEntity());
            return;
        }

        pointsToAdd = this.extractPointsToAdd(userMessageDto, messageLength);

        userEntity.setPointsToAdd(userEntity.getPointsToAdd() + pointsToAdd);

        this.saveUserMessage(messageLength, pointsToAdd, userEntity, false);

        lastUserMessageModel.getLastUserMessageEntity().setLastMessage(message);

        this.lastUserMessageService.fetchLastUserMessage(lastUserMessageModel.getLastUserMessageEntity());

        this.userService.updateUser(userEntity);
    }

    private int getMessageLength(UserMessageDto userMessageDto) {
        int emotesInMessage = 0;

        if (!userMessageDto.getEmotes().isEmpty()) {

            EmoteModel emoteModel = EmoteUtils.extractWrittenEmotes(userMessageDto.getEmotes(), userMessageDto.getMessage());

            if (userMessageDto.isEmoteOnly()) {
                return emoteModel.getEmotesNumber();
            }

            emoteModel.getWrittenEmotes().forEach(writtenEmote -> userMessageDto.setMessage(
                    userMessageDto.getMessage().replaceAll(writtenEmote, "").replaceAll("\\s+", "")));

            emotesInMessage = emoteModel.getEmotesNumber();
        }

        return userMessageDto.getMessage().length() + emotesInMessage;
    }

    private double extractPointsToAdd(UserMessageDto userMessageDto, int messageLength) {


        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatSymbols.setGroupingSeparator('.');

        DecimalFormat decimalFormat = new DecimalFormat("##.00", decimalFormatSymbols);

        int percentage = this.getPercentage(userMessageDto.isSubscriber(), userMessageDto.getSubscriptionTime(), userMessageDto.getSubscriptionTier());

        double pointsToAdd = (Double.parseDouble(String.valueOf(messageLength)) / percentage);


        return Double.parseDouble(decimalFormat.format(pointsToAdd));
    }

    private int getPercentage(boolean isSubscriber, int subscriptionTime, int subscriptionTier) {

        int percentageToExtract = subscriptionTime / 10;
        int percentage;
        if (isSubscriber) {
            switch (subscriptionTier) {
                case 1:
                    percentage = PointsConstants.PERCENTAGE_FOR_SUBS_T1 - percentageToExtract;
                    break;
                case 2:
                    percentage = PointsConstants.PERCENTAGE_FOR_SUBS_T2 - percentageToExtract;
                    break;
                case 3:
                    percentage = PointsConstants.PERCENTAGE_FOR_SUBS_T3 - percentageToExtract;
                    break;
                default:
                    percentage = PointsConstants.PERCENTAGE_FOR_NON_SUBS - percentageToExtract;
                    break;
            }
            return percentage;
        } else {
            return PointsConstants.PERCENTAGE_FOR_NON_SUBS - percentageToExtract;
        }
    }

    private void saveUserMessage(int messageLength, Double pointsToAdd, UserEntity userEntity, boolean isSpam) {

        UserMessageEntity userMessageEntity = new UserMessageEntity(messageLength, pointsToAdd, userEntity, isSpam);

        this.userMessageRepository.save(userMessageEntity);

    }

    private boolean isSpam(String message) {
        String pattern = "((.)\\2+)+";

        return message.matches(pattern);
    }
}
