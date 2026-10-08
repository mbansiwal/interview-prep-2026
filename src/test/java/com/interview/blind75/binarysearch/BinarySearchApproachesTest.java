package com.interview.blind75.binarysearch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntBiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchApproachesTest {

    static Stream<ToIntBiFunction<int[], Integer>> approaches() {
        BinarySearch s = new BinarySearch();
        return Stream.of(s::search, s::searchRecursive);
    }

    @ParameterizedTest @MethodSource("approaches")
    void found(ToIntBiFunction<int[], Integer> f) { assertEquals(4, f.applyAsInt(new int[]{-1, 0, 3, 5, 9, 12}, 9)); }

    @ParameterizedTest @MethodSource("approaches")
    void notFound(ToIntBiFunction<int[], Integer> f) { assertEquals(-1, f.applyAsInt(new int[]{-1, 0, 3, 5, 9, 12}, 2)); }

    @ParameterizedTest @MethodSource("approaches")
    void singleElement(ToIntBiFunction<int[], Integer> f) { assertEquals(0, f.applyAsInt(new int[]{5}, 5)); }

    @ParameterizedTest @MethodSource("approaches")
    void firstAndLast(ToIntBiFunction<int[], Integer> f) {
        assertEquals(0, f.applyAsInt(new int[]{1, 2, 3}, 1));
        assertEquals(2, f.applyAsInt(new int[]{1, 2, 3}, 3));
    }
}
