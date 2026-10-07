package com.interview.blind75.greedy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JumpGameIITest {

    private final JumpGameII solution = new JumpGameII();

    @Test
    void example1() { assertEquals(2, solution.jump(new int[]{2,3,1,1,4})); }

    @Test
    void example2() { assertEquals(2, solution.jump(new int[]{2,3,0,1,4})); }

    @Test
    void singleElement() { assertEquals(0, solution.jump(new int[]{0})); }
}
