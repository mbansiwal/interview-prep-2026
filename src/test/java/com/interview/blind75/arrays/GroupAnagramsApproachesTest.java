package com.interview.blind75.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GroupAnagramsApproachesTest {

    static Stream<Function<String[], List<List<String>>>> approaches() {
        GroupAnagrams s = new GroupAnagrams();
        return Stream.of(s::groupAnagrams, s::groupAnagramsCountKey);
    }

    private static Set<List<String>> normalize(List<List<String>> groups) {
        return groups.stream().map(g -> g.stream().sorted().collect(Collectors.toList())).collect(Collectors.toSet());
    }

    @ParameterizedTest @MethodSource("approaches")
    void standardExample(Function<String[], List<List<String>>> f) {
        Set<List<String>> expected = Set.of(List.of("bat"), List.of("nat", "tan"), List.of("ate", "eat", "tea"));
        assertEquals(expected, normalize(f.apply(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"})));
    }

    @ParameterizedTest @MethodSource("approaches")
    void emptyString(Function<String[], List<List<String>>> f) {
        assertEquals(Set.of(List.of("")), normalize(f.apply(new String[]{""})));
    }

    @ParameterizedTest @MethodSource("approaches")
    void countKeyNotAmbiguous(Function<String[], List<List<String>>> f) {
        // "ab" vs "bb..." style collisions: counts must be separated in the key
        Set<List<String>> expected = Set.of(List.of("bbbbbbbbbbba"), List.of("ab"));
        assertEquals(expected, normalize(f.apply(new String[]{"bbbbbbbbbbba", "ab"})));
    }
}
