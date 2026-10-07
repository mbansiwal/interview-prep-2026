package com.interview.blind75.stack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EvaluateRPNTest {

    private final EvaluateRPN solution = new EvaluateRPN();

    @Test
    void addThenMultiply() {
        assertEquals(9, solution.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
    }

    @Test
    void divisionAndAdd() {
        assertEquals(6, solution.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }

    @Test
    void complexExpression() {
        assertEquals(22, solution.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }

    @Test
    void singleNumber() {
        assertEquals(42, solution.evalRPN(new String[]{"42"}));
    }
}
