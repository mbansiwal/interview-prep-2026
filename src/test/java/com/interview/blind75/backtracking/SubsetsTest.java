package com.interview.blind75.backtracking;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SubsetsTest {

    private static final Subsets solution = new Subsets();

    static Stream<Named<Function<int[], List<List<Integer>>>>> approaches() {
        return Stream.of(
                Named.of("backtracking", solution::subsets),
                Named.of("iterative", solution::subsetsIterative),
                Named.of("bitmask", solution::subsetsBitmask));
    }

    // Subsets may come back in any order, with elements in any order.
    static List<List<Integer>> normalize(List<List<Integer>> subsets) {
        List<List<Integer>> out = new ArrayList<>();
        for (List<Integer> s : subsets) out.add(s.stream().sorted().toList());
        out.sort(Comparator.comparing(Object::toString));
        return out;
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void threeElements(Function<int[], List<List<Integer>>> approach) {
        assertEquals(normalize(List.of(List.of(), List.of(1), List.of(2), List.of(3),
                        List.of(1, 2), List.of(1, 3), List.of(2, 3), List.of(1, 2, 3))),
                normalize(approach.apply(new int[]{1, 2, 3})));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleElement(Function<int[], List<List<Integer>>> approach) {
        assertEquals(normalize(List.of(List.of(), List.of(0))), normalize(approach.apply(new int[]{0})));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void twoElementsWithNegative(Function<int[], List<List<Integer>>> approach) {
        assertEquals(normalize(List.of(List.of(), List.of(-1), List.of(2), List.of(-1, 2))),
                normalize(approach.apply(new int[]{-1, 2})));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void tenElementsGives1024(Function<int[], List<List<Integer>>> approach) {
        assertEquals(1024, approach.apply(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}).size());
    }
}
