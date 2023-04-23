package domain;

import java.util.List;

public class CoffeeAdditional implements Coffee{

    private Coffee coffee;

    public CoffeeAdditional(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public List<String> getAll() {
        return coffee.getAll();
    }

    @Override
    public int getPrice() {
        return coffee.getPrice();
    }
}

