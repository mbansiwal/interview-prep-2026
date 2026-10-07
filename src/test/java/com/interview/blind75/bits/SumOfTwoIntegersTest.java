package com.interview.blind75.bits;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SumOfTwoIntegersTest {

    private final SumOfTwoIntegers solution = new SumOfTwoIntegers();

    @Test
    void simple() { assertEquals(3, solution.getSum(1, 2)); }

    @Test
    void negativeNumbers() { assertEquals(-1, solution.getSum(-2, 1)); }

    @Test
    void withZero() { assertEquals(5, solution.getSum(5, 0)); }
}
