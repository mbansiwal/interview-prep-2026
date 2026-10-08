package com.interview.blind75.dp;

/**
 * ============================================================
 * PROBLEM : Palindromic Substrings
 * LINK    : https://leetcode.com/problems/palindromic-substrings/
 * DIFFICULTY: Medium
 * PATTERN : Dynamic Programming / Expand Around Center
 * ============================================================
 *
 * DESCRIPTION:
 * Given a string s, return the number of palindromic substrings in it.
 * A substring is a contiguous sequence of characters within the string.
 *
 * EXAMPLES:
 *   Input : s = "abc"  →  Output: 3  ("a","b","c")
 *   Input : s = "aaa"  →  Output: 6  ("a","a","a","aa","aa","aaa")
 *
 * CONSTRAINTS:
 *   - 1 <= s.length <= 1000
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * Brute force (check every substring) is O(n³).
 *
 * APPROACH 1: Expand around center, count each expansion   → countSubstrings
 *   1. For each of the 2n-1 centers, expand while s[left] == s[right].
 *   2. Every successful expansion is one more palindrome.
 *   Intuition: each palindrome has exactly one center, so nothing is double-counted.
 *   TIME  : O(n²)    SPACE : O(1)
 *
 * APPROACH 2: DP table                                     → countSubstringsDp
 *   1. isPal[i][j] = s[i] == s[j] && (j - i < 3 || isPal[i+1][j-1]).
 *   2. Fill i from right to left; count every true cell.
 *   Intuition: a palindrome is a smaller palindrome with matching ends.
 *   TIME  : O(n²)    SPACE : O(n²)
 *
 * (Manacher's algorithm counts them in O(n) time: sum of (p[i] + 1) / 2 over
 *  the transformed string — see LongestPalindromicSubstring for the code.)
 *
 * WHICH TO USE:
 * Approach 1 — same time, O(1) space, and reuses the LongestPalindromicSubstring idea.
 * ============================================================
 */
public class PalindromicSubstrings {

    /** Approach 1 — Expand around center. TIME O(n²) · SPACE O(1) */
    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += expand(s, i, i);     // odd length
            count += expand(s, i, i + 1); // even length
        }
        return count;
    }

    private int expand(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    /** Approach 2 — DP table of palindromic substrings. TIME O(n²) · SPACE O(n²) */
    public int countSubstringsDp(String s) {
        int n = s.length(), count = 0;
        boolean[][] isPal = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 3 || isPal[i + 1][j - 1])) {
                    isPal[i][j] = true;
                    count++;
                }
            }
        }
        return count;
    }
}
