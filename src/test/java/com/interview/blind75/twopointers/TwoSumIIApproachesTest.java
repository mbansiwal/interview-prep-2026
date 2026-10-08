package com.interview.blind75.twopointers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TwoSumIIApproachesTest {

    static Stream<BiFunction<int[], Integer, int[]>> approaches() {
        TwoSumII s = new TwoSumII();
        return Stream.of(s::twoSum, s::twoSumBinarySearch);
    }

    @ParameterizedTest @MethodSource("approaches")
    void example1(BiFunction<int[], Integer, int[]> f) { assertArrayEquals(new int[]{1, 2}, f.apply(new int[]{2, 7, 11, 15}, 9)); }

    @ParameterizedTest @MethodSource("approaches")
    void example2(BiFunction<int[], Integer, int[]> f) { assertArrayEquals(new int[]{1, 3}, f.apply(new int[]{2, 3, 4}, 6)); }

    @ParameterizedTest @MethodSource("approaches")
    void negatives(BiFunction<int[], Integer, int[]> f) { assertArrayEquals(new int[]{1, 2}, f.apply(new int[]{-1, 0}, -1)); }

    @ParameterizedTest @MethodSource("approaches")
    void duplicates(BiFunction<int[], Integer, int[]> f) { assertArrayEquals(new int[]{2, 3}, f.apply(new int[]{1, 3, 3, 9}, 6)); }
}
