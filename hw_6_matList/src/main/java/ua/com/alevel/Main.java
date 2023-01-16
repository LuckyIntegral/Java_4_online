package ua.com.alevel;

import ua.com.alevel.utils.MathList;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MathList<Integer> mathList = new MathList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            mathList.add((i) * random.nextInt(10));
        }
        System.out.println(mathList);
    }
}