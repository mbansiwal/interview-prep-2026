package com.interview.blind75.slidingwindow;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntBiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LongestRepeatingCharReplacementApproachesTest {

    static Stream<ToIntBiFunction<String, Integer>> approaches() {
        LongestRepeatingCharReplacement s = new LongestRepeatingCharReplacement();
        return Stream.of(s::characterReplacement, s::characterReplacementRecountMax);
    }

    @ParameterizedTest @MethodSource("approaches")
    void example1(ToIntBiFunction<String, Integer> f) { assertEquals(4, f.applyAsInt("ABAB", 2)); }

    @ParameterizedTest @MethodSource("approaches")
    void example2(ToIntBiFunction<String, Integer> f) { assertEquals(4, f.applyAsInt("AABABBA", 1)); }

    @ParameterizedTest @MethodSource("approaches")
    void kZero(ToIntBiFunction<String, Integer> f) { assertEquals(3, f.applyAsInt("ABBBA", 0)); }

    @ParameterizedTest @MethodSource("approaches")
    void kCoversWholeString(ToIntBiFunction<String, Integer> f) { assertEquals(5, f.applyAsInt("ABCDE", 4)); }
}
