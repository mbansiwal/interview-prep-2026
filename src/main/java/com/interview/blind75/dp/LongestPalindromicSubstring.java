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
 * APPROACH: Expand Around Center
 * ============================================================
 * For each center position (odd-length: single char, even-length: between two chars):
 * 1. Expand outward while s[left] == s[right].
 * 2. Track the longest palindrome found.
 *
 * WHY THIS WORKS:
 * Every palindrome has a center. By trying all 2n-1 possible centers,
 * we cover all palindromes in O(n) per center → O(n^2) total.
 * More intuitive and equally optimal compared to DP table approach.
 *
 * TIME  : O(n^2)
 * SPACE : O(1)
 * ============================================================
 */
public class LongestPalindromicSubstring {

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
}
