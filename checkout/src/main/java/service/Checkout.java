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

        Price finalPrice=new Price(0,0);
        for (Item item : items) {
            if(!products.contains(item.product())){
                continue;
            }
            int quantity = item.quantity();
            Product product=item.product();
                if (!product.offers().isEmpty()) {
                    List<Offer> sortedOffers = product.offers()
                            .stream()
                            .sorted(Comparator.comparingDouble(o -> (o.price().euro() + o.price().cent() * 0.01)/ o.quantity()))
                            .collect(Collectors.toList());
                     for (Offer o:sortedOffers) {
                         while (quantity!=0 && quantity >= o.quantity()){
                             finalPrice= new Price(finalPrice.euro()+o.price().euro(), finalPrice.cent()+o.price().cent());
                             quantity-=o.quantity();
                         }
                        }
            }


        }
        return finalPrice;
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
