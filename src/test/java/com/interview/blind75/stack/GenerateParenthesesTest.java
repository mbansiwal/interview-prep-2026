package com.interview.blind75.stack;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GenerateParenthesesTest {

    private final GenerateParentheses solution = new GenerateParentheses();

    @Test
    void n1() {
        assertEquals(List.of("()"), solution.generateParenthesis(1));
    }

    @Test
    void n2() {
        List<String> result = solution.generateParenthesis(2);
        assertEquals(2, result.size());
        assertTrue(result.contains("(())"));
        assertTrue(result.contains("()()"));
    }

    @Test
    void n3CountOnly() {
        assertEquals(5, solution.generateParenthesis(3).size());
    }
}
