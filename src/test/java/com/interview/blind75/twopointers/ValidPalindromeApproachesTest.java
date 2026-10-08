package com.interview.blind75.twopointers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ValidPalindromeApproachesTest {

    static Stream<Predicate<String>> approaches() {
        ValidPalindrome s = new ValidPalindrome();
        return Stream.of(s::isPalindrome, s::isPalindromeCleanReverse);
    }

    @ParameterizedTest @MethodSource("approaches")
    void panama(Predicate<String> f) { assertTrue(f.test("A man, a plan, a canal: Panama")); }

    @ParameterizedTest @MethodSource("approaches")
    void raceACar(Predicate<String> f) { assertFalse(f.test("race a car")); }

    @ParameterizedTest @MethodSource("approaches")
    void onlyPunctuation(Predicate<String> f) { assertTrue(f.test(" .,!")); }

    @ParameterizedTest @MethodSource("approaches")
    void digitsMatter(Predicate<String> f) { assertFalse(f.test("0P")); }
}
