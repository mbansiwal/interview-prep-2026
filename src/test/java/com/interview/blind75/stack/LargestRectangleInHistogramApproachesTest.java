package com.interview.blind75.stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LargestRectangleInHistogramApproachesTest {

    static Stream<ToIntFunction<int[]>> approaches() {
        LargestRectangleInHistogram s = new LargestRectangleInHistogram();
        return Stream.of(s::largestRectangleArea, s::largestRectangleAreaBoundaries);
    }

    @ParameterizedTest @MethodSource("approaches")
    void example1(ToIntFunction<int[]> f) { assertEquals(10, f.applyAsInt(new int[]{2, 1, 5, 6, 2, 3})); }

    @ParameterizedTest @MethodSource("approaches")
    void twoBars(ToIntFunction<int[]> f) { assertEquals(4, f.applyAsInt(new int[]{2, 4})); }

    @ParameterizedTest @MethodSource("approaches")
    void singleBar(ToIntFunction<int[]> f) { assertEquals(7, f.applyAsInt(new int[]{7})); }

    @ParameterizedTest @MethodSource("approaches")
    void equalHeights(ToIntFunction<int[]> f) { assertEquals(12, f.applyAsInt(new int[]{3, 3, 3, 3})); }

    @ParameterizedTest @MethodSource("approaches")
    void withZero(ToIntFunction<int[]> f) { assertEquals(6, f.applyAsInt(new int[]{4, 0, 2, 3, 3})); }
}
