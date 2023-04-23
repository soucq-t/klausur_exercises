package service;

import domain.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class CheckoutTest {

    @Nested
    class Construction {

        @Test
        void works_for_distinct_products() {
            var apple = new Product("Apple", new Price(1, 0));
            var orange = new Product("Orange", new Price(1, 0));
            var products = Set.of(apple, orange);

            assertThatCode(() -> new Checkout(products))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    class Totalling {

        @Test
        void returns_zero_for_empty_cart() {
            var checkout = new Checkout(Set.of());

            var price = checkout.total(Set.of());

            assertThat(price)
                    .isEqualTo(new Price(0, 0));
        }

        @Test
        void sums_prices_if_just_one_price_set() {
            var apple = new Product("Apple", new Price(1, 0));
            var orange = new Product("Orange", new Price(10, 0));
            var checkout = new Checkout(Set.of(apple, orange));
            var cart = Set.of(
                    new Item(apple, 2),
                    new Item(orange)
            );

            var price = checkout.total(cart);

            assertThat(price)
                    .isEqualTo(new Price(12, 0));
        }

        @Test
        void sums_cheapest_price_for_each_item() {
            var apple = new Product(
                    "Apple",
                    Set.of(
                            new Offer(1, new Price(1, 0)),
                            new Offer(10, new Price(8, 0))
                    ));
            var orange = new Product(
                    "Orange",
                    Set.of(
                            new Offer(1, new Price(1, 0)),
                            new Offer(2, new Price(1, 50)),
                            new Offer(3, new Price(5, 0))
                    ));
            var checkout = new Checkout(Set.of(apple, orange));
            var cart = Set.of(
                    new Item(apple, 11),
                    new Item(orange, 5)
            );

            var price = checkout.total(cart);

            assertThat(price)
                    .isEqualTo(new Price(13, 0));
        }

        @Test
        void ignores_unknown_products() {
            var apple = new Product("Apple", new Price(1, 0));
            var unknown = new Product("Orange", new Price(10, 0));
            var checkout = new Checkout(Set.of(apple));
            var cart = Set.of(
                    new Item(apple),
                    new Item(unknown)
            );

            var price = checkout.total(cart);

            assertThat(price)
                    .isEqualTo(new Price(1, 0));
        }

        @Test
        void skips_cheapest_if_quantity_not_reached() {
            var apple = new Product(
                    "Apple",
                    Set.of(
                            new Offer(1, new Price(1, 0)),
                            new Offer(2, new Price(1, 10))
                    ));
            var checkout = new Checkout(Set.of(apple));
            var cart = Set.of(new Item(apple));

            var price = checkout.total(cart);

            assertThat(price)
                    .isEqualTo(new Price(1, 0));
        }
    }

    @Nested
    class Filtering {

        @Test
        void removes_unknown_products() {
            var apple = new Product("Apple", new Price(1, 0));
            var orange = new Product("Orange", new Price(10, 0));
            var checkout = new Checkout(Set.of(apple));
            var cart = Set.of(
                    new Item(apple),
                    new Item(orange)
            );

            var filtered = checkout.filter(cart);

            assertThat(filtered)
                    .contains(new Item(apple));
        }
    }
}