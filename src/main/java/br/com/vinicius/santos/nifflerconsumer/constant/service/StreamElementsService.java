package br.com.vinicius.santos.nifflerconsumer.constant.service;


import br.com.vinicius.santos.nifflerconsumer.model.StreamElementsModel;

import java.io.IOException;

public interface StreamElementsService {

    StreamElementsModel addPointsToUser(String username, int points) throws IOException;
}
