package handshake_theory;

public class Main {
    public static void main(String[] args) {
        String message = "Hello, pass this message on";
        Person current = new Person(message, 0);
        current.greetings();
        Person friend;
        while (current.getCircle() != 6) {
            do {
                friend = new Person(current.getMessage(), current.getCircle() + 1);
                friend.greetings();
            } while (friend.isIdiot());
            current = friend;
        }
        System.out.println("The person №" + current.getCircle() + " receive his message, the theory works!");
    }
}