package handshake_theory;

import java.util.Random;

public class Person {
    private final int circle;
    private final String message;
    private final boolean isIdiot;
    private final static Random random = new Random();

    public Person(String message, int circle) {
        this.message = message;
        this.circle = circle;
        if (circle == 0 || circle == 6) {
            isIdiot = false;
        } else {
            isIdiot = random.nextBoolean();
        }
    }

    public void greetings() {
        if (circle == 6) {
            System.out.println("It's the " + circle + " circle. Finally i got my message");
        } else {
            if (isIdiot) {
                System.out.println("It's the " + circle + " circle, however idk what to do");
            } else {
                System.out.println("It's the " + circle + " circle, I'll pass this message on");
            }
        }
    }

    public String getMessage() {
        return message;
    }

    public int getCircle() {
        return circle;
    }

    public boolean isIdiot() {
        return isIdiot;
    }
}