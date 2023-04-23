package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OfferTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void construction_fails_for_non_positive_quantity(int quantity) {
        var price = new Price(1, 0);
        assertThatThrownBy(() -> new Offer(quantity, price))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
