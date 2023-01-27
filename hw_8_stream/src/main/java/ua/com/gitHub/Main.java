package ua.com.gitHub;

import ua.com.gitHub.service.TextService;

public class Main {
    //Без лібок і циклів, все стріми і хардкод :)
    //P.S. удачі з читанням
    public static void main(String[] args) {
        TextService service = new TextService();
        service.start();
    }
}