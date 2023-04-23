package service;

public record OutputFilter(OutputChannel outChannel) implements Output {

    @Override
    public void send(String text) {
        if (text != null && !text.startsWith("%ignore"))
            outChannel.send(text);
    }
}

