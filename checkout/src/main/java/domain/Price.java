package domain;

import java.util.Collection;

/**
 * Ein Preis in Euro und Cent
 */
public record Price(int euro, int cent) {

    public Price {

    }

    /**
     * Sums all prices.
     *
     * @param prices the prices
     * @return the sum
     */
    public static Price sum(Collection<Price> prices) {
        return null;
    }

    /**
     * Adds a given price to this, returning a new Price.
     *
     * @param price price to add
     * @return new Price containing sum of this and price
     */
    public Price add(Price price) {
        return null;
    }

    /**
     * Multiplies this by n, returning a new Price.
     *
     * @param n the factor
     * @return new Price containing the product of this and n
     */
    public Price multiply(int n) {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}
