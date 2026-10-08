package com.interview.blind75.backtracking;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SubsetsIITest {

    private static final SubsetsII solution = new SubsetsII();

    static Stream<Named<Function<int[], List<List<Integer>>>>> approaches() {
        return Stream.of(
                Named.of("backtracking", solution::subsetsWithDup),
                Named.of("iterative", solution::subsetsWithDupIterative));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void withDuplicates(Function<int[], List<List<Integer>>> approach) {
        assertEquals(SubsetsTest.normalize(List.of(List.of(), List.of(1), List.of(2),
                        List.of(1, 2), List.of(2, 2), List.of(1, 2, 2))),
                SubsetsTest.normalize(approach.apply(new int[]{1, 2, 2})));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleElement(Function<int[], List<List<Integer>>> approach) {
        assertEquals(SubsetsTest.normalize(List.of(List.of(), List.of(0))),
                SubsetsTest.normalize(approach.apply(new int[]{0})));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void allSame(Function<int[], List<List<Integer>>> approach) {
        assertEquals(SubsetsTest.normalize(List.of(List.of(), List.of(1), List.of(1, 1), List.of(1, 1, 1))),
                SubsetsTest.normalize(approach.apply(new int[]{1, 1, 1})));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void unsortedWithTwoDuplicateGroups(Function<int[], List<List<Integer>>> approach) {
        // {2,2,1,1}: each value appears 0, 1 or 2 times → 3 * 3 = 9 distinct subsets
        List<List<Integer>> result = approach.apply(new int[]{2, 1, 2, 1});
        assertEquals(9, result.size());
        assertEquals(9, SubsetsTest.normalize(result).stream().distinct().count());
    }
}
