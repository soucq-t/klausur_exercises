package domain;

import java.util.List;

public class WithMilk extends CoffeeAdditional {

    public WithMilk(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 1;
    }

    @Override
    public List<String> getAll() {
        var erg = super.getAll();
        erg.add("Milk");
        return erg;
    }
}
