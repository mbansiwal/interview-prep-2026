package com.interview.blind75.stack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MinStackTest {

    @Test
    void basicOperations() {
        MinStack ms = new MinStack();
        ms.push(-2);
        ms.push(0);
        ms.push(-3);
        assertEquals(-3, ms.getMin());
        ms.pop();
        assertEquals(0, ms.top());
        assertEquals(-2, ms.getMin());
    }

    @Test
    void singleElement() {
        MinStack ms = new MinStack();
        ms.push(5);
        assertEquals(5, ms.top());
        assertEquals(5, ms.getMin());
    }

    @Test
    void increasingOrder() {
        MinStack ms = new MinStack();
        ms.push(1);
        ms.push(2);
        ms.push(3);
        assertEquals(1, ms.getMin());
        ms.pop();
        assertEquals(1, ms.getMin());
    }
}
