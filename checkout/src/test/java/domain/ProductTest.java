package domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProductTest {

    @Nested
    class Construction {

        @ParameterizedTest
        @NullAndEmptySource
        void fails_without_description(String description) {
            var price = new Price(1, 1);
            assertThatThrownBy(() -> new Product(description, price))
                    .isInstanceOfAny(IllegalArgumentException.class, NullPointerException.class);
        }

        @ParameterizedTest
        @NullAndEmptySource
        void fails_without_offer(Set<Offer> offers) {
            assertThatThrownBy(() -> new Product("description", offers))
                    .isInstanceOfAny(IllegalArgumentException.class, NullPointerException.class);
        }

        @Test
        void fails_without_price_for_single() {
            var offers = Set.of(new Offer(2, new Price(1, 1)));

            assertThatThrownBy(() -> new Product("description", offers))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}