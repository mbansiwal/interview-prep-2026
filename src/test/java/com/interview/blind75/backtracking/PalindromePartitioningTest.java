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

class PalindromePartitioningTest {

    private static final PalindromePartitioning solution = new PalindromePartitioning();

    static Stream<Named<Function<String, List<List<String>>>>> approaches() {
        return Stream.of(
                Named.of("two-pointer checks", solution::partition),
                Named.of("dp table", solution::partitionDp));
    }

    private static List<List<String>> sorted(List<List<String>> parts) {
        List<List<String>> out = new ArrayList<>(parts);
        out.sort(Comparator.comparing(Object::toString));
        return out;
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void example1(Function<String, List<List<String>>> approach) {
        assertEquals(sorted(List.of(List.of("a", "a", "b"), List.of("aa", "b"))), sorted(approach.apply("aab")));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleChar(Function<String, List<List<String>>> approach) {
        assertEquals(List.of(List.of("a")), approach.apply("a"));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void allSame(Function<String, List<List<String>>> approach) {
        assertEquals(sorted(List.of(List.of("a", "a", "a"), List.of("a", "aa"), List.of("aa", "a"), List.of("aaa"))),
                sorted(approach.apply("aaa")));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void noMultiCharPalindromes(Function<String, List<List<String>>> approach) {
        assertEquals(List.of(List.of("a", "b", "c")), approach.apply("abc"));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void evenAndOddPalindromes(Function<String, List<List<String>>> approach) {
        assertEquals(sorted(List.of(List.of("a", "b", "b", "a"), List.of("a", "bb", "a"), List.of("abba"))),
                sorted(approach.apply("abba")));
    }
}
