package br.com.vinicius.santos.nifflerconsumer.service.impl;

import br.com.vinicius.santos.nifflerlib.model.LastUserMessageModel;
import br.com.vinicius.santos.nifflerlib.model.entity.LastUserMessageEntity;
import br.com.vinicius.santos.nifflerlib.model.entity.UserEntity;
import br.com.vinicius.santos.nifflerlib.repository.LastUserMessageRepository;
import br.com.vinicius.santos.nifflerconsumer.service.LastUserMessageService;
import br.com.vinicius.santos.nifflerconsumer.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LastUserMessageServiceImpl implements LastUserMessageService {

    @Autowired
    LastUserMessageRepository lastUserMessageRepository;

    @Override
    public LastUserMessageModel compareUserMessages(UserEntity userEntity, String actualMessage) {
        LastUserMessageEntity lastUserMessageEntity = this.lastUserMessageRepository.findLastUserMessageEntityByUser(userEntity);

        if (lastUserMessageEntity == null) {
            lastUserMessageEntity = this.fetchLastUserMessage(new LastUserMessageEntity(userEntity, ""));
        }

        String lastMessage = lastUserMessageEntity.getLastMessage();

        double similarity = StringUtils.similarity(actualMessage, lastMessage);

        return new LastUserMessageModel(actualMessage, lastMessage, similarity, lastUserMessageEntity);
    }

    @Override
    public LastUserMessageEntity fetchLastUserMessage(LastUserMessageEntity lastUserMessageEntity) {
        lastUserMessageEntity.setLastMessage(StringUtils.removeAllEmojis(lastUserMessageEntity.getLastMessage()));
        return this.lastUserMessageRepository.save(lastUserMessageEntity);
    }
}
