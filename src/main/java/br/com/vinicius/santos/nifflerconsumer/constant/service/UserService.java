package br.com.vinicius.santos.nifflerconsumer.constant.service;

import br.com.vinicius.santos.nifflerconsumer.model.UserModel;
import br.com.vinicius.santos.nifflerconsumer.model.entity.UserEntity;

import java.io.IOException;

public interface UserService {

    UserEntity fetchUser(UserModel userModel);

    void updateUser(UserEntity userEntity);

    void sendToQueueToAddUserPoints();

    void addUserPoints(UserEntity userEntity) throws IOException;

}
