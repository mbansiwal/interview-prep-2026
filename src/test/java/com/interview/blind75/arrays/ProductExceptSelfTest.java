package com.interview.blind75.arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductExceptSelfTest {

    private final ProductExceptSelf solution = new ProductExceptSelf();

    @Test
    void basicExample() {
        assertArrayEquals(new int[]{24, 12, 8, 6}, solution.productExceptSelf(new int[]{1, 2, 3, 4}));
    }

    @Test
    void containsZero() {
        assertArrayEquals(new int[]{0, 0, 9, 0, 0}, solution.productExceptSelf(new int[]{-1, 1, 0, -3, 3}));
    }

    @Test
    void twoElements() {
        assertArrayEquals(new int[]{2, 1}, solution.productExceptSelf(new int[]{1, 2}));
    }
}
