package com.interview.blind75.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MaximumProductSubarrayTest {

    static Stream<Arguments> approaches() {
        MaximumProductSubarray s = new MaximumProductSubarray();
        return Stream.of(
                Arguments.of("max/min tracking", (ToIntFunction<int[]>) s::maxProduct),
                Arguments.of("prefix/suffix", (ToIntFunction<int[]>) s::maxProductPrefixSuffix));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example1(String name, ToIntFunction<int[]> f) { assertEquals(6, f.applyAsInt(new int[]{2, 3, -2, 4})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void withZero(String name, ToIntFunction<int[]> f) { assertEquals(0, f.applyAsInt(new int[]{-2, 0, -1})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void allNegative(String name, ToIntFunction<int[]> f) { assertEquals(2, f.applyAsInt(new int[]{-2, -1})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void singleNegative(String name, ToIntFunction<int[]> f) { assertEquals(-3, f.applyAsInt(new int[]{-3})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void oddNegativesDropSuffix(String name, ToIntFunction<int[]> f) { assertEquals(24, f.applyAsInt(new int[]{2, -5, -2, -4, 3})); }
}
