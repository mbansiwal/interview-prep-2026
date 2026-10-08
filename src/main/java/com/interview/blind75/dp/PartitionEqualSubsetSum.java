package com.interview.blind75.dp;

import java.math.BigInteger;

/**
 * ============================================================
 * PROBLEM : Partition Equal Subset Sum
 * LINK    : https://leetcode.com/problems/partition-equal-subset-sum/
 * DIFFICULTY: Medium
 * PATTERN : Dynamic Programming (0/1 Knapsack)
 * ============================================================
 *
 * DESCRIPTION:
 * Given an integer array nums, return true if the array can be partitioned
 * into two subsets such that the sum of the elements in both subsets is equal.
 *
 * EXAMPLES:
 *   Input : nums = [1,5,11,5]  →  Output: true  ([1,5,5] and [11])
 *   Input : nums = [1,2,3,5]   →  Output: false
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 200
 *   - 1 <= nums[i] <= 100
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * Reduction: if the total is odd → false; otherwise ask "can some subset sum to
 * T = total / 2?" (0/1 knapsack). Brute force over all subsets is O(2^n).
 *
 * APPROACH 1: 1D boolean DP, iterate sums downward         → canPartition
 *   1. dp[0] = true; dp[j] = "some subset seen so far sums to j".
 *   2. For each num, for j = T down to num: dp[j] |= dp[j - num].
 *   Intuition: going downward means dp[j - num] still reflects the state BEFORE
 *   this num, so each number is used at most once.
 *   TIME  : O(n · T)    SPACE : O(T)
 *
 * APPROACH 2: Bitset shift                                 → canPartitionBitset
 *   1. Bit j of `reachable` is set when sum j is achievable; start with bit 0.
 *   2. For each num: reachable |= reachable << num.
 *   3. Answer = bit T is set.
 *   Intuition: the shift adds num to EVERY reachable sum at once, word by word.
 *   TIME  : O(n · T / 64) word operations    SPACE : O(T) bits
 *
 * APPROACH 3: Top-down recursion + memo on (index, remaining) → canPartitionMemo
 *   1. can(i, r) = can(i+1, r) || can(i+1, r - nums[i]); r == 0 → true.
 *   2. Cache by (i, r).
 *   Intuition: the "take it or leave it" decision written directly.
 *   TIME  : O(n · T)    SPACE : O(n · T) memo + O(n) recursion stack
 *
 * WHICH TO USE:
 * Approach 1 is the expected answer. Mention the bitset trick as a constant-factor
 * speed-up (~64×) that interviewers like as a follow-up.
 * ============================================================
 */
public class PartitionEqualSubsetSum {

    /** Approach 1 — 1D boolean DP, sums iterated downward. TIME O(n·T) · SPACE O(T) */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }

    /** Approach 2 — Bitset shift. TIME O(n·T/64) · SPACE O(T) */
    public boolean canPartitionBitset(int[] nums) {
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum % 2 != 0) return false;
        BigInteger reachable = BigInteger.ONE; // only sum 0 is reachable
        for (int num : nums) reachable = reachable.or(reachable.shiftLeft(num));
        return reachable.testBit(sum / 2);
    }

    /** Approach 3 — Top-down recursion + memo on (index, remaining). TIME O(n·T) · SPACE O(n·T) */
    public boolean canPartitionMemo(int[] nums) {
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        Boolean[][] memo = new Boolean[nums.length][target + 1];
        return can(nums, 0, target, memo);
    }

    private boolean can(int[] nums, int i, int remaining, Boolean[][] memo) {
        if (remaining == 0) return true;
        if (i == nums.length || remaining < 0) return false;
        if (memo[i][remaining] != null) return memo[i][remaining];
        boolean result = can(nums, i + 1, remaining, memo)
                || can(nums, i + 1, remaining - nums[i], memo);
        return memo[i][remaining] = result;
    }
}
