package br.com.vinicius.santos.nifflerconsumer.service;


import br.com.vinicius.santos.nifflerconsumer.model.StreamElementsModel;

import java.io.IOException;

public interface StreamElementsService {

    StreamElementsModel addPointsToUser(String username, int points) throws IOException;
}