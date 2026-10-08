package com.interview.blind75.math;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RotateImageTest {

    static Stream<Arguments> approaches() {
        RotateImage s = new RotateImage();
        return Stream.of(
                Arguments.of("transpose + reverse", (Consumer<int[][]>) s::rotate),
                Arguments.of("four-way layers", (Consumer<int[][]>) s::rotateLayers));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void rotate3x3(String name, Consumer<int[][]> f) {
        int[][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        f.accept(m);
        assertArrayEquals(new int[][]{{7, 4, 1}, {8, 5, 2}, {9, 6, 3}}, m);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void rotate4x4(String name, Consumer<int[][]> f) {
        int[][] m = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        f.accept(m);
        assertArrayEquals(new int[][]{{15, 13, 2, 5}, {14, 3, 4, 1}, {12, 6, 8, 9}, {16, 7, 10, 11}}, m);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void rotate1x1(String name, Consumer<int[][]> f) {
        int[][] m = {{1}};
        f.accept(m);
        assertArrayEquals(new int[][]{{1}}, m);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void rotate2x2(String name, Consumer<int[][]> f) {
        int[][] m = {{1, 2}, {3, 4}};
        f.accept(m);
        assertArrayEquals(new int[][]{{3, 1}, {4, 2}}, m);
    }
}
