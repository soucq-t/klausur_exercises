package domain;

import java.util.List;

public class CoffeeSimple implements Coffee{
    @Override
    public List<String> getAll() {
        return List.of("null");
    }

    @Override
    public int getPrice() {
        return 2;
    }
}
