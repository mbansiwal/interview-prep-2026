package com.interview.blind75.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LongestPalindromicSubstringTest {

    static Stream<Arguments> approaches() {
        LongestPalindromicSubstring s = new LongestPalindromicSubstring();
        return Stream.of(
                Arguments.of("expand center", (UnaryOperator<String>) s::longestPalindrome),
                Arguments.of("dp table", (UnaryOperator<String>) s::longestPalindromeDp),
                Arguments.of("manacher", (UnaryOperator<String>) s::longestPalindromeManacher));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void oddLength(String name, UnaryOperator<String> f) {
        String result = f.apply("babad");
        assertTrue(result.equals("bab") || result.equals("aba"), result);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void evenLength(String name, UnaryOperator<String> f) { assertEquals("bb", f.apply("cbbd")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void singleChar(String name, UnaryOperator<String> f) { assertEquals("a", f.apply("a")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void wholeString(String name, UnaryOperator<String> f) { assertEquals("racecar", f.apply("racecar")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void palindromeInMiddle(String name, UnaryOperator<String> f) { assertEquals("abccba", f.apply("xyzabccbaq")); }
}
