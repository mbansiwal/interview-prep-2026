package com.interview.blind75.arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidAnagramTest {

    private final ValidAnagram solution = new ValidAnagram();

    @Test
    void isAnagram() {
        assertTrue(solution.isAnagram("anagram", "nagaram"));
    }

    @Test
    void notAnagram() {
        assertFalse(solution.isAnagram("rat", "car"));
    }

    @Test
    void differentLengths() {
        assertFalse(solution.isAnagram("ab", "abc"));
    }

    @Test
    void singleChar() {
        assertTrue(solution.isAnagram("a", "a"));
    }
}
