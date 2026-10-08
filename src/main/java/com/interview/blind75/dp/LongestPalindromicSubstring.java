package com.interview.blind75.dp;

/**
 * ============================================================
 * PROBLEM : Longest Palindromic Substring
 * LINK    : https://leetcode.com/problems/longest-palindromic-substring/
 * DIFFICULTY: Medium
 * PATTERN : Dynamic Programming / Expand Around Center
 * ============================================================
 *
 * DESCRIPTION:
 * Given a string s, return the longest palindromic substring.
 *
 * EXAMPLES:
 *   Input : s = "babad"  →  Output: "bab" or "aba"
 *   Input : s = "cbbd"   →  Output: "bb"
 *
 * CONSTRAINTS:
 *   - 1 <= s.length <= 1000
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * Brute force (check every substring) is O(n³).
 *
 * APPROACH 1: Expand around center                         → longestPalindrome
 *   1. For each of the 2n-1 centers (a char, or the gap between two chars),
 *      expand outward while s[left] == s[right].
 *   2. Keep the longest span seen.
 *   Intuition: every palindrome is symmetric around some center.
 *   TIME  : O(n²)    SPACE : O(1)
 *
 * APPROACH 2: DP table                                     → longestPalindromeDp
 *   1. isPal[i][j] = s[i] == s[j] && (j - i < 3 || isPal[i+1][j-1]).
 *   2. Fill by increasing i from the end (so isPal[i+1][..] is ready); track the best.
 *   Intuition: a palindrome is a palindrome with matching letters wrapped around it.
 *   TIME  : O(n²)    SPACE : O(n²)
 *
 * APPROACH 3: Manacher's algorithm                         → longestPalindromeManacher
 *   1. Insert '#' between characters (with ^ and $ sentinels) so every palindrome
 *      has odd length.
 *   2. p[i] = radius of the palindrome centered at i. Reuse the mirror p[2c - i]
 *      inside the current rightmost palindrome, then expand only past its edge.
 *   Intuition: the right edge only moves forward, so total expansion work is linear.
 *   TIME  : O(n)    SPACE : O(n)
 *
 * WHICH TO USE:
 * Approach 1 is what interviewers expect (simple, O(1) space). Mention the DP
 * table as the textbook alternative and Manacher as the O(n) follow-up.
 * ============================================================
 */
public class LongestPalindromicSubstring {

    /** Approach 1 — Expand around center. TIME O(n²) · SPACE O(1) */
    public String longestPalindrome(String s) {
        int start = 0, maxLen = 1;
        for (int i = 0; i < s.length(); i++) {
            int odd = expand(s, i, i);
            int even = expand(s, i, i + 1);
            int len = Math.max(odd, even);
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    private int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    /** Approach 2 — DP table of palindromic substrings. TIME O(n²) · SPACE O(n²) */
    public String longestPalindromeDp(String s) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        int start = 0, maxLen = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 3 || isPal[i + 1][j - 1])) {
                    isPal[i][j] = true;
                    if (j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    /** Approach 3 — Manacher's algorithm. TIME O(n) · SPACE O(n) */
    public String longestPalindromeManacher(String s) {
        int n = s.length();
        // t = ^ # s0 # s1 # ... # s(n-1) # $   (sentinels stop expansion at the ends)
        char[] t = new char[2 * n + 3];
        t[0] = '^';
        t[2 * n + 2] = '$';
        for (int i = 0; i < n; i++) {
            t[2 * i + 1] = '#';
            t[2 * i + 2] = s.charAt(i);
        }
        t[2 * n + 1] = '#';

        int[] p = new int[t.length];
        int center = 0, right = 0, bestCenter = 0, bestLen = 0;
        for (int i = 1; i < t.length - 1; i++) {
            if (i < right) p[i] = Math.min(right - i, p[2 * center - i]);
            while (t[i + 1 + p[i]] == t[i - 1 - p[i]]) p[i]++;
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
            if (p[i] > bestLen) {
                bestLen = p[i];
                bestCenter = i;
            }
        }
        int start = (bestCenter - bestLen) / 2;
        return s.substring(start, start + bestLen);
    }
}
