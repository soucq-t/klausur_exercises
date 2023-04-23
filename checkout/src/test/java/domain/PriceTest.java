package domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PriceTest {

    @Test
    void multiplies() {
        var euro = new Price(1, 52);

        assertThat(euro.multiply(2))
                .isEqualTo(new Price(3, 4));
    }

    @Nested
    class Summing {

        @Test
        void works() {
            var prices = List.of(
                    new Price(1, 92),
                    new Price(2, 21)
            );

            var sum = Price.sum(prices);

            assertThat(sum)
                    .isEqualTo(new Price(4, 13));
        }

        @Test
        void returns_zero_for_empty_list() {
            var sum = Price.sum(List.of());

            assertThat(sum)
                    .isEqualTo(new Price(0, 0));
        }

    }

    @Nested
    class Construction {

        @Test
        void works_for_positive_values() {
            var euro = new Price(1, 2);

            assertThat(euro.toString())
                    .hasToString("1.02");
        }

        @Test
        void converts_cents_to_euros() {
            var euro = new Price(1, 103);

            assertThat(euro)
                    .isEqualTo(new Price(2, 3));
        }

        @Test
        void fails_for_negative_euro() {
            assertThatThrownBy(() -> new Price(-1, 1))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void fails_for_negative_cents() {
            assertThatThrownBy(() -> new Price(1, -1))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void works_for_zero() {
            var euro = new Price(0, 0);

            assertThat(euro.toString())
                    .hasToString("0.00");
        }

    }
}
