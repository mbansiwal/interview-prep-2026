package com.interview.blind75.heap;

import java.util.PriorityQueue;

/**
 * ============================================================
 * PROBLEM : Kth Largest Element in a Stream
 * LINK    : https://leetcode.com/problems/kth-largest-element-in-a-stream/
 * DIFFICULTY: Easy
 * PATTERN : Heap
 * ============================================================
 *
 * DESCRIPTION:
 * Design a class to find the kth largest element in a stream.
 * Implement KthLargest(k, nums) and add(val) which returns the kth largest
 * element after adding val to the stream.
 *
 * EXAMPLES:
 *   KthLargest kl = new KthLargest(3, [4,5,8,2]);
 *   kl.add(3) → 4; kl.add(5) → 5; kl.add(10) → 5; kl.add(9) → 8; kl.add(4) → 8
 *
 * CONSTRAINTS:
 *   - 1 <= k <= 10^4
 *   - 0 <= nums.length <= 10^4
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Min-Heap of Size k
 * 1. Maintain a min-heap of size at most k.
 * 2. On add: offer to heap. If size > k, poll (remove smallest).
 * 3. The heap's top (min) is the kth largest overall.
 * Intuition: the heap keeps only the k largest values; the smallest of them is the answer.
 * TIME  : O(n log k) constructor, O(log k) per add
 * SPACE : O(k)
 *
 * ALTERNATIVES: keep a sorted list (binary-search insert) → O(n) per add because
 * of shifting; or re-sort on each add → O(n log n). No materially better alternative.
 *
 * WHICH TO USE:
 * The min-heap of size k — it's the canonical "top k in a stream" pattern.
 * ============================================================
 */
public class KthLargestInStream {

    private final PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private final int k;

    /** Approach 1 — Min-heap of size k. TIME O(n log k) · SPACE O(k) */
    public KthLargestInStream(int k, int[] nums) {
        this.k = k;
        for (int num : nums) add(num);
    }

    /** Approach 1 — Min-heap of size k. TIME O(log k) · SPACE O(1) extra */
    public int add(int val) {
        minHeap.offer(val);
        while (minHeap.size() > k) minHeap.poll();
        return minHeap.peek();
    }
}
