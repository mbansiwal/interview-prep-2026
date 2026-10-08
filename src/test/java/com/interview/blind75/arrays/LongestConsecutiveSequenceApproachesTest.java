package com.interview.blind75.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LongestConsecutiveSequenceApproachesTest {

    static Stream<ToIntFunction<int[]>> approaches() {
        LongestConsecutiveSequence s = new LongestConsecutiveSequence();
        return Stream.of(s::longestConsecutive, s::longestConsecutiveSorting);
    }

    @ParameterizedTest @MethodSource("approaches")
    void example1(ToIntFunction<int[]> f) { assertEquals(4, f.applyAsInt(new int[]{100, 4, 200, 1, 3, 2})); }

    @ParameterizedTest @MethodSource("approaches")
    void example2WithDuplicates(ToIntFunction<int[]> f) { assertEquals(9, f.applyAsInt(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1})); }

    @ParameterizedTest @MethodSource("approaches")
    void empty(ToIntFunction<int[]> f) { assertEquals(0, f.applyAsInt(new int[]{})); }

    @ParameterizedTest @MethodSource("approaches")
    void duplicatesInsideRun(ToIntFunction<int[]> f) { assertEquals(3, f.applyAsInt(new int[]{1, 2, 2, 3})); }
}
