package service;

public class Other implements Subscriber{


    @Override
    public String call(String text) {
        return text;
    }
}
