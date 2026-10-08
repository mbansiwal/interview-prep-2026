package com.interview.blind75.intervals;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NonOverlappingIntervalsTest {

    private static final NonOverlappingIntervals solution = new NonOverlappingIntervals();

    static Stream<Named<ToIntFunction<int[][]>>> approaches() {
        return Stream.of(
                Named.of("sort by end", solution::eraseOverlapIntervals),
                Named.of("sort by start", solution::eraseOverlapIntervalsByStart));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void removeOne(ToIntFunction<int[][]> approach) {
        assertEquals(1, approach.applyAsInt(new int[][]{{1,2},{2,3},{3,4},{1,3}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void removeTwo(ToIntFunction<int[][]> approach) {
        assertEquals(2, approach.applyAsInt(new int[][]{{1,2},{1,2},{1,2}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void noRemoval(ToIntFunction<int[][]> approach) {
        assertEquals(0, approach.applyAsInt(new int[][]{{1,2},{2,3}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void longIntervalCoveringManyShortOnes(ToIntFunction<int[][]> approach) {
        // Removing the single long one is optimal, not the three short ones.
        assertEquals(1, approach.applyAsInt(new int[][]{{1,100},{1,2},{3,4},{5,6}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void emptyAndSingle(ToIntFunction<int[][]> approach) {
        assertEquals(0, approach.applyAsInt(new int[][]{}));
        assertEquals(0, approach.applyAsInt(new int[][]{{-5, 5}}));
    }
}
