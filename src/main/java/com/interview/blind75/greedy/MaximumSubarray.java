package com.interview.blind75.greedy;

/**
 * ============================================================
 * PROBLEM : Maximum Subarray
 * LINK    : https://leetcode.com/problems/maximum-subarray/
 * DIFFICULTY: Medium
 * PATTERN : Greedy / Kadane's Algorithm
 * ============================================================
 *
 * DESCRIPTION:
 * Given an integer array nums, find the contiguous subarray with the
 * largest sum and return the sum.
 *
 * EXAMPLES:
 *   Input : [-2,1,-3,4,-1,2,1,-5,4]  →  Output: 6 ([4,-1,2,1])
 *   Input : [1]                        →  Output: 1
 *   Input : [5,4,-1,7,8]              →  Output: 23
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 10^5
 *
 * ============================================================
 * APPROACH: Kadane's Algorithm
 * ============================================================
 * Keep a running currentSum. At each element:
 * - If currentSum < 0, reset to 0 (start a fresh subarray).
 * - Add nums[i] to currentSum.
 * - Update maxSum.
 *
 * WHY THIS WORKS:
 * A negative running sum is always a drag — it's better to start fresh.
 * This greedy choice ensures we always extend or restart at the optimal point.
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        int maxSum = nums[0], currentSum = 0;
        for (int num : nums) {
            if (currentSum < 0) currentSum = 0;
            currentSum += num;
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}
