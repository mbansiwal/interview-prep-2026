package com.interview.blind75.heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * ============================================================
 * PROBLEM : Find Median from Data Stream
 * LINK    : https://leetcode.com/problems/find-median-from-data-stream/
 * DIFFICULTY: Hard
 * PATTERN : Heap
 * ============================================================
 *
 * DESCRIPTION:
 * The MedianFinder class supports:
 * - addNum(num): add a number from the data stream
 * - findMedian(): return the median of all elements so far
 *
 * EXAMPLES:
 *   addNum(1); addNum(2); findMedian() → 1.5
 *   addNum(3); findMedian() → 2.0
 *
 * CONSTRAINTS:
 *   - -10^5 <= num <= 10^5
 *   - At least one element before calling findMedian
 *
 * ============================================================
 * APPROACH: Two Heaps (Lower Half Max-Heap, Upper Half Min-Heap)
 * ============================================================
 * 1. lowerMax holds the smaller half (max-heap).
 * 2. upperMin holds the larger half (min-heap).
 * 3. Keep sizes balanced: lowerMax.size() == upperMin.size() or +1.
 * 4. Median: if sizes equal → average of both tops; else lowerMax.peek().
 *
 * WHY THIS WORKS:
 * The two heaps together form the sorted array split at the median.
 * Rebalancing after each insert maintains the size invariant in O(log n).
 *
 * TIME  : O(log n) addNum, O(1) findMedian
 * SPACE : O(n)
 * ============================================================
 */
public class FindMedianFromDataStream {

    private final PriorityQueue<Integer> lowerMax = new PriorityQueue<>(Collections.reverseOrder());
    private final PriorityQueue<Integer> upperMin = new PriorityQueue<>();

    public void addNum(int num) {
        lowerMax.offer(num);
        if (!upperMin.isEmpty() && lowerMax.peek() > upperMin.peek()) {
            upperMin.offer(lowerMax.poll());
        }
        if (lowerMax.size() > upperMin.size() + 1) {
            upperMin.offer(lowerMax.poll());
        } else if (upperMin.size() > lowerMax.size()) {
            lowerMax.offer(upperMin.poll());
        }
    }

    public double findMedian() {
        if (lowerMax.size() == upperMin.size()) {
            return (lowerMax.peek() + upperMin.peek()) / 2.0;
        }
        return lowerMax.peek();
    }
}
