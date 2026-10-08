package com.interview.blind75.binarysearch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntBiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SearchInRotatedSortedArrayApproachesTest {

    static Stream<ToIntBiFunction<int[], Integer>> approaches() {
        SearchInRotatedSortedArray s = new SearchInRotatedSortedArray();
        return Stream.of(s::search, s::searchFindPivot);
    }

    @ParameterizedTest @MethodSource("approaches")
    void example1(ToIntBiFunction<int[], Integer> f) { assertEquals(4, f.applyAsInt(new int[]{4, 5, 6, 7, 0, 1, 2}, 0)); }

    @ParameterizedTest @MethodSource("approaches")
    void notFound(ToIntBiFunction<int[], Integer> f) { assertEquals(-1, f.applyAsInt(new int[]{4, 5, 6, 7, 0, 1, 2}, 3)); }

    @ParameterizedTest @MethodSource("approaches")
    void singleMissing(ToIntBiFunction<int[], Integer> f) { assertEquals(-1, f.applyAsInt(new int[]{1}, 0)); }

    @ParameterizedTest @MethodSource("approaches")
    void notRotated(ToIntBiFunction<int[], Integer> f) { assertEquals(3, f.applyAsInt(new int[]{1, 2, 3, 4, 5}, 4)); }

    @ParameterizedTest @MethodSource("approaches")
    void targetInLeftRun(ToIntBiFunction<int[], Integer> f) { assertEquals(1, f.applyAsInt(new int[]{5, 6, 1, 2, 3}, 6)); }
}
