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
 * APPROACH: Backtracking — Try All Palindromic Prefixes
 * ============================================================
 * 1. At each start index, try all substrings s[start..end].
 * 2. If it is a palindrome, add it to current path and recurse from end+1.
 * 3. Backtrack after recursion.
 * 4. Base case: start == s.length() → add copy of path to result.
 *
 * WHY THIS WORKS:
 * We only recurse when the prefix is a palindrome, ensuring every path
 * in the recursion tree represents a valid partial partitioning.
 *
 * TIME  : O(n * 2^n) — exponential subsets, O(n) palindrome check each
 * SPACE : O(n) — recursion depth
 * ============================================================
 */
public class PalindromePartitioning {

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
}
