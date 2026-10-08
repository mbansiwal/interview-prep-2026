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

class PermutationsTest {

    private static final Permutations solution = new Permutations();

    static Stream<Named<Function<int[], List<List<Integer>>>>> approaches() {
        return Stream.of(
                Named.of("used[] backtracking", solution::permute),
                Named.of("in-place swapping", solution::permuteSwap));
    }

    private static List<List<Integer>> sorted(List<List<Integer>> perms) {
        List<List<Integer>> out = new ArrayList<>(perms);
        out.sort(Comparator.comparing(Object::toString));
        return out;
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void threeElements(Function<int[], List<List<Integer>>> approach) {
        assertEquals(sorted(List.of(List.of(1, 2, 3), List.of(1, 3, 2), List.of(2, 1, 3),
                        List.of(2, 3, 1), List.of(3, 1, 2), List.of(3, 2, 1))),
                sorted(approach.apply(new int[]{1, 2, 3})));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void twoElements(Function<int[], List<List<Integer>>> approach) {
        assertEquals(sorted(List.of(List.of(0, 1), List.of(1, 0))), sorted(approach.apply(new int[]{0, 1})));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleElement(Function<int[], List<List<Integer>>> approach) {
        assertEquals(List.of(List.of(1)), approach.apply(new int[]{1}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void sixElementsAllDistinct(Function<int[], List<List<Integer>>> approach) {
        List<List<Integer>> result = approach.apply(new int[]{1, 2, 3, 4, 5, 6});
        assertEquals(720, result.size());
        assertEquals(720, result.stream().distinct().count());
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void doesNotChangeInput(Function<int[], List<List<Integer>>> approach) {
        int[] nums = {3, 1, 2};
        approach.apply(nums);
        assertArrayEquals(new int[]{3, 1, 2}, nums);
    }
}
