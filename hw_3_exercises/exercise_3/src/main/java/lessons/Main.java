package lessons;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int sum;
        int number;
        System.out.println("Write the number of lessons (0-10)");
        while (!(line = reader.readLine()).equals("exit")) {
            try {
                number = Integer.parseInt(line);
                if (number >= 0 && number <= 10) {
                    sum = number * 45 + ((number / 2) * 5) + (((number - 1) / 2) * 15);
                    System.out.println((sum / 60 + 9) + ":" + (sum % 60));
                    System.out.println("Write \"exit\" to exit the program");
                } else {
                    System.out.println("Write a number greater than 0 and less than 10");
                }
            } catch (Exception e) {
                System.out.println(line + " is not a number");
            }
        }
    }
}