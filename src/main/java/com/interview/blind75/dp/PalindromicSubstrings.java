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
 * APPROACH: Expand Around Center — Count Per Expansion
 * ============================================================
 * For each of the 2n-1 centers:
 * 1. Expand outward as long as s[left] == s[right].
 * 2. Each successful expansion is one palindromic substring.
 *
 * WHY THIS WORKS:
 * Every palindrome has a unique center. Expanding and counting gives all palindromes
 * in O(n) per center. Same technique as LongestPalindromicSubstring but we count
 * instead of tracking max length.
 *
 * TIME  : O(n^2)
 * SPACE : O(1)
 * ============================================================
 */
public class PalindromicSubstrings {

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
}
