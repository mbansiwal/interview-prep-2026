package com.interview.blind75.dp;

import java.util.Arrays;

/**
 * ============================================================
 * PROBLEM : Decode Ways
 * LINK    : https://leetcode.com/problems/decode-ways/
 * DIFFICULTY: Medium
 * PATTERN : Dynamic Programming (1D)
 * ============================================================
 *
 * DESCRIPTION:
 * A string of digits can be decoded: '1'→'A', ..., '26'→'Z'.
 * Given a string s of digits, return the number of ways to decode it.
 *
 * EXAMPLES:
 *   Input : "12"  →  Output: 2  ("AB" or "L")
 *   Input : "226" →  Output: 3  ("BZ","VF","BBF")
 *   Input : "06"  →  Output: 0  (no letter maps to 0)
 *
 * CONSTRAINTS:
 *   - 1 <= s.length <= 100
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * Recurrence: ways(i) = [s[i-1] != '0'] · ways(i-1) + [10 <= s[i-2..i-1] <= 26] · ways(i-2)
 * — the last letter used one digit or two. ways(0) = 1 (empty prefix).
 * Brute force (try every split) is O(2^n).
 *
 * APPROACH 1: Two rolling variables (space-optimised)      → numDecodings
 *   1. prev2 = ways(0) = 1, prev1 = ways(1) = (s[0] != '0').
 *   2. For i = 2..n apply the recurrence and shift.
 *   Intuition: only the previous two prefix counts are ever needed.
 *   TIME  : O(n)    SPACE : O(1)
 *
 * APPROACH 2: Top-down recursion + memo                    → numDecodingsMemo
 *   1. count(i) = ways to decode s[i..]; count(n) = 1; a '0' at i gives 0.
 *   2. count(i) = count(i+1) + (two-digit valid ? count(i+2) : 0); cache it.
 *   Intuition: "decode from here to the end", the most natural recursive framing.
 *   TIME  : O(n)    SPACE : O(n) memo + O(n) recursion stack
 *
 * APPROACH 3: Bottom-up table                              → numDecodingsTabulation
 *   1. dp[0] = 1, dp[1] = (s[0] != '0').
 *   2. dp[i] = recurrence above, for i = 2..n; return dp[n].
 *   Intuition: the same recurrence as Approach 1, kept as an array.
 *   TIME  : O(n)    SPACE : O(n)
 *
 * WHICH TO USE:
 * Talk through 2 or 3 to show the recurrence and the '0' rules, then submit 1.
 * The zero handling ("10" vs "100" vs "06") is what interviewers actually test.
 * ============================================================
 */
public class DecodeWays {

    /** Approach 1 — Two rolling variables. TIME O(n) · SPACE O(1) */
    public int numDecodings(String s) {
        int n = s.length();
        int prev2 = 1; // dp[0] = 1 (empty prefix)
        int prev1 = s.charAt(0) != '0' ? 1 : 0; // dp[1]

        for (int i = 2; i <= n; i++) {
            int curr = 0;
            int oneDigit = s.charAt(i - 1) - '0';
            int twoDigit = (s.charAt(i - 2) - '0') * 10 + oneDigit;
            if (oneDigit != 0) curr += prev1;
            if (twoDigit >= 10 && twoDigit <= 26) curr += prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    /** Approach 2 — Top-down recursion + memo. TIME O(n) · SPACE O(n) */
    public int numDecodingsMemo(String s) {
        int[] memo = new int[s.length() + 1];
        Arrays.fill(memo, -1);
        return count(s, 0, memo);
    }

    private int count(String s, int i, int[] memo) {
        if (i == s.length()) return 1;
        if (s.charAt(i) == '0') return 0;
        if (memo[i] != -1) return memo[i];
        int ways = count(s, i + 1, memo);
        if (i + 1 < s.length()) {
            int twoDigit = (s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0');
            if (twoDigit <= 26) ways += count(s, i + 2, memo);
        }
        return memo[i] = ways;
    }

    /** Approach 3 — Bottom-up table. TIME O(n) · SPACE O(n) */
    public int numDecodingsTabulation(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            int oneDigit = s.charAt(i - 1) - '0';
            int twoDigit = (s.charAt(i - 2) - '0') * 10 + oneDigit;
            if (oneDigit != 0) dp[i] += dp[i - 1];
            if (twoDigit >= 10 && twoDigit <= 26) dp[i] += dp[i - 2];
        }
        return dp[n];
    }
}
