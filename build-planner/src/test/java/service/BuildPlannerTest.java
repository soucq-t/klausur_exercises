package service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BuildPlannerTest {

    @Nested
    class GetRequirements {

        @Test
        void ignores_invalid_requirements() {
            var buildPlanner = new BuildPlanner(
                    "Requirements must match the specified pattern",
                    "^Step [A-Z] must be finished before step [A-Z] can begin\\.$"
            );

            assertThat(buildPlanner.getRequirements())
                    .isEmpty();
        }

        @Test
        void works_for_single_step() {
            var buildPlanner = new BuildPlanner("Step B must be finished before step A can begin.");
            Map<Character, Set<Character>> expected = new TreeMap<>();
            expected.put('A', Set.of('B'));
            expected.put('B', Set.of());

            var actual = buildPlanner.getRequirements();

            assertThat(actual).containsExactlyEntriesOf(expected);
        }

        @Test
        void works_for_multiple_steps() {
            var buildPlanner = new BuildPlanner(
                    "Step B must be finished before step A can begin.",
                    "Invalid requirement",
                    "Step C must be finished before step A can begin."
            );
            Map<Character, Set<Character>> expected = new TreeMap<>();
            expected.put('A', Set.of('B', 'C'));
            expected.put('B', Set.of());
            expected.put('C', Set.of());

            var actual = buildPlanner.getRequirements();

            assertThat(actual).containsExactlyEntriesOf(expected);
        }

        @Test
        void works_for_unsolvable_requirements() {
            var buildPlanner = new BuildPlanner(
                    "Step B must be finished before step A can begin.",
                    "Step X must be finished before step X can begin.",
                    "Step A must be finished before step B can begin."
            );
            Map<Character, Set<Character>> expected = new TreeMap<>();
            expected.put('A', Set.of('B'));
            expected.put('B', Set.of('A'));
            expected.put('X', Set.of('X'));

            var actual = buildPlanner.getRequirements();

            assertThat(actual).containsExactlyEntriesOf(expected);
        }
    }

    @Nested
    class Order {

        @Test
        void fails_for_step_requiring_itself() {
            var buildPlanner = new BuildPlanner(
                    "Step A must be finished before step B can begin.",
                    "Step X must be finished before step X can begin."
            );

            assertThatThrownBy(buildPlanner::order);
        }

        @Test
        void fails_for_circular_dependencies() {
            var buildPlanner = new BuildPlanner(
                    "Step B must be finished before step A can begin.",
                    "Step A must be finished before step B can begin.",
                    "Step C must be finished before step D can begin."
            );

            assertThatThrownBy(buildPlanner::order);
        }

        @Test
        void works_for_linear_requirements() {
            var buildPlanner = new BuildPlanner(
                    "Step C must be finished before step D can begin.",
                    "Step A must be finished before step X can begin.",
                    "Step X must be finished before step C can begin."
            );

            var order = buildPlanner.order();

            assertThat(order).containsExactlyElementsOf(List.of('A', 'X', 'C', 'D'));
        }

        @Test
        void alphabetically_produced_if_multiple_possibilities() {
            var buildPlanner = new BuildPlanner(
                    "Step A must be finished before step C can begin.",
                    "Step A must be finished before step B can begin."
            );

            var order = buildPlanner.order();

            assertThat(order).containsExactlyElementsOf(List.of('A', 'B', 'C'));
        }

        @Test
        void works_for_small_problem() {
            var buildPlanner = new BuildPlanner(
                    "Step C must be finished before step A can begin.",
                    "Step C must be finished before step F can begin.",
                    "Step A must be finished before step B can begin.",
                    "Step A must be finished before step D can begin.",
                    "Step B must be finished before step E can begin.",
                    "Step D must be finished before step E can begin.",
                    "Step F must be finished before step E can begin."
            );

            var order = buildPlanner.order();

            assertThat(order).containsExactlyElementsOf(List.of('C', 'A', 'B', 'D', 'F', 'E'));
        }

        @Test
        void works_for_large_problem() throws IOException, URISyntaxException {
            var uri = Objects.requireNonNull(getClass().getResource("/large.txt")).toURI();
            var requirements = Files
                    .readAllLines(Paths.get(uri))
                    .toArray(new String[0]);
            var buildPlanner = new BuildPlanner(requirements);

            var order = buildPlanner.order();

            assertThat(order).containsExactlyElementsOf(List.of(
                    'A', 'B', 'G', 'K', 'C', 'M', 'V', 'W', 'Y', 'D', 'E', 'H', 'F',
                    'O', 'P', 'Q', 'U', 'I', 'L', 'S', 'T', 'N', 'Z', 'R', 'J', 'X'
            ));
        }
    }

}
