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
 * APPROACH: Bucket Sort by Frequency
 * ============================================================
 * 1. Count frequency of each number using a HashMap.
 * 2. Create an array of lists (buckets) indexed by frequency (0..n).
 * 3. Place each number into the bucket matching its frequency.
 * 4. Iterate buckets from high frequency to low, collecting k elements.
 *
 * WHY THIS WORKS:
 * Frequencies range from 1 to n, so we can use the index as a frequency
 * bucket. This avoids sorting and achieves O(n) time overall.
 *
 * TIME  : O(n)
 * SPACE : O(n)
 * ============================================================
 */
public class TopKFrequent {

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
}
