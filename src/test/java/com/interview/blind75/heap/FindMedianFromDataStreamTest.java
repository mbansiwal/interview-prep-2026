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

    @Test
    void countingBasicMedian() {
        var mf = new FindMedianFromDataStream.CountingMedianFinder(0, 100);
        mf.addNum(1);
        mf.addNum(2);
        assertEquals(1.5, mf.findMedian());
        mf.addNum(3);
        assertEquals(2.0, mf.findMedian());
    }

    @Test
    void countingSingleAndDuplicates() {
        var mf = new FindMedianFromDataStream.CountingMedianFinder(0, 100);
        mf.addNum(5);
        assertEquals(5.0, mf.findMedian());
        mf.addNum(5);
        mf.addNum(100);
        mf.addNum(0);
        assertEquals(5.0, mf.findMedian());
    }

    @Test
    void countingLargeRange() {
        var mf = new FindMedianFromDataStream.CountingMedianFinder(-100000, 100000);
        mf.addNum(100000);
        mf.addNum(-100000);
        assertEquals(0.0, mf.findMedian());
    }

    @Test
    void approachesAgreeOnRandomStream() {
        java.util.Random random = new java.util.Random(7);
        FindMedianFromDataStream heaps = new FindMedianFromDataStream();
        var buckets = new FindMedianFromDataStream.CountingMedianFinder(0, 100);
        for (int i = 0; i < 200; i++) {
            int v = random.nextInt(101);
            heaps.addNum(v);
            buckets.addNum(v);
            assertEquals(heaps.findMedian(), buckets.findMedian());
        }
    }
}
