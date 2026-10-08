package com.interview.blind75.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TopKFrequentApproachesTest {

    static Stream<BiFunction<int[], Integer, int[]>> approaches() {
        TopKFrequent s = new TopKFrequent();
        return Stream.of(s::topKFrequent, s::topKFrequentHeap, s::topKFrequentQuickselect);
    }

    private static int[] sorted(int[] a) { int[] c = a.clone(); Arrays.sort(c); return c; }

    @ParameterizedTest @MethodSource("approaches")
    void example1(BiFunction<int[], Integer, int[]> f) { assertArrayEquals(new int[]{1, 2}, sorted(f.apply(new int[]{1, 1, 1, 2, 2, 3}, 2))); }

    @ParameterizedTest @MethodSource("approaches")
    void singleElement(BiFunction<int[], Integer, int[]> f) { assertArrayEquals(new int[]{1}, sorted(f.apply(new int[]{1}, 1))); }

    @ParameterizedTest @MethodSource("approaches")
    void negativesAndUniqueTopK(BiFunction<int[], Integer, int[]> f) {
        assertArrayEquals(new int[]{-1, 4}, sorted(f.apply(new int[]{4, 4, 4, -1, -1, 6, 7}, 2)));
    }

    @ParameterizedTest @MethodSource("approaches")
    void kEqualsDistinctCount(BiFunction<int[], Integer, int[]> f) {
        assertArrayEquals(new int[]{5, 6, 7}, sorted(f.apply(new int[]{5, 6, 7, 7}, 3)));
    }
}
