package com.interview.blind75.dp;

/**
 * ============================================================
 * PROBLEM : House Robber
 * LINK    : https://leetcode.com/problems/house-robber/
 * DIFFICULTY: Medium
 * PATTERN : Dynamic Programming (1D)
 * ============================================================
 *
 * DESCRIPTION:
 * Given an integer array nums representing house values, return the maximum
 * amount you can rob without robbing two adjacent houses.
 *
 * EXAMPLES:
 *   Input : nums = [1,2,3,1]  →  Output: 4  (rob 0 and 2)
 *   Input : nums = [2,7,9,3,1]  →  Output: 12  (rob 0, 2, 4)
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 100
 *
 * ============================================================
 * APPROACH: DP with Rolling Variables
 * ============================================================
 * dp[i] = max(rob house i + dp[i-2], skip house i = dp[i-1])
 * Space-optimize: keep only prev2 and prev1.
 *
 * WHY THIS WORKS:
 * At each house, the optimal choice is to rob it (gain nums[i] + best from 2 ago)
 * or skip it (keep best from previous house).
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class HouseRobber {

    public int rob(int[] nums) {
        int prev2 = 0, prev1 = 0;
        for (int num : nums) {
            int curr = Math.max(num + prev2, prev1);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
