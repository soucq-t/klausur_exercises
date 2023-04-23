package service;

import domain.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Eine Supermarktkassa
 * Wird mit einem Set von verkauften Produkten initialisiert.
 */
public record Checkout(Set<Product> products) {

    /**
     * Computes the price for the whole shopping cart.
     *
     * @param items the items in the shopping cart
     * @return the total price
     */
    public Price total(Set<Item> items) {
        return null;
    }

    /**
     * Removes all items not contained in sellable products.
     *
     * @param items the items in the shopping cart
     * @return the sellable items
     */
    public Set<Item> filter(Set<Item> items) {
        return null;
    }
}
