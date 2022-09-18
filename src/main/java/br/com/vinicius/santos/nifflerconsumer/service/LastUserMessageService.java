package br.com.vinicius.santos.nifflerconsumer.service;

import br.com.vinicius.santos.nifflerconsumer.model.LastUserMessageModel;
import br.com.vinicius.santos.nifflerconsumer.model.entity.LastUserMessageEntity;
import br.com.vinicius.santos.nifflerconsumer.model.entity.UserEntity;

public interface LastUserMessageService {

    LastUserMessageModel compareUserMessages(UserEntity userEntity, String message);

    LastUserMessageEntity fetchLastUserMessage(LastUserMessageEntity lastUserMessageEntity);

}
