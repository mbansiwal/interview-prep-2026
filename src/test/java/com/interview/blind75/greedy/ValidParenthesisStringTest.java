package com.interview.blind75.greedy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidParenthesisStringTest {

    private final ValidParenthesisString solution = new ValidParenthesisString();

    @Test
    void simple() { assertTrue(solution.checkValidString("()")); }

    @Test
    void withStar() { assertTrue(solution.checkValidString("(*)")); }

    @Test
    void starAsClose() { assertTrue(solution.checkValidString("(*))")); }

    @Test
    void invalid() { assertFalse(solution.checkValidString("(((")); }
}
