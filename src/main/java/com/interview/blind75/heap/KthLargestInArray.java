package com.interview.blind75.heap;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * ============================================================
 * PROBLEM : Kth Largest Element in an Array
 * LINK    : https://leetcode.com/problems/kth-largest-element-in-an-array/
 * DIFFICULTY: Medium
 * PATTERN : Heap
 * ============================================================
 *
 * DESCRIPTION:
 * Given an integer array nums and integer k, return the kth largest element.
 * Note: it is the kth largest in sorted order, not the kth distinct element.
 *
 * EXAMPLES:
 *   Input : nums = [3,2,1,5,6,4], k = 2
 *   Output: 5
 *
 *   Input : nums = [3,2,3,1,2,4,5,5,6], k = 4
 *   Output: 4
 *
 * CONSTRAINTS:
 *   - 1 <= k <= nums.length <= 10^5
 *   - -10^4 <= nums[i] <= 10^4
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Min-Heap of Size k
 * 1. Push every element into a min-heap; if it grows beyond k, pop the smallest.
 * 2. At the end the heap holds the k largest values; its top is the kth largest.
 * Intuition: the heap top is the "entry bar" to the top-k club.
 * TIME  : O(n log k)
 * SPACE : O(k)
 *
 * APPROACH 2: Quickselect (Hoare's selection)
 * 1. The kth largest sits at index n - k of the sorted array.
 * 2. Partition around a random pivot; continue only on the side containing n - k.
 * Intuition: quicksort that throws away the half it doesn't need.
 * TIME  : O(n) average, O(n²) worst case (random pivots make it very unlikely)
 * SPACE : O(1) extra — but it reorders (mutates) the input
 *
 * APPROACH 3: Counting sort (bounded values)
 * 1. Count occurrences of each value between min and max.
 * 2. Walk the counts from the largest value down until k elements are passed.
 * Intuition: when values live in a small range, counting beats comparing.
 * TIME  : O(n + R), R = max - min + 1 (≤ 20,001 here)
 * SPACE : O(R)
 *
 * WHICH TO USE:
 * Interviewers usually expect heap first, then quickselect as the O(n) follow-up.
 * Mention counting sort when the value range is small (as in LeetCode's constraints).
 * Plain sorting is O(n log n) — fine as a baseline only.
 * ============================================================
 */
public class KthLargestInArray {

    /** Approach 1 — Min-heap of size k. TIME O(n log k) · SPACE O(k) */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) minHeap.poll();
        }
        return minHeap.peek();
    }

    /** Approach 2 — Quickselect. TIME O(n) avg, O(n²) worst · SPACE O(1) extra (mutates input) */
    public int findKthLargestQuickselect(int[] nums, int k) {
        int target = nums.length - k;
        int lo = 0, hi = nums.length - 1;
        Random random = new Random(42);
        while (lo < hi) {
            int pivotIndex = partition(nums, lo, hi, lo + random.nextInt(hi - lo + 1));
            if (pivotIndex == target) break;
            if (pivotIndex < target) lo = pivotIndex + 1;
            else hi = pivotIndex - 1;
        }
        return nums[target];
    }

    /** Approach 3 — Counting sort. TIME O(n + R) · SPACE O(R), R = max - min + 1 */
    public int findKthLargestCounting(int[] nums, int k) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int[] count = new int[max - min + 1];
        for (int num : nums) count[num - min]++;

        int remaining = k;
        for (int i = count.length - 1; i >= 0; i--) {
            remaining -= count[i];
            if (remaining <= 0) return i + min;
        }
        throw new IllegalArgumentException("k is larger than nums.length");
    }

    // Lomuto partition: values smaller than the pivot end up on its left.
    private int partition(int[] nums, int lo, int hi, int pivotIndex) {
        int pivot = nums[pivotIndex];
        swap(nums, pivotIndex, hi);
        int store = lo;
        for (int i = lo; i < hi; i++) {
            if (nums[i] < pivot) swap(nums, i, store++);
        }
        swap(nums, store, hi);
        return store;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i]; nums[i] = nums[j]; nums[j] = t;
    }
}
