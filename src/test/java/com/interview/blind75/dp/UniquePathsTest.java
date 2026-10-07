package com.interview.blind75.dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UniquePathsTest {

    private final UniquePaths solution = new UniquePaths();

    @Test
    void example1() { assertEquals(28, solution.uniquePaths(3, 7)); }

    @Test
    void example2() { assertEquals(3, solution.uniquePaths(3, 2)); }

    @Test
    void singleRow() { assertEquals(1, solution.uniquePaths(1, 5)); }
}
