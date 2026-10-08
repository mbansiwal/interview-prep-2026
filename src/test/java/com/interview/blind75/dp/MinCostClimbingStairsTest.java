package com.interview.blind75.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MinCostClimbingStairsTest {

    static Stream<Arguments> approaches() {
        MinCostClimbingStairs s = new MinCostClimbingStairs();
        return Stream.of(
                Arguments.of("rolling O(1)", (ToIntFunction<int[]>) s::minCostClimbingStairs),
                Arguments.of("tabulation", (ToIntFunction<int[]>) s::minCostTabulation));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example1(String name, ToIntFunction<int[]> f) { assertEquals(15, f.applyAsInt(new int[]{10, 15, 20})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example2(String name, ToIntFunction<int[]> f) {
        assertEquals(6, f.applyAsInt(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void twoSteps(String name, ToIntFunction<int[]> f) { assertEquals(0, f.applyAsInt(new int[]{0, 0})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void twoStepsPickCheaper(String name, ToIntFunction<int[]> f) { assertEquals(3, f.applyAsInt(new int[]{5, 3})); }
}
