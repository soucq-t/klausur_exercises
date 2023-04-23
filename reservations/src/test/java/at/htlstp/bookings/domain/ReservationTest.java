package at.htlstp.bookings.domain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.time.Month;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.assertj.core.api.Assertions.assertThat;

public class ReservationTest {

    @Nested
    class Validation {

        private static ValidatorFactory validatorFactory;
        private static Validator validator;

        @BeforeAll
        public static void createValidator() {
            validatorFactory = buildDefaultValidatorFactory();
            validator = validatorFactory.getValidator();
        }

        @AfterAll
        public static void close() {
            validatorFactory.close();
        }

        @Test
        void succeeds_without_id() {
            var reservation = new Reservation(
                    null,
                    new Table(null, 1),
                    new Guest(),
                    LocalDateTime.of(2030, Month.JANUARY, 1, 12, 0),
                    1);

            var violations = validator.validate(reservation);

            assertThat(violations).isEmpty();
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 2})
        void succeeds_with_positive_group_size_up_to_table_size(int groupSize) {
            var reservation = new Reservation(
                    1L,
                    new Table(null, 2),
                    new Guest(),
                    LocalDateTime.of(2030, Month.JANUARY, 1, 12, 0),
                    groupSize);

            var violations = validator.validate(reservation);

            assertThat(violations).isEmpty();
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1})
        void fails_with_non_positive_group_size(int groupSize) {
            var reservation = new Reservation(
                    1L,
                    new Table(null, 10),
                    new Guest(),
                    LocalDateTime.of(2030, Month.JANUARY, 1, 12, 0),
                    groupSize);

            var violations = validator.validate(reservation);

            assertThat(violations).isNotEmpty();
        }

        @Test
        void fails_with_past_reservation_date() {
            var reservation = new Reservation(
                    1L,
                    new Table(null, 10),
                    new Guest(),
                    LocalDateTime.now().minusMinutes(1L),
                    1);

            var violations = validator.validate(reservation);

            assertThat(violations).isNotEmpty();
        }
    }
}
