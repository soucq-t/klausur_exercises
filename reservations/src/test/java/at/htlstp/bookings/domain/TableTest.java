package at.htlstp.bookings.domain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.assertj.core.api.Assertions.assertThat;

public class TableTest {

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
            var table = new Table(null, 1);

            var violations = validator.validate(table);

            assertThat(violations).isEmpty();
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 2})
        void succeeds_with_positive_size(int size) {
            var table = new Table(1L, size);

            var violations = validator.validate(table);

            assertThat(violations).isEmpty();
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1})
        void fails_with_non_positive_size(int size) {
            var table = new Table(1L, size);

            var violations = validator.validate(table);

            assertThat(violations).isNotEmpty();
        }

        @Test
        void fails_without_size() {
            var table = new Table(1L, null);

            var violations = validator.validate(table);

            assertThat(violations).isNotEmpty();
        }
    }

}
