package br.com.vinicius.santos.nifflerconsumer.model;

import br.com.vinicius.santos.nifflerconsumer.model.entity.LastUserMessageEntity;

public class LastUserMessageModel {

    private String actualMessage;

    private String lastMessage;

    private double similarityPercentage;

    private LastUserMessageEntity lastUserMessageEntity;

    public LastUserMessageModel(String actualMessage, String lastMessage, double similarityPercentage, LastUserMessageEntity lastUserMessageEntity) {
        this.actualMessage = actualMessage;
        this.lastMessage = lastMessage;
        this.similarityPercentage = similarityPercentage;
        this.lastUserMessageEntity = lastUserMessageEntity;
    }

    public LastUserMessageModel() {
    }

    public String getActualMessage() {
        return actualMessage;
    }

    public void setActualMessage(String actualMessage) {
        this.actualMessage = actualMessage;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public double getSimilarityPercentage() {
        return similarityPercentage;
    }

    public void setSimilarityPercentage(double similarityPercentage) {
        this.similarityPercentage = similarityPercentage;
    }

    public LastUserMessageEntity getLastUserMessageEntity() {
        return lastUserMessageEntity;
    }

    public void setLastUserMessageEntity(LastUserMessageEntity lastUserMessageEntity) {
        this.lastUserMessageEntity = lastUserMessageEntity;
    }
}
