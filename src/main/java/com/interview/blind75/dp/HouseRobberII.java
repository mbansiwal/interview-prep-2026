package com.interview.blind75.dp;

/**
 * ============================================================
 * PROBLEM : House Robber II
 * LINK    : https://leetcode.com/problems/house-robber-ii/
 * DIFFICULTY: Medium
 * PATTERN : Dynamic Programming (1D)
 * ============================================================
 *
 * DESCRIPTION:
 * Same as House Robber but the houses are arranged in a circle (first and last
 * are adjacent). Return the maximum amount you can rob.
 *
 * EXAMPLES:
 *   Input : nums = [2,3,2]  →  Output: 3
 *   Input : nums = [1,2,3,1]  →  Output: 4
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 100
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * Key reduction (both approaches): first and last can't both be robbed, so
 * answer = max(rob houses 0..n-2, rob houses 1..n-1), each a linear House Robber.
 *
 * APPROACH 1: Two linear passes with rolling variables     → rob
 *   1. Run House Robber I on nums[0..n-2] (skip last).
 *   2. Run it on nums[1..n-1] (skip first). Return the max.
 *   Intuition: one of the two ranges always contains the optimal plan.
 *   TIME  : O(n)    SPACE : O(1)
 *
 * APPROACH 2: Two linear passes with dp tables             → robTabulation
 *   1. Same two ranges, but each pass fills a dp array.
 *   2. dp[i] = max(nums[i] + dp[i-2], dp[i-1]) over the range.
 *   Intuition: easier to debug/print; shows the space you're saving in Approach 1.
 *   TIME  : O(n)    SPACE : O(n)
 *
 * WHICH TO USE:
 * Approach 1. The insight interviewers look for is the circular → two-linear
 * reduction; the space optimisation is the same as House Robber I.
 * ============================================================
 */
public class HouseRobberII {

    /** Approach 1 — Two passes, rolling variables. TIME O(n) · SPACE O(1) */
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(robRange(nums, 0, nums.length - 2),
                        robRange(nums, 1, nums.length - 1));
    }

    private int robRange(int[] nums, int start, int end) {
        int prev2 = 0, prev1 = 0;
        for (int i = start; i <= end; i++) {
            int curr = Math.max(nums[i] + prev2, prev1);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    /** Approach 2 — Two passes, dp tables. TIME O(n) · SPACE O(n) */
    public int robTabulation(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(robRangeTable(nums, 0, nums.length - 2),
                        robRangeTable(nums, 1, nums.length - 1));
    }

    private int robRangeTable(int[] nums, int start, int end) {
        int len = end - start + 1;
        int[] dp = new int[len + 1];
        dp[1] = nums[start];
        for (int i = 2; i <= len; i++) {
            dp[i] = Math.max(nums[start + i - 1] + dp[i - 2], dp[i - 1]);
        }
        return dp[len];
    }
}
