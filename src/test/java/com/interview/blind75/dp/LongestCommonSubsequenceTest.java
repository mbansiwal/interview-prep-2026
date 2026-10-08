package com.interview.blind75.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntBiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LongestCommonSubsequenceTest {

    static Stream<Arguments> approaches() {
        LongestCommonSubsequence s = new LongestCommonSubsequence();
        return Stream.of(
                Arguments.of("2D table", (ToIntBiFunction<String, String>) s::longestCommonSubsequence),
                Arguments.of("one row", (ToIntBiFunction<String, String>) s::lcsOneRow),
                Arguments.of("memo", (ToIntBiFunction<String, String>) s::lcsMemo));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example1(String name, ToIntBiFunction<String, String> f) { assertEquals(3, f.applyAsInt("abcde", "ace")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void identical(String name, ToIntBiFunction<String, String> f) { assertEquals(3, f.applyAsInt("abc", "abc")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void noCommon(String name, ToIntBiFunction<String, String> f) { assertEquals(0, f.applyAsInt("abc", "def")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void singleCharMatch(String name, ToIntBiFunction<String, String> f) { assertEquals(1, f.applyAsInt("a", "bab")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void interleaved(String name, ToIntBiFunction<String, String> f) { assertEquals(5, f.applyAsInt("abcba", "abcbcba")); }
}
