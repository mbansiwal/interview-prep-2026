package com.interview.blind75.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PowXNTest {

    private final PowXN solution = new PowXN();

    @Test
    void positivePower() { assertEquals(1024.0, solution.myPow(2.0, 10), 1e-5); }

    @Test
    void negativePower() { assertEquals(0.25, solution.myPow(2.0, -2), 1e-5); }

    @Test
    void powerOfZero() { assertEquals(1.0, solution.myPow(5.0, 0), 1e-5); }

    @Test
    void minIntExponentDoesNotOverflow() {
        // -Integer.MIN_VALUE overflows int; the solution must widen to long first
        assertEquals(1.0, solution.myPow(1.0, Integer.MIN_VALUE), 1e-9);
        assertEquals(0.0, solution.myPow(2.0, Integer.MIN_VALUE), 1e-9);
    }

    @Test
    void negativeBaseOddPower() { assertEquals(-8.0, solution.myPow(-2.0, 3), 1e-9); }
}
