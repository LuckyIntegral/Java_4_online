package ua.com.gitHub.utils;

import ua.com.gitHub.service.TextService.*;

import java.util.List;

public class Table {
    // З наступного разу буду використовувати лібки для табличок, якщо не зможу цю ніяк спростити :)))

    //P.S. спочатку що я робив розуміли двоє: Я і Бог, залишився тільки Бог...
    public static String makeEntryTable(List<Entry> list) {
        String word = "word", rating = "rating", count = "count", percentage = "percentage";
        int wordSize = Math.max(list.stream()
                .map(value -> value.getValue().length())
                .max((a, b) -> a - b).get(), word.length());
        int ratingSize = Math.max(list.stream()
                .map(entry -> String.valueOf(entry.getRank()).length())
                .max((a, b) -> a - b).get(), rating.length());
        int counterSize = Math.max(list.stream()
                .map(entry -> String.valueOf(entry.getCount()).length())
                .max((a, b) -> a - b).get(), count.length());
        int perSize = percentage.length();
        String bound = '+' + "-".repeat(wordSize)
                + '+' + "-".repeat(ratingSize)
                + '+' + "-".repeat(counterSize)
                + '+' + "-".repeat(perSize) + "+\n";
        StringBuilder builder = new StringBuilder();
        builder.append(bound).append("|").append(word)
                .append(" ".repeat(wordSize - word.length()))
                .append("|").append(rating)
                .append(" ".repeat(ratingSize - rating.length()))
                .append("|").append(count)
                .append(" ".repeat(counterSize - count.length()))
                .append("|").append(percentage)
                .append(" ".repeat(perSize - percentage.length()))
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
                        + " ".repeat(perSize - 5 - String.valueOf(entry.getPercent()).length())
                        + "|\n" + bound)
                .forEach(builder::append);
        return builder.toString();
    }
}
