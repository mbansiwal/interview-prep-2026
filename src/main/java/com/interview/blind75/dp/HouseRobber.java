package com.interview.blind75.dp;

import java.util.Arrays;

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
 * APPROACHES
 * ============================================================
 * Recurrence: best(i) = max(nums[i] + best(i-2), best(i-1))
 * — rob house i (and skip i-1) or skip house i.
 * Brute force (try every non-adjacent subset) is O(2^n).
 *
 * APPROACH 1: Two rolling variables (space-optimised)      → rob
 *   1. prev2 = best up to i-2, prev1 = best up to i-1 (both start at 0).
 *   2. For each house: curr = max(num + prev2, prev1); shift the window.
 *   Intuition: the recurrence only reaches two houses back.
 *   TIME  : O(n)    SPACE : O(1)
 *
 * APPROACH 2: Top-down recursion + memo                    → robMemo
 *   1. best(i) = max(nums[i] + best(i-2), best(i-1)), best(<0) = 0.
 *   2. Cache best(i) so each index is solved once.
 *   Intuition: the "rob or skip" decision written directly as recursion.
 *   TIME  : O(n)    SPACE : O(n) memo + O(n) recursion stack
 *
 * APPROACH 3: Bottom-up table                              → robTabulation
 *   1. dp[i] = best using houses 0..i-1; dp[0] = 0, dp[1] = nums[0].
 *   2. dp[i] = max(nums[i-1] + dp[i-2], dp[i-1]).
 *   Intuition: same recurrence filled left to right.
 *   TIME  : O(n)    SPACE : O(n)
 *
 * WHICH TO USE:
 * Explain 2 → 3 → 1 and submit Approach 1. The table version is handy if you're
 * asked to reconstruct WHICH houses were robbed (walk dp backwards).
 * ============================================================
 */
public class HouseRobber {

    /** Approach 1 — Two rolling variables. TIME O(n) · SPACE O(1) */
    public int rob(int[] nums) {
        int prev2 = 0, prev1 = 0;
        for (int num : nums) {
            int curr = Math.max(num + prev2, prev1);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    /** Approach 2 — Top-down recursion + memo. TIME O(n) · SPACE O(n) */
    public int robMemo(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return best(nums, nums.length - 1, memo);
    }

    private int best(int[] nums, int i, int[] memo) {
        if (i < 0) return 0;
        if (memo[i] != -1) return memo[i];
        return memo[i] = Math.max(nums[i] + best(nums, i - 2, memo), best(nums, i - 1, memo));
    }

    /** Approach 3 — Bottom-up table. TIME O(n) · SPACE O(n) */
    public int robTabulation(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(nums[i - 1] + dp[i - 2], dp[i - 1]);
        }
        return dp[n];
    }
}
