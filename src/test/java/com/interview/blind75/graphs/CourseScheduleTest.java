package com.interview.blind75.graphs;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CourseScheduleTest {

    private static final CourseSchedule solution = new CourseSchedule();

    static Stream<Named<BiPredicate<Integer, int[][]>>> approaches() {
        return Stream.of(
                Named.of("dfs colours", solution::canFinish),
                Named.of("kahn bfs", solution::canFinishKahn));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void canFinish(BiPredicate<Integer, int[][]> approach) {
        assertTrue(approach.test(2, new int[][]{{1, 0}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void cyclePrevents(BiPredicate<Integer, int[][]> approach) {
        assertFalse(approach.test(2, new int[][]{{1, 0}, {0, 1}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void noPrerequisites(BiPredicate<Integer, int[][]> approach) {
        assertTrue(approach.test(5, new int[][]{}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void longerCycleAmongOtherwiseFineCourses(BiPredicate<Integer, int[][]> approach) {
        assertFalse(approach.test(5, new int[][]{{1, 0}, {2, 1}, {3, 2}, {1, 3}, {4, 0}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void diamondIsFine(BiPredicate<Integer, int[][]> approach) {
        assertTrue(approach.test(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}));
    }
}
