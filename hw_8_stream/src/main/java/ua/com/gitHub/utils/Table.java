package ua.com.gitHub.utils;

import ua.com.gitHub.service.TextService.*;

import java.util.LinkedList;
import java.util.List;

public class Table {
    // З наступного разу буду використовувати лібки для табличок, якщо не зможу цю ніяк спростити :)))
    //P.S. спочатку що я робив розуміли двоє: Я і Бог, залишився тільки Бог...
    public static String makeEntryTable(List<Entry> list) {
        for (Entry entry : list) {
            entry.setPercents(getPercents(entry.getSentencesMeeting()));
        }
        String word = "word", rating = "rating", count = "count", percentage = "percentage", percentPerSentence = "% in sentences";
        int wordSize = Math.max(Entry.longestName.length(), word.length());
        int ratingSize = Math.max(Entry.biggestRank.length(), rating.length());
        int counterSize = Math.max(Entry.biggestCount.length(), count.length());
        int perSize = percentage.length();
        int sizeOfPercents = Math.max(list.stream()
                .map(e -> e.getPercents().length())
                .max((a, b) -> a - b).get(), percentPerSentence.length());
        String bound = '+' + "-".repeat(wordSize)
                + '+' + "-".repeat(ratingSize)
                + '+' + "-".repeat(counterSize)
                + '+' + "-".repeat(perSize)
                + '+' + "-".repeat(sizeOfPercents)
                + "+\n";
        StringBuilder builder = new StringBuilder();
        builder.append(bound).append("|").append(word)
                .append(" ".repeat(wordSize - word.length()))
                .append("|").append(rating)
                .append(" ".repeat(ratingSize - rating.length()))
                .append("|").append(count)
                .append(" ".repeat(counterSize - count.length()))
                .append("|").append(percentage)
                .append("|").append(percentPerSentence)
                .append(" ".repeat(sizeOfPercents - percentPerSentence.length()))
                .append("|\n").append(bound);
        list.stream()
                .sorted((a, b) -> a.getRank() - b.getRank())
                .map(entry -> "|" + entry.getValue()
                        + " ".repeat(wordSize - entry.getValue().length())
                        + "|  " + entry.getRank()
                        + " ".repeat(ratingSize - 2 - String.valueOf(entry.getRank()).length())
                        + "|  " + entry.getCount()
                        + " ".repeat(counterSize - 2 - String.valueOf(entry.getCount()).length())
                        + "|   " + entry.getPercent() + '%'
                        + " ".repeat(perSize - 4 - String.valueOf(entry.getPercent()).length())
                        + "|" + getPercents(entry.getSentencesMeeting())
                        + " ".repeat(sizeOfPercents - entry.getPercents().length())
                        + "|\n" + bound)
                .forEach(builder::append);
        return builder.toString();
    }

    private static String getPercents(LinkedList<Integer> integers) {
        StringBuilder builder = new StringBuilder();
        for (Integer integer : integers) {
            builder.append(integer).append("% ");
        }
        return builder.toString();
    }
}