package br.com.vinicius.santos.nifflerconsumer.util;


import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static double similarity(String actualMessage, String lastMessage) {
        String longerMessage = actualMessage;
        String shorterMessage = lastMessage;

        if (actualMessage.length() < lastMessage.length()) {
            longerMessage = lastMessage;
            shorterMessage = actualMessage;
        }

        int longerLength = longerMessage.length();

        if (longerLength == 0) {
            return 1.0;
        }

        final int comparedMessages = org.apache.commons.lang3.StringUtils.getLevenshteinDistance(longerMessage, shorterMessage);

        return (longerLength - comparedMessages) / (double) longerLength;
    }

    public static String removeAllEmojisExcept(String message){
        Collection<Emoji> collection = new ArrayList<>();
        collection.add(EmojiManager.getForAlias("heart"));
        collection.add(EmojiManager.getForAlias("smile"));
        collection.add(EmojiManager.getForAlias("smiley"));

        return EmojiParser.removeAllEmojisExcept(message, collection);
    }

    public static String removeAllEmojis(String message){
        String regex = "[^\\p{L}\\p{N}\\p{P}\\p{Z}]";
        Pattern pattern = Pattern.compile(
                regex,
                Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(message);
        return matcher.replaceAll("");
    }

}
