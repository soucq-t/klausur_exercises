package service;

public class OutputChannel implements Output {

    @Override
    public void send(String text) {
        System.out.println(text);
    }
}
