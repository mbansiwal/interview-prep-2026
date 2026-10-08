package com.interview.blind75.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PalindromicSubstringsTest {

    static Stream<Arguments> approaches() {
        PalindromicSubstrings s = new PalindromicSubstrings();
        return Stream.of(
                Arguments.of("expand center", (ToIntFunction<String>) s::countSubstrings),
                Arguments.of("dp table", (ToIntFunction<String>) s::countSubstringsDp));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void allDistinct(String name, ToIntFunction<String> f) { assertEquals(3, f.applyAsInt("abc")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void allSame(String name, ToIntFunction<String> f) { assertEquals(6, f.applyAsInt("aaa")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void singleChar(String name, ToIntFunction<String> f) { assertEquals(1, f.applyAsInt("a")); }

    // a, b, b, a, bb, abba
    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void evenPalindrome(String name, ToIntFunction<String> f) { assertEquals(6, f.applyAsInt("abba")); }
}
