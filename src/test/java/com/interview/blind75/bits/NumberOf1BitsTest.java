package com.interview.blind75.bits;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberOf1BitsTest {

    private final NumberOf1Bits solution = new NumberOf1Bits();

    @Test
    void threeBits() { assertEquals(3, solution.hammingWeight(0b00000000000000000000000000001011)); }

    @Test
    void oneBit() { assertEquals(1, solution.hammingWeight(0b10000000)); }

    @Test
    void zero() { assertEquals(0, solution.hammingWeight(0)); }

    @Test
    void highBitSet() {
        // 0xFFFFFFFD = 11111111111111111111111111111101 (negative as a Java int)
        assertEquals(31, solution.hammingWeight(-3));
        assertEquals(32, solution.hammingWeight(-1));
    }
}
