package com.interview.blind75.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * PROBLEM : Palindrome Partitioning
 * LINK    : https://leetcode.com/problems/palindrome-partitioning/
 * DIFFICULTY: Medium
 * PATTERN : Backtracking
 * ============================================================
 *
 * DESCRIPTION:
 * Given a string s, partition s such that every substring of the partition
 * is a palindrome. Return all possible palindrome partitioning of s.
 *
 * EXAMPLES:
 *   Input : s = "aab"
 *   Output: [["a","a","b"],["aa","b"]]
 *
 *   Input : s = "a"
 *   Output: [["a"]]
 *
 * CONSTRAINTS:
 *   - 1 <= s.length <= 16
 *   - s contains only lowercase letters
 *
 * ============================================================
 * APPROACHES  (n = s.length(); there can be up to 2^(n-1) partitions)
 * ============================================================
 * APPROACH 1: Backtracking with two-pointer palindrome checks
 * 1. From `start`, try every end; if s[start..end] is a palindrome, take it and recurse from end + 1.
 * 2. When start reaches the end of the string, save the partition.
 * Intuition: only palindromic prefixes are ever explored, so every leaf is a valid answer.
 * TIME  : O(n * 2^n) — up to 2^n partitions, O(n) check/copy work per step
 * SPACE : O(n) recursion + current path (excluding output)
 *
 * APPROACH 2: Backtracking + precomputed palindrome table (DP)
 * 1. Fill isPal[i][j] = s[i]==s[j] && (j - i < 2 || isPal[i+1][j-1]), from the bottom row up.
 * 2. Same backtracking, but each palindrome test is an O(1) table lookup.
 * Intuition: the same substrings get re-checked across many branches — compute each once.
 * TIME  : O(n²) table + O(n * 2^n) backtracking (same worst case, much less repeated work)
 * SPACE : O(n²) table + O(n) recursion
 *
 * WHICH TO USE:
 * Start with Approach 1, then offer the DP table as the optimisation — it's the standard follow-up
 * and the same table solves Palindrome Partitioning II (min cuts).
 * ============================================================
 */
public class PalindromePartitioning {

    /** Approach 1 — Backtracking + two-pointer checks. TIME O(n * 2^n) · SPACE O(n) (excluding output) */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, List<String> current,
                           List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                current.add(s.substring(start, end + 1));
                backtrack(s, end + 1, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }

    /** Approach 2 — Backtracking + DP palindrome table. TIME O(n * 2^n) · SPACE O(n²) */
    public List<List<String>> partitionDp(String s) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                isPal[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || isPal[i + 1][j - 1]);
            }
        }
        List<List<String>> result = new ArrayList<>();
        backtrackWithTable(s, 0, isPal, new ArrayList<>(), result);
        return result;
    }

    private void backtrackWithTable(String s, int start, boolean[][] isPal,
                                    List<String> current, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int end = start; end < s.length(); end++) {
            if (!isPal[start][end]) continue;
            current.add(s.substring(start, end + 1));
            backtrackWithTable(s, end + 1, isPal, current, result);
            current.remove(current.size() - 1);
        }
    }
}
