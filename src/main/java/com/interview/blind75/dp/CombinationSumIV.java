package com.interview.blind75.dp;

import java.util.Arrays;

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
 * APPROACHES
 * ============================================================
 * Recurrence: count(t) = sum over num <= t of count(t - num); count(0) = 1
 * — "num is the LAST element of the sequence". (n = nums.length, T = target.)
 * Brute force (enumerate every sequence) is exponential.
 *
 * APPROACH 1: Bottom-up DP, amount in the OUTER loop       → combinationSum4
 *   1. dp[0] = 1 (the empty sequence).
 *   2. For t = 1..T, for each num <= t: dp[t] += dp[t - num].
 *   Intuition: every sequence summing to t ends with some num; what precedes it
 *   sums to t - num. Summing over the last element counts each ordering once.
 *   TIME  : O(T · n)    SPACE : O(T)
 *
 * APPROACH 2: Top-down recursion + memo                    → combinationSum4Memo
 *   1. count(0) = 1; count(t) = sum of count(t - num) for num <= t.
 *   2. Cache count(t).
 *   Intuition: identical recurrence; only computes the totals reachable from T.
 *   TIME  : O(T · n)    SPACE : O(T) memo + O(T) recursion stack
 *
 * Contrast with Coin Change II (LC 518): there the COIN loop is outer, so coins
 * are added in a fixed order and each multiset is counted once. Swapping the
 * loops switches between counting orderings (this problem) and multisets (518).
 *
 * FOLLOW-UP: if negative numbers were allowed, the count could be infinite
 * (e.g. +1 and -1 cancel forever), so you'd need a cap on sequence length.
 *
 * WHICH TO USE:
 * Approach 1. Mention that sorting nums lets the inner loop `break` once num > t.
 * ============================================================
 */
public class CombinationSumIV {

    /** Approach 1 — Bottom-up DP over the target. TIME O(T·n) · SPACE O(T) */
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

    /** Approach 2 — Top-down recursion + memo. TIME O(T·n) · SPACE O(T) */
    public int combinationSum4Memo(int[] nums, int target) {
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -1);
        return count(nums, target, memo);
    }

    private int count(int[] nums, int t, int[] memo) {
        if (t == 0) return 1;
        if (memo[t] != -1) return memo[t];
        int total = 0;
        for (int num : nums) {
            if (num <= t) total += count(nums, t - num, memo);
        }
        return memo[t] = total;
    }
}
