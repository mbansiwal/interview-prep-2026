package com.interview.blind75.dp;

import java.util.Arrays;

/**
 * ============================================================
 * PROBLEM : Longest Increasing Subsequence
 * LINK    : https://leetcode.com/problems/longest-increasing-subsequence/
 * DIFFICULTY: Medium
 * PATTERN : Dynamic Programming / Binary Search (Patience Sort)
 * ============================================================
 *
 * DESCRIPTION:
 * Given an integer array nums, return the length of the longest strictly
 * increasing subsequence.
 *
 * EXAMPLES:
 *   Input : [10,9,2,5,3,7,101,18]  →  Output: 4  ([2,3,7,101])
 *   Input : [0,1,0,3,2,3]          →  Output: 4
 *   Input : [7,7,7,7,7,7,7]        →  Output: 1
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 2500
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * Brute force (every subsequence) is O(2^n).
 *
 * APPROACH 1: Tails array + binary search (patience sort)  → lengthOfLIS
 *   1. tails[k] = smallest possible tail of an increasing subsequence of length k+1.
 *   2. For each num, binary-search the leftmost tails[pos] >= num.
 *   3. Overwrite tails[pos] = num (append when pos == size).
 *   Intuition: a smaller tail can only help future extensions; tails stays sorted,
 *   so binary search applies. Its size is the answer (the array itself is NOT an LIS).
 *   TIME  : O(n log n)    SPACE : O(n)
 *
 * APPROACH 2: Quadratic DP                                 → lengthOfLISQuadratic
 *   1. dp[i] = length of the longest increasing subsequence ENDING at i (start at 1).
 *   2. For each j < i with nums[j] < nums[i]: dp[i] = max(dp[i], dp[j] + 1).
 *   3. Answer = max over dp.
 *   Intuition: an LIS ending at i extends the best LIS ending at a smaller value.
 *   TIME  : O(n²)    SPACE : O(n)
 *
 * WHICH TO USE:
 * Start with Approach 2 (easy to justify, reconstructable via a parent array),
 * then optimise to Approach 1 — that progression is exactly what's expected.
 * ============================================================
 */
public class LongestIncreasingSubsequence {

    /** Approach 1 — Tails array + binary search. TIME O(n log n) · SPACE O(n) */
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int num : nums) {
            int lo = 0, hi = size;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (tails[mid] < num) lo = mid + 1;
                else hi = mid;
            }
            tails[lo] = num;
            if (lo == size) size++;
        }
        return size;
    }

    /** Approach 2 — Quadratic DP (LIS ending at each index). TIME O(n²) · SPACE O(n) */
    public int lengthOfLISQuadratic(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int best = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            best = Math.max(best, dp[i]);
        }
        return best;
    }
}
