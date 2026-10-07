package com.interview.blind75.dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EditDistanceTest {

    private final EditDistance solution = new EditDistance();

    @Test
    void example1() { assertEquals(3, solution.minDistance("horse", "ros")); }

    @Test
    void example2() { assertEquals(5, solution.minDistance("intention", "execution")); }

    @Test
    void emptyStrings() { assertEquals(0, solution.minDistance("", "")); }
}
