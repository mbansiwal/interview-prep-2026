package com.interview.blind75.bits;

/**
 * ============================================================
 * PROBLEM : Counting Bits
 * LINK    : https://leetcode.com/problems/counting-bits/
 * DIFFICULTY: Easy
 * PATTERN : Bit Manipulation / DP
 * ============================================================
 *
 * DESCRIPTION:
 * Given an integer n, return an array ans of length n+1 where ans[i] is the
 * number of 1's in the binary representation of i.
 *
 * EXAMPLES:
 *   Input : n=2  →  Output: [0,1,1]
 *   Input : n=5  →  Output: [0,1,1,2,1,2]
 *
 * CONSTRAINTS:
 *   - 0 <= n <= 10^5
 *
 * ============================================================
 * APPROACH: DP with Bit Shift
 * ============================================================
 * dp[i] = dp[i >> 1] + (i & 1)
 * i >> 1 is i/2 (drop the last bit), and (i & 1) is the last bit value.
 *
 * WHY THIS WORKS:
 * The number of 1-bits in i = number of 1-bits in i/2 + last bit of i.
 * This gives O(n) without any inner loop.
 *
 * TIME  : O(n)
 * SPACE : O(1) — output array not counted
 * ============================================================
 */
public class CountingBits {

    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }
}
