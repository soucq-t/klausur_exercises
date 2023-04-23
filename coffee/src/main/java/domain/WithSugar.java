package domain;

import java.util.List;

public class WithSugar extends CoffeeAdditional {

    public WithSugar(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 2;
    }

    @Override
    public List<String> getAll() {
        var erg = super.getAll();
        erg.add("sugar");
        return erg;
    }
}
