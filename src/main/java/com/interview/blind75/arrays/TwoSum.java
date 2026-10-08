package com.interview.blind75.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * PROBLEM : Two Sum
 * LINK    : https://leetcode.com/problems/two-sum/
 * DIFFICULTY: Easy
 * PATTERN : Arrays & Hashing
 * ============================================================
 *
 * DESCRIPTION:
 * Given an array of integers nums and an integer target, return
 * indices of the two numbers such that they add up to target.
 * You may assume exactly one solution exists, and you may not
 * use the same element twice.
 *
 * EXAMPLES:
 *   Input : nums = [2,7,11,15], target = 9
 *   Output: [0,1]
 *
 *   Input : nums = [3,2,4], target = 6
 *   Output: [1,2]
 *
 * CONSTRAINTS:
 *   - 2 <= nums.length <= 10^4
 *   - Exactly one valid answer exists
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: One-pass HashMap complement lookup  (primary)
 *   1. For each nums[i], complement = target − nums[i].
 *   2. If the complement was seen before, return [its index, i].
 *   3. Otherwise record nums[i] → i.
 *   Intuition: trade memory for time — each lookup is O(1) instead of a scan.
 *   TIME O(n) · SPACE O(n)
 *
 * APPROACH 2: Sort indices by value + two pointers
 *   1. Build an array of indices sorted by their values (original array untouched).
 *   2. l = smallest, r = largest; move l right if the sum is too small, r left if too big.
 *   Intuition: same idea as Two Sum II once the values are ordered.
 *   TIME O(n log n) · SPACE O(n) for the index array
 *
 * WHICH TO USE:
 *   #1 is the expected answer. Mention #2 to show the sorted-array connection
 *   (and that it needs the index array because sorting loses positions).
 *   Brute force (check every pair) is O(n²) time, O(1) space.
 * ============================================================
 */
public class TwoSum {

    /** Approach 1 — one-pass HashMap. TIME O(n) · SPACE O(n) */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexByValue = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (indexByValue.containsKey(complement)) {
                return new int[]{indexByValue.get(complement), i};
            }
            indexByValue.put(nums[i], i);
        }
        return new int[]{};
    }

    /** Approach 2 — sort indices by value, then two pointers. TIME O(n log n) · SPACE O(n) */
    public int[] twoSumSortedPointers(int[] nums, int target) {
        Integer[] idx = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> Integer.compare(nums[a], nums[b]));

        int l = 0, r = nums.length - 1;
        while (l < r) {
            int sum = nums[idx[l]] + nums[idx[r]];
            if (sum == target) {
                int a = idx[l], b = idx[r];
                return new int[]{Math.min(a, b), Math.max(a, b)};
            }
            if (sum < target) l++;
            else r--;
        }
        return new int[]{};
    }
}
