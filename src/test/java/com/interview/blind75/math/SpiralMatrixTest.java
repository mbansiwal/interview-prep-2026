package com.interview.blind75.math;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SpiralMatrixTest {

    static Stream<Arguments> approaches() {
        SpiralMatrix s = new SpiralMatrix();
        return Stream.of(
                Arguments.of("boundaries", (Function<int[][], List<Integer>>) s::spiralOrder),
                Arguments.of("simulation", (Function<int[][], List<Integer>>) s::spiralOrderSimulation));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void threeByThree(String name, Function<int[][], List<Integer>> f) {
        assertEquals(List.of(1, 2, 3, 6, 9, 8, 7, 4, 5), f.apply(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void threeByFour(String name, Function<int[][], List<Integer>> f) {
        assertEquals(List.of(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7),
                f.apply(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void singleRow(String name, Function<int[][], List<Integer>> f) {
        assertEquals(List.of(1, 2, 3), f.apply(new int[][]{{1, 2, 3}}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void singleColumn(String name, Function<int[][], List<Integer>> f) {
        assertEquals(List.of(1, 2, 3), f.apply(new int[][]{{1}, {2}, {3}}));
    }
}
