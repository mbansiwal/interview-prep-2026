package com.interview.blind75.dp;

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
 * APPROACH: 0/1 Knapsack — Can We Reach Sum/2?
 * ============================================================
 * If total sum is odd, return false immediately.
 * Target = sum / 2.
 * dp[j] = can we form sum j using a subset of nums?
 * For each num (iterate j from target down to num to avoid reuse):
 *   dp[j] |= dp[j - num]
 *
 * WHY THIS WORKS:
 * Classic 0/1 knapsack: can we pick items to hit exactly target?
 * Iterating j backwards ensures each element is used at most once.
 *
 * TIME  : O(n * target) = O(n * sum/2)
 * SPACE : O(target)
 * ============================================================
 */
public class PartitionEqualSubsetSum {

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
}
