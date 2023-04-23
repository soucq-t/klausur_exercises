package service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrbitsTest {

    @Nested
    class Construction {

        @ParameterizedTest
        @ValueSource(strings = {
                "no_bracket",
                "multiple)brackets)",
                ")multiple)brackets",
                "_)multiple)brackets)",
                "_)multiple)brackets)_",
                "_)",
                ")_",
                "same)same"
        })
        void fails_for_invalid_input(String input) {
            assertThatThrownBy(() -> new OrbitService(input))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest
        @NullAndEmptySource
        void fails_for_empty_input(String input) {
            assertThatThrownBy(() -> new OrbitService(input))
                    .isInstanceOfAny(IllegalArgumentException.class, NullPointerException.class);
        }

        /*
            A - B - A
         */
        @Test
        void fails_without_root() {
            assertThatThrownBy(() -> new OrbitService("A)B", "B)A"))
                    .isInstanceOfAny(IllegalArgumentException.class);
        }


        /*
            A - B      C - D
         */
        @Test
        void fails_for_multiple_root() {
            assertThatThrownBy(() -> new OrbitService("A)B", "C)D"))
                    .isInstanceOfAny(IllegalArgumentException.class);
        }
    }

    @Nested
    class GetRoot {

        @Test
        void works_tiny() {
            var service = new OrbitService("root)b");

            assertThat(service.getRoot())
                    .isEqualTo("root");
        }

        /*
                G - H         J - K
               /              /
            COM - B - C - D - E - F
                       \
                         I
        */
        @Test
        void works_small() {
            var service = new OrbitService("B)C", "root_not_first)B", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K");

            assertThat(service.getRoot())
                    .isEqualTo("root_not_first");
        }

        @Test
        void works_big() throws IOException, URISyntaxException {
            var uri = getClass().getResource("/input_orbits.txt").toURI();
            var input = Files.readAllLines(Paths.get(uri))
                    .toArray(new String[0]);
            var service = new OrbitService(input);

            assertThat(service.getRoot())
                    .isEqualTo("COM");
        }
    }


    @Nested
    class CountTotalDistance {

        @Test
        void works_tiny() {
            var service = new OrbitService("root)b");

            assertThat(service.totalDistance())
                    .isEqualTo(1);
        }


        /*
                   G - H       J - K - L
                  /           /
            COM - B - C - D - E - F
                           \
                            I

            B: 1, C: 2, D: 3, E: 4, F: 5, I: 4, G: 2, H: 3, J: 5, K: 6, L: 7
        */
        @Test
        void works_small() {
            var service = new OrbitService("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L");

            assertThat(service.totalDistance())
                    .isEqualTo(42);
        }

        @Test
        void works_big() throws IOException, URISyntaxException {
            var uri = Objects.requireNonNull(getClass().getResource("/input_orbits.txt")).toURI();
            var input = Files
                    .readAllLines(Paths.get(uri))
                    .toArray(new String[0]);
            var service = new OrbitService(input);

            assertThat(service.totalDistance())
                    .isEqualTo(295936);
        }
    }
}
