package com.interview.blind75.dp;

/**
 * ============================================================
 * PROBLEM : Min Cost Climbing Stairs
 * LINK    : https://leetcode.com/problems/min-cost-climbing-stairs/
 * DIFFICULTY: Easy
 * PATTERN : Dynamic Programming (1D)
 * ============================================================
 *
 * DESCRIPTION:
 * You are given an integer array cost where cost[i] is the cost of the i-th step.
 * Once you pay, you can climb one or two steps. You can start from index 0 or 1.
 * Return the minimum cost to reach the top of the floor (beyond the last step).
 *
 * EXAMPLES:
 *   Input : cost = [10,15,20]  →  Output: 15  (start at index 1, step 2 to top)
 *   Input : cost = [1,100,1,1,1,100,1,1,100,1]  →  Output: 6
 *
 * CONSTRAINTS:
 *   - 2 <= cost.length <= 1000
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * Recurrence: reach(i) = cost[i] + min(reach(i-1), reach(i-2)); answer is
 * min(reach(n-1), reach(n-2)) because the top is one or two steps past the end.
 *
 * APPROACH 1: Two rolling variables (space-optimised)      → minCostClimbingStairs
 *   1. prev2 = cost[0], prev1 = cost[1].
 *   2. For i = 2..n-1: curr = cost[i] + min(prev1, prev2); shift.
 *   3. Return min(prev1, prev2).
 *   Intuition: each step only depends on the two steps below it.
 *   TIME  : O(n)    SPACE : O(1)
 *
 * APPROACH 2: Bottom-up table                              → minCostTabulation
 *   1. dp[i] = minimum cost to stand on the top of position i (dp[0] = dp[1] = 0,
 *      because you may start on step 0 or 1 for free).
 *   2. dp[i] = min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]); answer dp[n].
 *   Intuition: "cost to arrive" formulation — the top is just position n.
 *   TIME  : O(n)    SPACE : O(n)
 *
 * WHICH TO USE:
 * Approach 1 for submission; Approach 2's "arrive at n" framing is the cleanest
 * to explain on a whiteboard.
 * ============================================================
 */
public class MinCostClimbingStairs {

    /** Approach 1 — Two rolling variables. TIME O(n) · SPACE O(1) */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int prev2 = cost[0], prev1 = cost[1];
        for (int i = 2; i < n; i++) {
            int curr = cost[i] + Math.min(prev1, prev2);
            prev2 = prev1;
            prev1 = curr;
        }
        return Math.min(prev1, prev2);
    }

    /** Approach 2 — Bottom-up table ("cost to arrive at i"). TIME O(n) · SPACE O(n) */
    public int minCostTabulation(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }
}
