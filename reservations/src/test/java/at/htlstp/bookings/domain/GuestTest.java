package at.htlstp.bookings.domain;

import org.junit.jupiter.api.*;

import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.assertj.core.api.Assertions.assertThat;

public class GuestTest {

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
            var guest = new Guest(null, "name");

            var violations = validator.validate(guest);

            assertThat(violations).isEmpty();
        }

        @Test
        void succeeds_without_name() {
            var guest = new Guest(1L, null);

            var violations = validator.validate(guest);

            assertThat(violations).isEmpty();
        }
    }
}
