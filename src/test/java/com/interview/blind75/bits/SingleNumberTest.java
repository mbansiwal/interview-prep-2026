package com.interview.blind75.bits;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SingleNumberTest {

    private final SingleNumber solution = new SingleNumber();

    @Test
    void example1() { assertEquals(1, solution.singleNumber(new int[]{2, 2, 1})); }

    @Test
    void example2() { assertEquals(4, solution.singleNumber(new int[]{4, 1, 2, 1, 2})); }

    @Test
    void singleElement() { assertEquals(1, solution.singleNumber(new int[]{1})); }
}
