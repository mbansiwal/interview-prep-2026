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
 * APPROACH: DP — Cost to Reach Each Step
 * ============================================================
 * dp[i] = min cost to reach step i.
 * dp[i] = cost[i] + min(dp[i-1], dp[i-2])
 * Answer = min(dp[n-1], dp[n-2]).
 *
 * WHY THIS WORKS:
 * At each step, we either arrived from one step back or two steps back,
 * choosing the cheaper option plus this step's cost.
 *
 * TIME  : O(n)
 * SPACE : O(1) — only need prev two values
 * ============================================================
 */
public class MinCostClimbingStairs {

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
}
