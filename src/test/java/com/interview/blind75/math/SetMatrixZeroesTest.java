package com.interview.blind75.math;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SetMatrixZeroesTest {

    static Stream<Arguments> approaches() {
        SetMatrixZeroes s = new SetMatrixZeroes();
        return Stream.of(
                Arguments.of("in-place markers", (Consumer<int[][]>) s::setZeroes),
                Arguments.of("flag arrays", (Consumer<int[][]>) s::setZeroesWithFlags));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example1(String name, Consumer<int[][]> f) {
        int[][] m = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        f.accept(m);
        assertArrayEquals(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}, m);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example2(String name, Consumer<int[][]> f) {
        int[][] m = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        f.accept(m);
        assertArrayEquals(new int[][]{{0, 0, 0, 0}, {0, 4, 5, 0}, {0, 3, 1, 0}}, m);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void noZeroes(String name, Consumer<int[][]> f) {
        int[][] m = {{1, 2}, {3, 4}};
        f.accept(m);
        assertArrayEquals(new int[][]{{1, 2}, {3, 4}}, m);
    }

    // zero only in the first column must not wipe the first row
    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void zeroInFirstColumnOnly(String name, Consumer<int[][]> f) {
        int[][] m = {{1, 2, 3}, {0, 5, 6}};
        f.accept(m);
        assertArrayEquals(new int[][]{{0, 2, 3}, {0, 0, 0}}, m);
    }
}
