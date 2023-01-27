package ua.com.gitHub.service;

import ua.com.gitHub.utils.Table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

public class TextService {
    public void start() {
        String text;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                System.out.println("\nPlease enter text to display statistic, or type \"exit\" to exit the program");
                text = reader.readLine();
                if (text == null || text.equalsIgnoreCase("exit") || text.isBlank()) {
                    System.exit(0);
                }
                System.out.println(makeStatistic(text));
            } while (!text.isEmpty());
        } catch (IOException e) {
            System.out.println("Try again next time...");
        }
    }

    private String makeStatistic(String text) {
        List<Entry> words = countWords(text);
        int amount = words.stream().map(entry -> entry.count).reduce(Integer::sum).get();
        fillEntriesWithSentencesStatistic(words, text);
        List<Integer> top = words.stream()
                .map(e -> e.count)
                .distinct()
                .sorted((a, b) -> b - a)
                .toList();
        words.stream().peek(entry -> entry.setRank(top.indexOf(entry.getCount()) + 1))
                .forEach(entry -> entry.setPercent(entry.count * 100 / amount));

        return Table.makeEntryTable(words);
    }

    private void fillEntriesWithSentencesStatistic(List<Entry> list, String text) {
        for (String string : text.split("\\.")) {
            for (Entry entry : list) {
                if (string.contains(entry.value)) {
                    List<String> elements = Pattern.compile("\\w+")
                            .matcher(text)
                            .results()
                            .map(e -> e.group().toLowerCase())
                            .toList();
                    int total = 0;
                    int current = 0;
                    for (String element : elements) {
                        total++;
                        if (element.equals(entry.value)) {
                            current++;
                        }
                    }
                    if (total == 0 || current == 0) {
                        entry.add(0);
                    } else {
                        entry.add(current * 100 / total);
                    }
                } else {
                    entry.add(0);
                }
            }
        }
    }

    private List<Entry> countWords(String text) {
        List<Entry> entries;
        HashMap<String, Integer> words = new HashMap<>();
        Pattern.compile("\\w+")
                .matcher(text)
                .results()
                .map(e -> e.group().toLowerCase())
                .forEach(el -> {
                    if (words.containsKey(el)) {
                        words.put(el, words.get(el) + 1);
                    } else {
                        words.put(el, 1);
                    }
                });
        entries = words.entrySet()
                .stream()
                .map(a -> new Entry(a.getKey(), a.getValue()))
                .toList();
        return entries;
    }

    public static class Entry {
        public static String longestName = "";
        public static String biggestCount = "";
        public static String biggestRank = "";

        private final String value;
        private final int count;
        private int rank;
        private int percent;
        private final LinkedList<Integer> sentencesMeeting = new LinkedList<>();
        private String percents;

        public String getPercents() {
            return percents;
        }

        public void setPercents(String percents) {
            this.percents = percents;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "value='" + value + '\'' +
                    ", count='" + count + '\'' +
                    ", rank='" + rank + '\'' +
                    ", percent='" + percent + '\'' +
                    '}';
        }

        public Entry(String value, int count) {
            if (longestName.length() < value.length()) {
                longestName = value;
            }
            this.value = value;

            if (biggestCount.length() < String.valueOf(count).length()) {
                biggestCount = String.valueOf(count);
            }
            this.count = count;
        }

        public void add(int percent) {
            sentencesMeeting.add(percent);
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            if (biggestRank.length() < String.valueOf(rank).length()) {
                biggestRank = String.valueOf(rank);
            }
            this.rank = rank;
        }

        public LinkedList<Integer> getSentencesMeeting() {
            return sentencesMeeting;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }

        public String getValue() {
            return value;
        }

        public Integer getCount() {
            return count;
        }
    }
}