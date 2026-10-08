package com.interview.blind75.graphs;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CourseScheduleIITest {

    private static final CourseScheduleII solution = new CourseScheduleII();

    static Stream<Named<BiFunction<Integer, int[][], int[]>>> approaches() {
        return Stream.of(
                Named.of("dfs post-order", solution::findOrder),
                Named.of("kahn bfs", solution::findOrderKahn));
    }

    private static void assertValidOrder(int numCourses, int[][] prereqs, int[] order) {
        assertEquals(numCourses, order.length);
        int[] position = new int[numCourses];
        java.util.Arrays.fill(position, -1);
        for (int i = 0; i < order.length; i++) position[order[i]] = i;
        for (int p : position) assertNotEquals(-1, p, "every course must appear");
        for (int[] pre : prereqs) {
            assertTrue(position[pre[1]] < position[pre[0]],
                    "prereq " + pre[1] + " must come before " + pre[0]);
        }
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void twoCoursesOrder(BiFunction<Integer, int[][], int[]> approach) {
        int[][] prereqs = {{1, 0}};
        assertValidOrder(2, prereqs, approach.apply(2, prereqs));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void fourCoursesDiamond(BiFunction<Integer, int[][], int[]> approach) {
        int[][] prereqs = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        assertValidOrder(4, prereqs, approach.apply(4, prereqs));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void cycleReturnsEmpty(BiFunction<Integer, int[][], int[]> approach) {
        assertEquals(0, approach.apply(2, new int[][]{{1, 0}, {0, 1}}).length);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void noDependencies(BiFunction<Integer, int[][], int[]> approach) {
        assertValidOrder(3, new int[0][], approach.apply(3, new int[][]{}));
    }
}
