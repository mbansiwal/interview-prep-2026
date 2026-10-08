package com.interview.blind75.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LongestIncreasingSubsequenceTest {

    static Stream<Arguments> approaches() {
        LongestIncreasingSubsequence s = new LongestIncreasingSubsequence();
        return Stream.of(
                Arguments.of("patience n log n", (ToIntFunction<int[]>) s::lengthOfLIS),
                Arguments.of("quadratic DP", (ToIntFunction<int[]>) s::lengthOfLISQuadratic));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example1(String name, ToIntFunction<int[]> f) { assertEquals(4, f.applyAsInt(new int[]{10, 9, 2, 5, 3, 7, 101, 18})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example2(String name, ToIntFunction<int[]> f) { assertEquals(4, f.applyAsInt(new int[]{0, 1, 0, 3, 2, 3})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void allSame(String name, ToIntFunction<int[]> f) { assertEquals(1, f.applyAsInt(new int[]{7, 7, 7, 7, 7})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void singleElement(String name, ToIntFunction<int[]> f) { assertEquals(1, f.applyAsInt(new int[]{42})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void strictlyDecreasing(String name, ToIntFunction<int[]> f) { assertEquals(1, f.applyAsInt(new int[]{5, 4, 3, 2, 1})); }
}
