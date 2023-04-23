package service;

import java.util.ArrayList;
import java.util.List;

public class Publischer {
    private final List<Subscriber> stuff = new ArrayList<>();

    public void addOther(Other other) {
        stuff.add(other);
    }

    public void removeOther(Other other) {
        stuff.remove(other);
    }

    private void notifyOther() {
        for (var other : stuff) {
            other.call("context");
        }
    }
}
