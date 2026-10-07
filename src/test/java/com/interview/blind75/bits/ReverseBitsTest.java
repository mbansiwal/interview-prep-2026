package com.interview.blind75.bits;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReverseBitsTest {

    private final ReverseBits solution = new ReverseBits();

    @Test
    void example1() { assertEquals(964176192, solution.reverseBits(43261596)); }

    @Test
    void allOnes() { assertEquals(-2, solution.reverseBits(Integer.MAX_VALUE)); }

    @Test
    void zero() { assertEquals(0, solution.reverseBits(0)); }
}
