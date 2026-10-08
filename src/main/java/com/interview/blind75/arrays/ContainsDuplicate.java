package com.interview.blind75.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ============================================================
 * PROBLEM : Contains Duplicate
 * LINK    : https://leetcode.com/problems/contains-duplicate/
 * DIFFICULTY: Easy
 * PATTERN : Arrays & Hashing
 * ============================================================
 *
 * DESCRIPTION:
 * Given an integer array nums, return true if any value appears
 * at least twice in the array, and false if every element is distinct.
 *
 * EXAMPLES:
 *   Input : nums = [1,2,3,1]
 *   Output: true
 *
 *   Input : nums = [1,2,3,4]
 *   Output: false
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 10^5
 *   - -10^9 <= nums[i] <= 10^9
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: HashSet membership  (primary)
 *   1. Walk the array, calling seen.add(num) for each value.
 *   2. Set.add returns false if the value was already present → duplicate.
 *   Intuition: one O(1) hash operation both checks and records each value.
 *   TIME O(n) · SPACE O(n)
 *
 * APPROACH 2: Sort, then compare neighbours
 *   1. Sort the array.
 *   2. Equal values are now adjacent; any nums[i] == nums[i-1] is a duplicate.
 *   Intuition: sorting groups duplicates together, so one linear scan finds them.
 *   TIME O(n log n) · SPACE O(log n) sort stack (mutates input)
 *
 * WHICH TO USE:
 *   Lead with #1 (linear time). Offer #2 if asked to avoid extra memory or
 *   if the input may be modified. Brute force (compare every pair) is O(n²).
 * ============================================================
 */
public class ContainsDuplicate {

    /** Approach 1 — HashSet membership. TIME O(n) · SPACE O(n) */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                return true;
            }
        }
        return false;
    }

    /** Approach 2 — sort then compare neighbours. TIME O(n log n) · SPACE O(log n), mutates input */
    public boolean containsDuplicateSorting(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return true;
        }
        return false;
    }
}
