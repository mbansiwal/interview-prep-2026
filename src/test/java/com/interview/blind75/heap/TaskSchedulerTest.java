package com.interview.blind75.heap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskSchedulerTest {

    private final TaskScheduler solution = new TaskScheduler();

    @Test
    void withCooldown() {
        assertEquals(8, solution.leastInterval(new char[]{'A','A','A','B','B','B'}, 2));
    }

    @Test
    void noCooldown() {
        assertEquals(6, solution.leastInterval(new char[]{'A','A','A','B','B','B'}, 0));
    }

    @Test
    void manyUnique() {
        assertEquals(9, solution.leastInterval(new char[]{'A','A','A','B','C','D','E','F','G'}, 2));
    }
}
