package com.interview.blind75.twopointers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ThreeSumApproachesTest {

    static Stream<Function<int[], List<List<Integer>>>> approaches() {
        ThreeSum s = new ThreeSum();
        return Stream.of(s::threeSum, s::threeSumHashSet);
    }

    private static Set<List<Integer>> norm(List<List<Integer>> r) {
        Set<List<Integer>> out = r.stream().map(t -> t.stream().sorted().collect(Collectors.toList())).collect(Collectors.toSet());
        assertEquals(r.size(), out.size(), "no duplicate triplets allowed");
        return out;
    }

    @ParameterizedTest @MethodSource("approaches")
    void example1(Function<int[], List<List<Integer>>> f) {
        assertEquals(Set.of(List.of(-1, -1, 2), List.of(-1, 0, 1)), norm(f.apply(new int[]{-1, 0, 1, 2, -1, -4})));
    }

    @ParameterizedTest @MethodSource("approaches")
    void allZeros(Function<int[], List<List<Integer>>> f) {
        assertEquals(Set.of(List.of(0, 0, 0)), norm(f.apply(new int[]{0, 0, 0, 0})));
    }

    @ParameterizedTest @MethodSource("approaches")
    void noTriplet(Function<int[], List<List<Integer>>> f) {
        assertEquals(Set.of(), norm(f.apply(new int[]{0, 1, 1})));
    }

    @ParameterizedTest @MethodSource("approaches")
    void manyDuplicates(Function<int[], List<List<Integer>>> f) {
        assertEquals(Set.of(List.of(-2, 0, 2), List.of(-2, 1, 1)), norm(f.apply(new int[]{-2, 0, 1, 1, 2, 2, -2})));
    }
}
