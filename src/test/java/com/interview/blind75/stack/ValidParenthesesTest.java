package com.interview.blind75.stack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidParenthesesTest {

    private final ValidParentheses solution = new ValidParentheses();

    @Test
    void allTypes() {
        assertTrue(solution.isValid("()[]{}"));
    }

    @Test
    void nested() {
        assertTrue(solution.isValid("{[()]}"));
    }

    @Test
    void mismatch() {
        assertFalse(solution.isValid("(]"));
    }

    @Test
    void unclosed() {
        assertFalse(solution.isValid("(("));
    }
}
