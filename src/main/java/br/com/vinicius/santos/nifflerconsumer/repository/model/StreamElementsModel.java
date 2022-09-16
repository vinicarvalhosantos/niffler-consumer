package br.com.vinicius.santos.nifflerconsumer.repository.model;

public class StreamElementsModel {

    private String channel;

    private String username;

    private int amount;

    private int newAmount;

    private String message;

    public String getChannel() {
        return channel;
    }

    public String getUsername() {
        return username;
    }

    public int getAmount() {
        return amount;
    }

    public int getNewAmount() {
        return newAmount;
    }

    public String getMessage() {
        return message;
    }
}
