package com.interview.blind75.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PartitionEqualSubsetSumTest {

    static Stream<Arguments> approaches() {
        PartitionEqualSubsetSum s = new PartitionEqualSubsetSum();
        return Stream.of(
                Arguments.of("1D dp", (Predicate<int[]>) s::canPartition),
                Arguments.of("bitset", (Predicate<int[]>) s::canPartitionBitset),
                Arguments.of("memo", (Predicate<int[]>) s::canPartitionMemo));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void canPartition(String name, Predicate<int[]> f) { assertTrue(f.test(new int[]{1, 5, 11, 5})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void cannotPartition(String name, Predicate<int[]> f) { assertFalse(f.test(new int[]{1, 2, 3, 5})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void oddSum(String name, Predicate<int[]> f) { assertFalse(f.test(new int[]{1, 2})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void singleElement(String name, Predicate<int[]> f) { assertFalse(f.test(new int[]{2})); }

    // even total (12) but no subset reaches 6 without reusing a number
    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void evenSumStillImpossible(String name, Predicate<int[]> f) { assertFalse(f.test(new int[]{1, 1, 10})); }
}
