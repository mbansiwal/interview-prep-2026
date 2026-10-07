package com.interview.blind75.greedy;

/**
 * ============================================================
 * PROBLEM : Valid Parenthesis String
 * LINK    : https://leetcode.com/problems/valid-parenthesis-string/
 * DIFFICULTY: Medium
 * PATTERN : Greedy
 * ============================================================
 *
 * DESCRIPTION:
 * Given a string s containing '(', ')' and '*', where '*' can be '(', ')' or empty,
 * return true if s is valid.
 *
 * EXAMPLES:
 *   Input : "()"   →  Output: true
 *   Input : "(*)"  →  Output: true
 *   Input : "(*))" →  Output: true
 *
 * CONSTRAINTS:
 *   - 1 <= s.length <= 100
 *
 * ============================================================
 * APPROACH: Greedy — Track Range [minOpen, maxOpen]
 * ============================================================
 * Track the range of possible open-paren counts:
 * - minOpen: minimum possible unmatched '(' (treat '*' as ')')
 * - maxOpen: maximum possible unmatched '(' (treat '*' as '(')
 * For each character:
 * - '(' → both min and max increase by 1
 * - ')' → both decrease by 1
 * - '*' → min decreases, max increases
 * If maxOpen < 0 at any point → too many ')' → return false.
 * Clamp minOpen to 0 (can't have negative unmatched opens).
 * Valid if minOpen == 0 at end.
 *
 * WHY THIS WORKS:
 * If 0 is within [minOpen, maxOpen] at the end, there's a valid assignment of '*'.
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class ValidParenthesisString {

    public boolean checkValidString(String s) {
        int minOpen = 0, maxOpen = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else { // '*'
                minOpen--;
                maxOpen++;
            }
            if (maxOpen < 0) return false;
            minOpen = Math.max(minOpen, 0);
        }
        return minOpen == 0;
    }
}
