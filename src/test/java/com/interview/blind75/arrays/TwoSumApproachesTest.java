package com.interview.blind75.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TwoSumApproachesTest {

    static Stream<BiFunction<int[], Integer, int[]>> approaches() {
        TwoSum s = new TwoSum();
        return Stream.of(s::twoSum, s::twoSumSortedPointers);
    }

    private static int[] sorted(int[] a) { int[] c = a.clone(); Arrays.sort(c); return c; }

    @ParameterizedTest @MethodSource("approaches")
    void example1(BiFunction<int[], Integer, int[]> f) { assertArrayEquals(new int[]{0, 1}, sorted(f.apply(new int[]{2, 7, 11, 15}, 9))); }

    @ParameterizedTest @MethodSource("approaches")
    void example2(BiFunction<int[], Integer, int[]> f) { assertArrayEquals(new int[]{1, 2}, sorted(f.apply(new int[]{3, 2, 4}, 6))); }

    @ParameterizedTest @MethodSource("approaches")
    void duplicateValues(BiFunction<int[], Integer, int[]> f) { assertArrayEquals(new int[]{0, 1}, sorted(f.apply(new int[]{3, 3}, 6))); }

    @ParameterizedTest @MethodSource("approaches")
    void negatives(BiFunction<int[], Integer, int[]> f) { assertArrayEquals(new int[]{2, 4}, sorted(f.apply(new int[]{-1, -2, -3, -4, -5}, -8))); }
}
