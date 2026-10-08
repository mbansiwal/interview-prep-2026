package com.interview.blind75.slidingwindow;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MinWindowSubstringApproachesTest {

    static Stream<BinaryOperator<String>> approaches() {
        MinWindowSubstring s = new MinWindowSubstring();
        return Stream.of(s::minWindow, s::minWindowArray);
    }

    @ParameterizedTest @MethodSource("approaches")
    void example1(BinaryOperator<String> f) { assertEquals("BANC", f.apply("ADOBECODEBANC", "ABC")); }

    @ParameterizedTest @MethodSource("approaches")
    void wholeString(BinaryOperator<String> f) { assertEquals("a", f.apply("a", "a")); }

    @ParameterizedTest @MethodSource("approaches")
    void impossible(BinaryOperator<String> f) { assertEquals("", f.apply("a", "aa")); }

    @ParameterizedTest @MethodSource("approaches")
    void duplicatesInT(BinaryOperator<String> f) { assertEquals("baca", f.apply("acbbaca", "aba")); }

    @ParameterizedTest @MethodSource("approaches")
    void caseSensitive(BinaryOperator<String> f) { assertEquals("Ab", f.apply("aAbB", "bA")); }
}
