package com.interview.blind75.dp;

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
 * APPROACH: DP — dp[i] = ways to decode s[0..i-1]
 * ============================================================
 * dp[i] depends on:
 * - Single digit: if s[i-1] != '0', add dp[i-1]
 * - Two digits: if s[i-2..i-1] forms 10..26, add dp[i-2]
 *
 * WHY THIS WORKS:
 * We can take one character (valid if non-zero) or two characters (valid if 10-26).
 * Each choice maps to the corresponding number of ways from earlier in the string.
 *
 * TIME  : O(n)
 * SPACE : O(1) — rolling two variables
 * ============================================================
 */
public class DecodeWays {

    public int numDecodings(String s) {
        int n = s.length();
        int prev2 = 1; // dp[0] = 1 (empty prefix)
        int prev1 = s.charAt(0) != '0' ? 1 : 0; // dp[1]

        for (int i = 2; i <= n; i++) {
            int curr = 0;
            int oneDigit = s.charAt(i - 1) - '0';
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if (oneDigit != 0) curr += prev1;
            if (twoDigit >= 10 && twoDigit <= 26) curr += prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
