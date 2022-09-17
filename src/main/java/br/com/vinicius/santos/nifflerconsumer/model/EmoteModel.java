package br.com.vinicius.santos.nifflerconsumer.model;

import java.util.List;

public class EmoteModel {

    private final List<String> writtenEmotes;

    private final int emotesNumber;

    public EmoteModel(List<String> writtenEmotes, int emotesNumber) {
        this.writtenEmotes = writtenEmotes;
        this.emotesNumber = emotesNumber;
    }

    public List<String> getWrittenEmotes() {
        return writtenEmotes;
    }

    public int getEmotesNumber() {
        return emotesNumber;
    }
}
