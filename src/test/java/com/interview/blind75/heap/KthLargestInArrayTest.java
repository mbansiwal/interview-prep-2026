package com.interview.blind75.heap;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class KthLargestInArrayTest {

    private static final KthLargestInArray solution = new KthLargestInArray();

    static Stream<Named<BiFunction<int[], Integer, Integer>>> approaches() {
        return Stream.of(
                Named.of("min-heap", solution::findKthLargest),
                Named.of("quickselect", solution::findKthLargestQuickselect),
                Named.of("counting sort", solution::findKthLargestCounting));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void k2(BiFunction<int[], Integer, Integer> approach) {
        assertEquals(5, approach.apply(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void k4WithDuplicates(BiFunction<int[], Integer, Integer> approach) {
        assertEquals(4, approach.apply(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void k1(BiFunction<int[], Integer, Integer> approach) {
        assertEquals(6, approach.apply(new int[]{1, 2, 3, 4, 5, 6}, 1));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleElement(BiFunction<int[], Integer, Integer> approach) {
        assertEquals(1, approach.apply(new int[]{1}, 1));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void allNegativeDuplicates(BiFunction<int[], Integer, Integer> approach) {
        assertEquals(-1, approach.apply(new int[]{-1, -1, -1}, 2));
        assertEquals(-10000, approach.apply(new int[]{10000, -10000, 0}, 3));
    }
}
