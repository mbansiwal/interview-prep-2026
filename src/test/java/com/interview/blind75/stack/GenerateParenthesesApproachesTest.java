package com.interview.blind75.stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GenerateParenthesesApproachesTest {

    static Stream<IntFunction<List<String>>> approaches() {
        GenerateParentheses s = new GenerateParentheses();
        return Stream.of(s::generateParenthesis, s::generateParenthesisDp);
    }

    private static Set<String> asSet(List<String> l) {
        Set<String> s = new HashSet<>(l);
        assertEquals(l.size(), s.size(), "no duplicates");
        return s;
    }

    @ParameterizedTest @MethodSource("approaches")
    void n3(IntFunction<List<String>> f) {
        assertEquals(Set.of("((()))", "(()())", "(())()", "()(())", "()()()"), asSet(f.apply(3)));
    }

    @ParameterizedTest @MethodSource("approaches")
    void n1(IntFunction<List<String>> f) { assertEquals(Set.of("()"), asSet(f.apply(1))); }

    @ParameterizedTest @MethodSource("approaches")
    void n5CatalanCount(IntFunction<List<String>> f) { assertEquals(42, asSet(f.apply(5)).size()); }
}
