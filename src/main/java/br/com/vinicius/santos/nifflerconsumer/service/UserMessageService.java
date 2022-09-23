package br.com.vinicius.santos.nifflerconsumer.service;

import br.com.vinicius.santos.nifflerlib.model.dto.UserMessageDto;

public interface UserMessageService {

    void analyseMessage(UserMessageDto userMessageDto);

}
