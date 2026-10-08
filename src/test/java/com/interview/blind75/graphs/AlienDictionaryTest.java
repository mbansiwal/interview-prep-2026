package com.interview.blind75.graphs;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AlienDictionaryTest {

    private static final AlienDictionary solution = new AlienDictionary();

    static Stream<Named<Function<List<String>, String>>> approaches() {
        return Stream.of(
                Named.of("kahn bfs", solution::alienOrder),
                Named.of("dfs post-order", solution::alienOrderDfs));
    }

    private static void assertConsistent(List<String> words, String order) {
        assertFalse(order.isEmpty());
        for (int i = 0; i + 1 < words.size(); i++) {
            String a = words.get(i), b = words.get(i + 1);
            int j = 0;
            while (j < Math.min(a.length(), b.length()) && a.charAt(j) == b.charAt(j)) j++;
            if (j < Math.min(a.length(), b.length())) {
                assertTrue(order.indexOf(a.charAt(j)) < order.indexOf(b.charAt(j)),
                        a.charAt(j) + " must come before " + b.charAt(j) + " in " + order);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void example1HasUniqueAnswer(Function<List<String>, String> approach) {
        List<String> words = List.of("wrt", "wrf", "er", "ett", "rftt");
        String order = approach.apply(words);
        assertEquals("wertf", order);
        assertConsistent(words, order);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void cycleReturnsEmpty(Function<List<String>, String> approach) {
        assertEquals("", approach.apply(List.of("z", "x", "z")));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void prefixAfterLongerWordIsInvalid(Function<List<String>, String> approach) {
        assertEquals("", approach.apply(List.of("abc", "ab")));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleWordReturnsAllItsLetters(Function<List<String>, String> approach) {
        String order = approach.apply(List.of("zx"));
        assertEquals(2, order.length());
        assertTrue(order.contains("z") && order.contains("x"));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void includesLettersWithNoRules(Function<List<String>, String> approach) {
        List<String> words = List.of("za", "zb", "q");
        String order = approach.apply(words);
        assertEquals(4, order.length());
        assertConsistent(words, order);
    }
}
