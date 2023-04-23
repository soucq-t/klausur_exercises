package app;

import domain.*;

public class Main {
    public static void main(String[] args) {
        Coffee coffee = new CoffeeSimple();
        //coffee = new CoffeeAdditional(coffee);
        coffee= new WithMilk(coffee);
        System.out.println(        coffee.getAll());
        coffee=new WithSugar(coffee);
        System.out.println(        coffee.getAll());
    }
}
