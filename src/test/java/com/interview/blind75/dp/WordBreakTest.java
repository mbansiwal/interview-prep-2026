package com.interview.blind75.dp;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class WordBreakTest {

    private final WordBreak solution = new WordBreak();

    @Test
    void canBreak() { assertTrue(solution.wordBreak("leetcode", List.of("leet", "code"))); }

    @Test
    void canBreakReuse() { assertTrue(solution.wordBreak("applepenapple", List.of("apple", "pen"))); }

    @Test
    void cannotBreak() { assertFalse(solution.wordBreak("catsandog", List.of("cats","dog","sand","and","cat"))); }

    @Test
    void worstCaseRepeatedPrefixesStillFast() {
        String s = "a".repeat(150) + "b";
        assertTimeoutPreemptively(Duration.ofSeconds(1),
                () -> assertFalse(solution.wordBreak(s, List.of("a", "aa", "aaa", "aaaa"))));
    }

    @Test
    void longWordsMixedWithShort() { assertTrue(solution.wordBreak("catsanddog", List.of("cat", "cats", "and", "sand", "dog"))); }
}
