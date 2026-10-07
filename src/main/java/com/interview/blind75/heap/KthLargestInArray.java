package com.interview.blind75.heap;

import java.util.PriorityQueue;

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
 *
 * ============================================================
 * APPROACH: Min-Heap of Size k
 * ============================================================
 * 1. Process all elements through a min-heap of max size k.
 * 2. If heap grows beyond k, poll the smallest.
 * 3. After processing, heap.peek() is the kth largest.
 *
 * WHY THIS WORKS:
 * After processing all elements, the heap contains the k largest values.
 * The smallest among them (heap.peek()) is the kth largest overall.
 *
 * TIME  : O(n log k)
 * SPACE : O(k)
 *
 * FOLLOW-UP: Quickselect — O(n) average, O(n²) worst, O(1) extra space.
 * Partition like quicksort, but recurse only into the side containing the
 * target index (n - k). See findKthLargestQuickselect below.
 * ============================================================
 */
public class KthLargestInArray {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) minHeap.poll();
        }
        return minHeap.peek();
    }

    public int findKthLargestQuickselect(int[] nums, int k) {
        int target = nums.length - k;
        int lo = 0, hi = nums.length - 1;
        java.util.Random random = new java.util.Random(42);
        while (lo < hi) {
            int pivotIndex = partition(nums, lo, hi, lo + random.nextInt(hi - lo + 1));
            if (pivotIndex == target) break;
            if (pivotIndex < target) lo = pivotIndex + 1;
            else hi = pivotIndex - 1;
        }
        return nums[target];
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
