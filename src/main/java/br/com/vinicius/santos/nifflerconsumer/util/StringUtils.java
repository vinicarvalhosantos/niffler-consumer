package br.com.vinicius.santos.nifflerconsumer.util;


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

}
