package br.com.vinicius.santos.nifflerconsumer.util;


import br.com.vinicius.santos.nifflerlib.model.EmoteModel;

import java.util.ArrayList;
import java.util.List;

public class EmoteUtils {

    public static EmoteModel extractWrittenEmotes(List<String> emotes, String message) {

        List<String> writtenEmotes = new ArrayList<>();
        int emotesInMessage = 0;
        for (String emote : emotes) {

            String[] allEmoteHash = emote.split(":");

            String[] emoteAllPositionsMessage = allEmoteHash[1].split(",");

            emotesInMessage += emoteAllPositionsMessage.length;

            String[] emotePositionMessage = emoteAllPositionsMessage[0].split("-");

            int emoteFirstPosition = Integer.parseInt(emotePositionMessage[0]);

            int emoteSecondPosition = Integer.parseInt(emotePositionMessage[1]) + 1;

            String emoteText = message.substring(emoteFirstPosition, emoteSecondPosition);
            writtenEmotes.add(emoteText);

        }

        return new EmoteModel(writtenEmotes, emotesInMessage);

    }

}
