package com.interview.blind75.heap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FindMedianFromDataStreamTest {

    @Test
    void basicMedian() {
        FindMedianFromDataStream mf = new FindMedianFromDataStream();
        mf.addNum(1);
        mf.addNum(2);
        assertEquals(1.5, mf.findMedian());
        mf.addNum(3);
        assertEquals(2.0, mf.findMedian());
    }

    @Test
    void single() {
        FindMedianFromDataStream mf = new FindMedianFromDataStream();
        mf.addNum(5);
        assertEquals(5.0, mf.findMedian());
    }

    @Test
    void largeValues() {
        FindMedianFromDataStream mf = new FindMedianFromDataStream();
        mf.addNum(100000);
        mf.addNum(-100000);
        assertEquals(0.0, mf.findMedian());
    }
}
