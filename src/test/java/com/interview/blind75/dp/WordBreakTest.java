package com.interview.blind75.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WordBreakTest {

    static Stream<Arguments> approaches() {
        WordBreak s = new WordBreak();
        return Stream.of(
                Arguments.of("bottom-up dp", (BiPredicate<String, List<String>>) s::wordBreak),
                Arguments.of("memo dfs", (BiPredicate<String, List<String>>) s::wordBreakMemo),
                Arguments.of("bfs", (BiPredicate<String, List<String>>) s::wordBreakBfs));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void canBreak(String name, BiPredicate<String, List<String>> f) {
        assertTrue(f.test("leetcode", List.of("leet", "code")));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void canBreakReuse(String name, BiPredicate<String, List<String>> f) {
        assertTrue(f.test("applepenapple", List.of("apple", "pen")));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void cannotBreak(String name, BiPredicate<String, List<String>> f) {
        assertFalse(f.test("catsandog", List.of("cats", "dog", "sand", "and", "cat")));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void worstCaseRepeatedPrefixesStillFast(String name, BiPredicate<String, List<String>> f) {
        String s = "a".repeat(150) + "b";
        assertTimeoutPreemptively(Duration.ofSeconds(1),
                () -> assertFalse(f.test(s, List.of("a", "aa", "aaa", "aaaa"))));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void longWordsMixedWithShort(String name, BiPredicate<String, List<String>> f) {
        assertTrue(f.test("catsanddog", List.of("cat", "cats", "and", "sand", "dog")));
    }
}
