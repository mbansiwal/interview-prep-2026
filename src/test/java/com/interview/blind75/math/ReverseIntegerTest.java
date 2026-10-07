package com.interview.blind75.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReverseIntegerTest {

    private final ReverseInteger solution = new ReverseInteger();

    @Test
    void positive() { assertEquals(321, solution.reverse(123)); }

    @Test
    void negative() { assertEquals(-321, solution.reverse(-123)); }

    @Test
    void trailingZero() { assertEquals(21, solution.reverse(120)); }

    @Test
    void overflow() { assertEquals(0, solution.reverse(1534236469)); }
}
