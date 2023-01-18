package ua.com.youtube;

import ua.com.youtube.utils.Dictionary;

import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final int size = 10_000;
    public static void main(String[] args) {
        Dictionary<String, String> dictionary = new Dictionary<>();
        Map<String, String> map = new HashMap<>();

        long beginAdding = System.currentTimeMillis();
        for (int i = 0; i < size; i++) dictionary.put("key " + i, "test value");
        long endAdding = System.currentTimeMillis();

        System.out.println("Adding " + size + " elements time: " + (endAdding - beginAdding));
        System.out.println("dictionary size: " + dictionary.size());

        long beginRemoving = System.currentTimeMillis();
        for (int i = 0; i < size; i++) dictionary.remove("key " + i);
        long endRemoving = System.currentTimeMillis();

        System.out.println("Removing " + size + " elements time: " + (endRemoving - beginRemoving));
        System.out.println("dictionary size: " + dictionary.size());

    }
}