package com.interview.blind75.dp;

/**
 * ============================================================
 * PROBLEM : Combination Sum IV
 * LINK    : https://leetcode.com/problems/combination-sum-iv/
 * DIFFICULTY: Medium
 * PATTERN : Dynamic Programming (1D, unbounded — ordered)
 * ============================================================
 *
 * DESCRIPTION:
 * Given an array of distinct positive integers nums and a target, return the
 * number of possible combinations that add up to target. Different orders count
 * as different combinations (so this really counts ordered sequences).
 *
 * EXAMPLES:
 *   Input : nums = [1,2,3], target = 4  →  Output: 7
 *           (1,1,1,1) (1,1,2) (1,2,1) (2,1,1) (2,2) (1,3) (3,1)
 *   Input : nums = [9], target = 3      →  Output: 0
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 200, 1 <= nums[i] <= 1000, all distinct
 *   - 1 <= target <= 1000; the answer fits in a 32-bit int
 *
 * ============================================================
 * APPROACH: Bottom-up DP over the target (amount in OUTER loop)
 * ============================================================
 * 1. dp[t] = number of ordered sequences summing to t. dp[0] = 1 (the empty sequence).
 * 2. For each t from 1 to target:
 * 3.   For each num ≤ t: dp[t] += dp[t - num]   ("num is the LAST element").
 * 4. Return dp[target].
 *
 * WHY THIS WORKS:
 * Every sequence summing to t ends with some num, and what comes before it is
 * any sequence summing to t - num. Summing over all possible last numbers counts
 * each ordering exactly once.
 * Contrast with Coin Change II (LC 518): there the COIN loop is outer, so coins
 * are added in a fixed order and each multiset is counted once. Swapping the
 * loops switches between counting orderings (this problem) and multisets (518).
 *
 * FOLLOW-UP: if negative numbers were allowed, the count could be infinite
 * (e.g. +1 and -1 cancel forever), so you'd need a cap on sequence length.
 *
 * TIME  : O(target · n)
 * SPACE : O(target)
 * ============================================================
 */
public class CombinationSumIV {

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int t = 1; t <= target; t++) {
            for (int num : nums) {
                if (num <= t) dp[t] += dp[t - num];
            }
        }
        return dp[target];
    }
}
