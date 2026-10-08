package com.interview.blind75.slidingwindow;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LongestSubstringWithoutRepeatingApproachesTest {

    static Stream<ToIntFunction<String>> approaches() {
        LongestSubstringWithoutRepeating s = new LongestSubstringWithoutRepeating();
        return Stream.of(s::lengthOfLongestSubstring, s::lengthOfLongestSubstringArray, s::lengthOfLongestSubstringSet);
    }

    @ParameterizedTest @MethodSource("approaches")
    void abcabcbb(ToIntFunction<String> f) { assertEquals(3, f.applyAsInt("abcabcbb")); }

    @ParameterizedTest @MethodSource("approaches")
    void allSame(ToIntFunction<String> f) { assertEquals(1, f.applyAsInt("bbbbb")); }

    @ParameterizedTest @MethodSource("approaches")
    void pwwkew(ToIntFunction<String> f) { assertEquals(3, f.applyAsInt("pwwkew")); }

    @ParameterizedTest @MethodSource("approaches")
    void empty(ToIntFunction<String> f) { assertEquals(0, f.applyAsInt("")); }

    @ParameterizedTest @MethodSource("approaches")
    void repeatBeforeWindowIgnored(ToIntFunction<String> f) { assertEquals(5, f.applyAsInt("abba cd")); }
}
