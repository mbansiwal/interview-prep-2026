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
 * APPROACH: Run House Robber I Twice (Skip First or Skip Last)
 * ============================================================
 * Since first and last are adjacent, we cannot rob both.
 * Option A: rob among nums[0..n-2] (skip last house).
 * Option B: rob among nums[1..n-1] (skip first house).
 * Answer = max(A, B).
 *
 * WHY THIS WORKS:
 * One of the two options will always contain the optimal solution.
 * We reduce circular to two linear subproblems.
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class HouseRobberII {

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
}
