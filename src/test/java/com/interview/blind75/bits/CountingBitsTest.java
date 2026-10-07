package com.interview.blind75.bits;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CountingBitsTest {

    private final CountingBits solution = new CountingBits();

    @Test
    void n2() { assertArrayEquals(new int[]{0, 1, 1}, solution.countBits(2)); }

    @Test
    void n5() { assertArrayEquals(new int[]{0, 1, 1, 2, 1, 2}, solution.countBits(5)); }

    @Test
    void n0() { assertArrayEquals(new int[]{0}, solution.countBits(0)); }
}
