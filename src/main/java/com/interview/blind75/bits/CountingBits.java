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
 * APPROACHES
 * ============================================================
 * Counting each number's bits separately is O(n log n); the follow-up asks for
 * O(n) in one pass. Both DPs reuse an answer for a smaller number.
 * (Space below excludes the n+1 output array, which the problem requires.)
 *
 * APPROACH 1: Drop the last bit                            → countBits
 *   1. dp[i] = dp[i >> 1] + (i & 1).
 *   Intuition: i >> 1 is i without its lowest bit; add that bit back.
 *   TIME  : O(n)    SPACE : O(1) extra
 *
 * APPROACH 2: Drop the lowest SET bit                      → countBitsLowestSetBit
 *   1. dp[i] = dp[i & (i - 1)] + 1.
 *   Intuition: i & (i - 1) clears exactly one 1-bit (the Number of 1 Bits trick),
 *   and the result is always smaller than i, so it's already computed.
 *   TIME  : O(n)    SPACE : O(1) extra
 *
 * WHICH TO USE:
 * Either — both are one line. Approach 2 links nicely to the n & (n-1) trick.
 * ============================================================
 */
public class CountingBits {

    /** Approach 1 — dp[i] = dp[i >> 1] + (i & 1). TIME O(n) · SPACE O(1) extra */
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }

    /** Approach 2 — dp[i] = dp[i & (i-1)] + 1. TIME O(n) · SPACE O(1) extra */
    public int[] countBitsLowestSetBit(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i & (i - 1)] + 1;
        }
        return dp;
    }
}
