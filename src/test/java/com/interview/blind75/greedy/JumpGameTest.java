package com.interview.blind75.greedy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JumpGameTest {

    private final JumpGame solution = new JumpGame();

    @Test
    void canReach() { assertTrue(solution.canJump(new int[]{2,3,1,1,4})); }

    @Test
    void cannotReach() { assertFalse(solution.canJump(new int[]{3,2,1,0,4})); }

    @Test
    void singleElement() { assertTrue(solution.canJump(new int[]{0})); }
}
