package com.interview.blind75.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ValidAnagramApproachesTest {

    static Stream<BiPredicate<String, String>> approaches() {
        ValidAnagram s = new ValidAnagram();
        return Stream.of(s::isAnagram, s::isAnagramSorting, s::isAnagramHashMap);
    }

    @ParameterizedTest @MethodSource("approaches")
    void anagram(BiPredicate<String, String> f) { assertTrue(f.test("anagram", "nagaram")); }

    @ParameterizedTest @MethodSource("approaches")
    void notAnagram(BiPredicate<String, String> f) { assertFalse(f.test("rat", "car")); }

    @ParameterizedTest @MethodSource("approaches")
    void differentLengths(BiPredicate<String, String> f) { assertFalse(f.test("a", "ab")); }

    @ParameterizedTest @MethodSource("approaches")
    void sameLettersDifferentCounts(BiPredicate<String, String> f) { assertFalse(f.test("aab", "abb")); }
}
