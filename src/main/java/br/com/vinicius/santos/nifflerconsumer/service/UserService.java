package br.com.vinicius.santos.nifflerconsumer.service;

import br.com.vinicius.santos.nifflerlib.model.UserModel;
import br.com.vinicius.santos.nifflerlib.model.entity.UserEntity;

import java.io.IOException;

public interface UserService {

    UserEntity fetchUser(UserModel userModel);

    void updateUser(UserEntity userEntity);

    void sendToQueueToAddUserPoints();

    void addUserPoints(UserEntity userEntity) throws IOException;

}
