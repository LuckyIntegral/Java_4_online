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
        } catch (Exception e) {
            System.out.println("Something went wrong...");

        }
    }

    private String makeStatistic(String text) {
        List<Entry> words = countWords(text);
        int amount = words.stream().map(entry -> entry.count).reduce(Integer::sum).get();
        List<Integer> top = words.stream()
                .map(e -> e.count)
                .distinct()
                .sorted((a, b) -> b - a)
                .toList();
        words.stream().peek(entry -> entry.setRank(top.indexOf(entry.getCount()) + 1))
                .forEach(entry -> entry.setPercent(entry.count * 100 / amount));

        return Table.makeEntryTable(words);
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
        private final String value;
        private final int count;
        private int rank;
        private int percent;

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
            this.value = value;
            this.count = count;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
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