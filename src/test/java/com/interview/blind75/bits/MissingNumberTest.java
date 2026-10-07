package com.interview.blind75.bits;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MissingNumberTest {

    private final MissingNumber solution = new MissingNumber();

    @Test
    void missing2() { assertEquals(2, solution.missingNumber(new int[]{3, 0, 1})); }

    @Test
    void missing2Again() { assertEquals(2, solution.missingNumber(new int[]{0, 1})); }

    @Test
    void missingLast() { assertEquals(8, solution.missingNumber(new int[]{9,6,4,2,3,5,7,0,1})); }
}
