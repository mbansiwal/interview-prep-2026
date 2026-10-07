package com.interview.blind75.heap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KthLargestInStreamTest {

    @Test
    void example() {
        KthLargestInStream kl = new KthLargestInStream(3, new int[]{4, 5, 8, 2});
        assertEquals(4, kl.add(3));
        assertEquals(5, kl.add(5));
        assertEquals(5, kl.add(10));
        assertEquals(8, kl.add(9));
        assertEquals(8, kl.add(4));
    }

    @Test
    void emptyInit() {
        KthLargestInStream kl = new KthLargestInStream(1, new int[]{});
        assertEquals(3, kl.add(3));
        assertEquals(3, kl.add(1));
        assertEquals(5, kl.add(5));
    }

    @Test
    void k1() {
        KthLargestInStream kl = new KthLargestInStream(1, new int[]{1, 2, 3});
        assertEquals(4, kl.add(4));
    }
}
