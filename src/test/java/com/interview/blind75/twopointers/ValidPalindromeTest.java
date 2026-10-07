package com.interview.blind75.twopointers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidPalindromeTest {

    private final ValidPalindrome solution = new ValidPalindrome();

    @Test
    void isPalindrome() {
        assertTrue(solution.isPalindrome("A man, a plan, a canal: Panama"));
    }

    @Test
    void notPalindrome() {
        assertFalse(solution.isPalindrome("race a car"));
    }

    @Test
    void emptyAfterFilter() {
        assertTrue(solution.isPalindrome(" "));
    }

    @Test
    void singleChar() {
        assertTrue(solution.isPalindrome("a"));
    }
}
