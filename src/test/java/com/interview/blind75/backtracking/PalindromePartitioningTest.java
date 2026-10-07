package com.interview.blind75.backtracking;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PalindromePartitioningTest {

    private final PalindromePartitioning solution = new PalindromePartitioning();

    @Test
    void example1() {
        List<List<String>> result = solution.partition("aab");
        assertEquals(2, result.size());
    }

    @Test
    void singleChar() {
        List<List<String>> result = solution.partition("a");
        assertEquals(1, result.size());
        assertEquals(List.of("a"), result.get(0));
    }

    @Test
    void allSame() {
        List<List<String>> result = solution.partition("aaa");
        assertEquals(4, result.size()); // [a,a,a],[a,aa],[aa,a],[aaa]
    }
}
