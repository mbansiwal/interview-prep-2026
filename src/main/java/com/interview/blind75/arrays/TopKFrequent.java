package com.interview.blind75.arrays;

import java.util.*;

/**
 * ============================================================
 * PROBLEM : Top K Frequent Elements
 * LINK    : https://leetcode.com/problems/top-k-frequent-elements/
 * DIFFICULTY: Medium
 * PATTERN : Arrays & Hashing
 * ============================================================
 *
 * DESCRIPTION:
 * Given an integer array nums and an integer k, return the k most
 * frequent elements. You may return the answer in any order.
 *
 * EXAMPLES:
 *   Input : nums = [1,1,1,2,2,3], k = 2
 *   Output: [1,2]
 *
 *   Input : nums = [1], k = 1
 *   Output: [1]
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 10^5
 *   - k is in the range [1, number of unique elements]
 *   - The answer is guaranteed to be unique
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Bucket sort by frequency  (primary)
 *   1. Count frequencies in a HashMap.
 *   2. buckets[f] = list of numbers that appear exactly f times (f ≤ n).
 *   3. Walk buckets from high f to low, collecting k numbers.
 *   Intuition: frequencies are bounded by n, so they can index an array — no sort.
 *   TIME O(n) · SPACE O(n)
 *
 * APPROACH 2: Min-heap of size k
 *   1. Count frequencies.
 *   2. Push each distinct number into a min-heap ordered by frequency;
 *      pop whenever the heap grows past k.
 *   3. The heap now holds the k most frequent.
 *   Intuition: the heap keeps only the current top k, evicting the weakest.
 *   TIME O(n log k) · SPACE O(n) map + O(k) heap
 *
 * APPROACH 3: Quickselect on distinct numbers
 *   1. Count frequencies; put the distinct numbers in an array.
 *   2. Partition by frequency (like quicksort) until the k most frequent
 *      occupy the last k slots.
 *   Intuition: we only need the top k in any order, not a full sort.
 *   TIME O(n) average, O(n²) worst · SPACE O(n)
 *
 * WHICH TO USE:
 *   #1 is optimal and simple. #2 is the go-to when data is a stream or k is
 *   small. #3 is the classic follow-up; mention its O(n²) worst case.
 * ============================================================
 */
public class TopKFrequent {

    /** Approach 1 — bucket sort by frequency. TIME O(n) · SPACE O(n) */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) freq.merge(num, 1, Integer::sum);

        @SuppressWarnings("unchecked")
        List<Integer>[] buckets = new List[nums.length + 1];
        freq.forEach((num, count) -> {
            if (buckets[count] == null) buckets[count] = new ArrayList<>();
            buckets[count].add(num);
        });

        int[] result = new int[k];
        int idx = 0;
        for (int i = buckets.length - 1; i >= 0 && idx < k; i--) {
            if (buckets[i] != null) {
                for (int num : buckets[i]) {
                    result[idx++] = num;
                    if (idx == k) break;
                }
            }
        }
        return result;
    }

    /** Approach 2 — min-heap of size k. TIME O(n log k) · SPACE O(n) */
    public int[] topKFrequentHeap(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) freq.merge(num, 1, Integer::sum);

        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(freq::get));
        for (int num : freq.keySet()) {
            heap.offer(num);
            if (heap.size() > k) heap.poll();
        }

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) result[i] = heap.poll();
        return result;
    }

    /** Approach 3 — quickselect on distinct numbers. TIME O(n) avg, O(n²) worst · SPACE O(n) */
    public int[] topKFrequentQuickselect(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) freq.merge(num, 1, Integer::sum);

        int[] unique = freq.keySet().stream().mapToInt(Integer::intValue).toArray();
        int n = unique.length;
        int target = n - k; // everything from index target onward is the answer
        int lo = 0, hi = n - 1;
        Random random = new Random(42);
        while (lo < hi) {
            int p = partition(unique, freq, lo, hi, lo + random.nextInt(hi - lo + 1));
            if (p == target) break;
            if (p < target) lo = p + 1;
            else hi = p - 1;
        }
        return Arrays.copyOfRange(unique, target, n);
    }

    // Lomuto partition by ascending frequency; returns the pivot's final index.
    private int partition(int[] a, Map<Integer, Integer> freq, int lo, int hi, int pivotIdx) {
        int pivotFreq = freq.get(a[pivotIdx]);
        swap(a, pivotIdx, hi);
        int store = lo;
        for (int i = lo; i < hi; i++) {
            if (freq.get(a[i]) < pivotFreq) swap(a, store++, i);
        }
        swap(a, store, hi);
        return store;
    }

    private void swap(int[] a, int i, int j) { int t = a[i]; a[i] = a[j]; a[j] = t; }
}
