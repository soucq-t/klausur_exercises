package domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ItemTest {

    @Nested
    class Constructing {

        @Test
        void fails_without_product() {
            assertThatThrownBy(() -> new Item(null))
                    .isInstanceOfAny(IllegalArgumentException.class, NullPointerException.class);
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1})
        void fails_for_non_positive_quantities(int quantity) {
            var product = new Product("Apple", new Price(1, 0));

            assertThatThrownBy(() -> new Item(product, quantity))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}