package br.com.vinicius.santos.nifflerconsumer.service;

import br.com.vinicius.santos.nifflerlib.model.LastUserMessageModel;
import br.com.vinicius.santos.nifflerlib.model.entity.LastUserMessageEntity;
import br.com.vinicius.santos.nifflerlib.model.entity.UserEntity;

public interface LastUserMessageService {

    LastUserMessageModel compareUserMessages(UserEntity userEntity, String message);

    LastUserMessageEntity fetchLastUserMessage(LastUserMessageEntity lastUserMessageEntity);

}
