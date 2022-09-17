package br.com.vinicius.santos.nifflerconsumer.controller;

import br.com.vinicius.santos.nifflerconsumer.constant.service.UserService;
import br.com.vinicius.santos.nifflerlib.constants.RabbitMqConstants;
import org.springframework.amqp.core.QueueInformation;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledFetchUserPoints {

    @Autowired
    UserService userService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 900000)// 15 minutes
    public void fetchUserPoints() {

        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        QueueInformation queueInformation = rabbitAdmin.getQueueInfo(RabbitMqConstants.ADD_USER_POINTS);

        if (queueInformation != null) {

            if (queueInformation.getMessageCount() == 0) {

                this.userService.sendToQueueToAddUserPoints();
            }
        }


    }

}
