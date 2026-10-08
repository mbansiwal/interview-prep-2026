package com.interview.blind75.greedy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MaximumSubarrayTest {

    static Stream<Arguments> approaches() {
        MaximumSubarray s = new MaximumSubarray();
        return Stream.of(
                Arguments.of("kadane", (ToIntFunction<int[]>) s::maxSubArray),
                Arguments.of("prefix min", (ToIntFunction<int[]>) s::maxSubArrayPrefix),
                Arguments.of("divide & conquer", (ToIntFunction<int[]>) s::maxSubArrayDivideConquer));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example1(String name, ToIntFunction<int[]> f) { assertEquals(6, f.applyAsInt(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void allPositive(String name, ToIntFunction<int[]> f) { assertEquals(23, f.applyAsInt(new int[]{5, 4, -1, 7, 8})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void singleNegative(String name, ToIntFunction<int[]> f) { assertEquals(-1, f.applyAsInt(new int[]{-1})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void allNegativePicksLargest(String name, ToIntFunction<int[]> f) { assertEquals(-2, f.applyAsInt(new int[]{-3, -2, -5})); }
}
