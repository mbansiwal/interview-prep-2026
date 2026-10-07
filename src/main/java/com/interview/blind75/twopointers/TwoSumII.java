package com.interview.blind75.twopointers;

/**
 * ============================================================
 * PROBLEM : Two Sum II - Input Array Is Sorted
 * LINK    : https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 * DIFFICULTY: Medium
 * PATTERN : Two Pointers
 * ============================================================
 *
 * DESCRIPTION:
 * Given a 1-indexed sorted integer array, find two numbers that add
 * to target. Return their 1-based indices. Each input has exactly
 * one solution and you may not use the same element twice.
 *
 * EXAMPLES:
 *   Input : numbers = [2,7,11,15], target = 9
 *   Output: [1,2]
 *
 *   Input : numbers = [2,3,4], target = 6
 *   Output: [1,3]
 *
 * CONSTRAINTS:
 *   - 2 <= numbers.length <= 3 * 10^4
 *   - numbers is sorted in non-decreasing order
 *   - Only one valid answer exists
 *
 * ============================================================
 * APPROACH: Two Pointers on Sorted Array
 * ============================================================
 * 1. Start with left=0 (smallest) and right=n-1 (largest).
 * 2. If sum < target, we need a larger value — move left right.
 * 3. If sum > target, we need a smaller value — move right left.
 * 4. If sum == target, return 1-indexed pair.
 *
 * WHY THIS WORKS:
 * Sorted order means moving left increases sum, moving right decreases.
 * We close in on the target without needing extra space.
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class TwoSumII {

    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) return new int[]{l + 1, r + 1};
            else if (sum < target) l++;
            else r--;
        }
        return new int[]{};
    }
}
