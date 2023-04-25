package domain;

import java.util.Collection;

/**
 * Ein Preis in Euro und Cent
 */
public record Price(int euro, int cent) {


    public Price {
        if (euro<0 || cent<0){
            throw new IllegalArgumentException();
        }
        if ( cent>= 100){
            while (cent >=100){
                euro++;
                cent=cent-100;
            }
        }
    }

    /**
     * Sums all prices.
     *
     * @param prices the prices
     * @return the sum
     */
    public static Price sum(Collection<Price> prices) {
        return prices.stream()
                .reduce((price, price2) -> {
                    return new Price(price2.euro+price.euro,price2.cent+price.cent);
                }).orElse(new Price(0,0));
    }

    /**
     * Adds a given price to this, returning a new Price.
     *
     * @param price price to add
     * @return new Price containing sum of this and price
     */
    public Price add(Price price) {
        return new Price(this.euro+price.euro,this.cent+price.cent);
    }

    /**
     * Multiplies this by n, returning a new Price.
     *
     * @param n the factor
     * @return new Price containing the product of this and n
     */
    public Price multiply(int n) {
        var euro= this.euro*n;
        var cent = this.cent*n;
        while (cent >100){
            euro++;
            cent=cent-100;
        }
        return new Price(euro,cent);
    }

    @Override
    public String toString() {
        return this.euro+"."+(this.cent<10?"0"+this.cent:this.cent);
    }
}
