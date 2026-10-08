package com.interview.blind75.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ContainsDuplicateApproachesTest {

    static Stream<Predicate<int[]>> approaches() {
        ContainsDuplicate s = new ContainsDuplicate();
        return Stream.of(s::containsDuplicate, s::containsDuplicateSorting);
    }

    @ParameterizedTest @MethodSource("approaches")
    void hasDuplicate(Predicate<int[]> f) { assertTrue(f.test(new int[]{1, 2, 3, 1})); }

    @ParameterizedTest @MethodSource("approaches")
    void allDistinct(Predicate<int[]> f) { assertFalse(f.test(new int[]{1, 2, 3, 4})); }

    @ParameterizedTest @MethodSource("approaches")
    void singleElement(Predicate<int[]> f) { assertFalse(f.test(new int[]{7})); }

    @ParameterizedTest @MethodSource("approaches")
    void manyDuplicatesWithNegatives(Predicate<int[]> f) { assertTrue(f.test(new int[]{-1, 1, 3, 3, 4, 3, 2, 4, 2})); }
}
