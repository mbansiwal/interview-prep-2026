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
 * APPROACHES
 * ============================================================
 * APPROACH 1: Two Heaps (this class)
 * 1. lowerMax (max-heap) holds the smaller half; upperMin (min-heap) the larger half.
 * 2. Insert into lowerMax, then move its top across if it's bigger than upperMin's top.
 * 3. Rebalance so lowerMax has the same size as upperMin, or one more.
 * 4. Median = lowerMax top (odd count) or the average of both tops (even count).
 * Intuition: the two heaps are the sorted array split exactly at the median.
 * TIME  : O(log n) addNum, O(1) findMedian
 * SPACE : O(n)
 *
 * APPROACH 2: Counting buckets (CountingMedianFinder) — follow-up "all numbers in [0, 100]"
 * 1. Keep count[v] for every possible value v in a small fixed range.
 * 2. findMedian walks the buckets until it reaches the middle position(s).
 * Intuition: with a tiny value range, a histogram is a sorted array for free.
 * TIME  : O(1) addNum, O(R) findMedian where R = size of the value range
 * SPACE : O(R)
 *
 * ALTERNATIVE (worse): keep a sorted list and binary-search the insert position:
 * O(n) addNum (shifting), O(1) findMedian, O(n) space.
 *
 * WHICH TO USE:
 * Two heaps is the expected answer. Mention counting buckets for the bounded-range
 * follow-up (for "99% of values in [0,100]": buckets plus two overflow structures).
 * ============================================================
 */
public class FindMedianFromDataStream {

    private final PriorityQueue<Integer> lowerMax = new PriorityQueue<>(Collections.reverseOrder());
    private final PriorityQueue<Integer> upperMin = new PriorityQueue<>();

    /** Approach 1 — Two heaps. TIME O(log n) · SPACE O(n) total */
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

    /** Approach 1 — Two heaps. TIME O(1) · SPACE O(1) */
    public double findMedian() {
        if (lowerMax.size() == upperMin.size()) {
            return (lowerMax.peek() + upperMin.peek()) / 2.0;
        }
        return lowerMax.peek();
    }

    /** Approach 2 — Counting buckets for values in [minValue, maxValue]. */
    public static class CountingMedianFinder {
        private final int minValue;
        private final int[] count;
        private int total;

        /** Approach 2 — Counting buckets. TIME O(R) · SPACE O(R) */
        public CountingMedianFinder(int minValue, int maxValue) {
            this.minValue = minValue;
            this.count = new int[maxValue - minValue + 1];
        }

        /** Approach 2 — Counting buckets. TIME O(1) · SPACE O(R) total */
        public void addNum(int num) {
            count[num - minValue]++;
            total++;
        }

        /** Approach 2 — Counting buckets. TIME O(R) · SPACE O(1) */
        public double findMedian() {
            int leftPos = (total - 1) / 2;   // 0-based index of the lower middle
            int rightPos = total / 2;        // equals leftPos when total is odd
            Integer left = null;
            int seen = 0;
            for (int i = 0; i < count.length; i++) {
                seen += count[i];
                if (left == null && seen > leftPos) left = i + minValue;
                if (seen > rightPos) return (left + i + minValue) / 2.0;
            }
            throw new IllegalStateException("findMedian called on an empty stream");
        }
    }
}
