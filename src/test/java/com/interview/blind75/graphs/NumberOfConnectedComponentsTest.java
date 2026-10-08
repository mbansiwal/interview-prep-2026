package com.interview.blind75.graphs;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NumberOfConnectedComponentsTest {

    private static final NumberOfConnectedComponents solution = new NumberOfConnectedComponents();

    static Stream<Named<BiFunction<Integer, int[][], Integer>>> approaches() {
        return Stream.of(
                Named.of("union-find", solution::countComponents),
                Named.of("dfs", solution::countComponentsDfs));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void twoComponents(BiFunction<Integer, int[][], Integer> approach) {
        assertEquals(2, approach.apply(5, new int[][]{{0, 1}, {1, 2}, {3, 4}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleChain(BiFunction<Integer, int[][], Integer> approach) {
        assertEquals(1, approach.apply(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void noEdgesEveryNodeAlone(BiFunction<Integer, int[][], Integer> approach) {
        assertEquals(4, approach.apply(4, new int[][]{}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void cycleDoesNotDoubleCount(BiFunction<Integer, int[][], Integer> approach) {
        assertEquals(2, approach.apply(4, new int[][]{{0, 1}, {1, 2}, {2, 0}}));
    }
}
