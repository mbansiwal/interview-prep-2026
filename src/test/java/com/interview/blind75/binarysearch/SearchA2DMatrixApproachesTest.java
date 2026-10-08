package com.interview.blind75.binarysearch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SearchA2DMatrixApproachesTest {

    static Stream<BiPredicate<int[][], Integer>> approaches() {
        SearchA2DMatrix s = new SearchA2DMatrix();
        return Stream.of(s::searchMatrix, s::searchMatrixTwoPhase, s::searchMatrixStaircase);
    }

    private static final int[][] M = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};

    @ParameterizedTest @MethodSource("approaches")
    void found(BiPredicate<int[][], Integer> f) { assertTrue(f.test(M, 3)); }

    @ParameterizedTest @MethodSource("approaches")
    void notFound(BiPredicate<int[][], Integer> f) { assertFalse(f.test(M, 13)); }

    @ParameterizedTest @MethodSource("approaches")
    void corners(BiPredicate<int[][], Integer> f) {
        assertTrue(f.test(M, 1));
        assertTrue(f.test(M, 60));
    }

    @ParameterizedTest @MethodSource("approaches")
    void outOfRange(BiPredicate<int[][], Integer> f) {
        assertFalse(f.test(M, 0));
        assertFalse(f.test(M, 61));
    }

    @ParameterizedTest @MethodSource("approaches")
    void singleCell(BiPredicate<int[][], Integer> f) { assertTrue(f.test(new int[][]{{4}}, 4)); }
}
