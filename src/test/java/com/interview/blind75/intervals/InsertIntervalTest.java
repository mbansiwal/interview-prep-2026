package com.interview.blind75.intervals;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class InsertIntervalTest {

    private static final InsertInterval solution = new InsertInterval();

    static Stream<Named<BiFunction<int[][], int[], int[][]>>> approaches() {
        return Stream.of(
                Named.of("linear scan", solution::insert),
                Named.of("binary search", solution::insertBinarySearch));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void mergeInMiddle(BiFunction<int[][], int[], int[][]> approach) {
        assertArrayEquals(new int[][]{{1,5},{6,9}}, approach.apply(new int[][]{{1,3},{6,9}}, new int[]{2,5}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void mergeMultiple(BiFunction<int[][], int[], int[][]> approach) {
        assertArrayEquals(new int[][]{{1,2},{3,10},{12,16}},
            approach.apply(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}}, new int[]{4,8}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void noMerge(BiFunction<int[][], int[], int[][]> approach) {
        assertArrayEquals(new int[][]{{1,2},{3,4},{5,6}}, approach.apply(new int[][]{{1,2},{5,6}}, new int[]{3,4}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void emptyList(BiFunction<int[][], int[], int[][]> approach) {
        assertArrayEquals(new int[][]{{5,7}}, approach.apply(new int[][]{}, new int[]{5,7}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void insertAtEdgesAndTouching(BiFunction<int[][], int[], int[][]> approach) {
        assertArrayEquals(new int[][]{{0,0},{1,5}}, approach.apply(new int[][]{{1,5}}, new int[]{0,0}));
        assertArrayEquals(new int[][]{{1,5},{6,8}}, approach.apply(new int[][]{{1,5}}, new int[]{6,8}));
        assertArrayEquals(new int[][]{{1,7}}, approach.apply(new int[][]{{1,5}}, new int[]{5,7}));
        assertArrayEquals(new int[][]{{0,9}}, approach.apply(new int[][]{{1,2},{4,5}}, new int[]{0,9}));
    }
}
