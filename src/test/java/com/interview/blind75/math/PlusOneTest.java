package com.interview.blind75.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlusOneTest {

    private final PlusOne solution = new PlusOne();

    @Test
    void simple() { assertArrayEquals(new int[]{1,2,4}, solution.plusOne(new int[]{1,2,3})); }

    @Test
    void allNines() { assertArrayEquals(new int[]{1,0,0,0}, solution.plusOne(new int[]{9,9,9})); }

    @Test
    void singleDigit() { assertArrayEquals(new int[]{1}, solution.plusOne(new int[]{0})); }
}
