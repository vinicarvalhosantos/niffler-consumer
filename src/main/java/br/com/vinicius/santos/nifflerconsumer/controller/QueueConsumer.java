package br.com.vinicius.santos.nifflerconsumer.controller;

import br.com.vinicius.santos.nifflerlib.model.entity.UserEntity;
import br.com.vinicius.santos.nifflerconsumer.service.UserMessageService;
import br.com.vinicius.santos.nifflerconsumer.service.UserService;
import br.com.vinicius.santos.nifflerlib.constants.RabbitMqConstants;
import br.com.vinicius.santos.nifflerlib.model.dto.UserMessageDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class QueueConsumer {

    @Autowired
    UserMessageService userMessageService;

    @Autowired
    UserService userService;

    @RabbitListener(queues = {RabbitMqConstants.MESSAGES_BOT_QUEUE})
    public void receiveFromBot(@Payload UserMessageDto userMessageDto) {
        this.userMessageService.analyseMessage(userMessageDto);
    }

    /*@RabbitListener(queues = {RabbitMqConstants.ADD_USER_POINTS})
    public void receiveToAddUserPoints(@Payload UserEntity userEntity) throws IOException {
        this.userService.addUserPoints(userEntity);
    }*/

}
