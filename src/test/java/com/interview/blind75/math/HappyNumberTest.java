package com.interview.blind75.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HappyNumberTest {

    private final HappyNumber solution = new HappyNumber();

    @Test
    void happy19() { assertTrue(solution.isHappy(19)); }

    @Test
    void unhappy2() { assertFalse(solution.isHappy(2)); }

    @Test
    void happy1() { assertTrue(solution.isHappy(1)); }

    @Test
    void happy7() { assertTrue(solution.isHappy(7)); }

    @Test
    void largeUnhappy() { assertFalse(solution.isHappy(Integer.MAX_VALUE)); }
}
