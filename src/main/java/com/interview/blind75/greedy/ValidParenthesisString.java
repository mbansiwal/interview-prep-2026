package com.interview.blind75.greedy;

import java.util.ArrayDeque;
import java.util.Deque;

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
 * APPROACHES
 * ============================================================
 * Trying all 3^k choices for k stars is exponential; a DP over (index, open count)
 * is O(n²). All three below are O(n).
 *
 * APPROACH 1: Greedy range of possible open counts         → checkValidString
 *   1. minOpen = fewest unmatched '(' possible (stars as ')'),
 *      maxOpen = most possible (stars as '(').
 *   2. '(' → both +1; ')' → both −1; '*' → min −1, max +1.
 *   3. maxOpen < 0 → too many ')' → false. Clamp minOpen at 0.
 *   4. Valid iff minOpen == 0 at the end.
 *   Intuition: every count in [minOpen, maxOpen] is achievable; we need 0 at the end.
 *   TIME  : O(n)    SPACE : O(1)
 *
 * APPROACH 2: Two-pass balance scan                        → checkValidStringTwoPass
 *   1. Left→right treating '*' as '(' — the balance must never drop below 0.
 *   2. Right→left treating '*' as ')' — the reverse balance must never drop below 0.
 *   Intuition: pass 1 proves no ')' lacks a possible partner on its left; pass 2
 *   proves no '(' lacks a possible partner on its right.
 *   TIME  : O(n)    SPACE : O(1)
 *
 * APPROACH 3: Two stacks of indices                        → checkValidStringStacks
 *   1. Push indices of '(' and '*' on separate stacks.
 *   2. On ')': pop a '(' if any, else a '*', else false.
 *   3. At the end, each leftover '(' needs a '*' with a LARGER index to close it.
 *   Intuition: matches the usual parentheses-stack solution, with stars as backups.
 *   TIME  : O(n)    SPACE : O(n)
 *
 * WHICH TO USE:
 * Approach 1 is the slickest; Approach 3 is the easiest to come up with and explain.
 * ============================================================
 */
public class ValidParenthesisString {

    /** Approach 1 — Greedy [minOpen, maxOpen] range. TIME O(n) · SPACE O(1) */
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

    /** Approach 2 — Two-pass balance scan. TIME O(n) · SPACE O(1) */
    public boolean checkValidStringTwoPass(String s) {
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            balance += s.charAt(i) == ')' ? -1 : 1;   // '*' counted as '('
            if (balance < 0) return false;
        }
        balance = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            balance += s.charAt(i) == '(' ? -1 : 1;   // '*' counted as ')'
            if (balance < 0) return false;
        }
        return true;
    }

    /** Approach 3 — Two stacks of indices. TIME O(n) · SPACE O(n) */
    public boolean checkValidStringStacks(String s) {
        Deque<Integer> opens = new ArrayDeque<>(), stars = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') opens.push(i);
            else if (c == '*') stars.push(i);
            else if (!opens.isEmpty()) opens.pop();
            else if (!stars.isEmpty()) stars.pop();
            else return false;
        }
        while (!opens.isEmpty()) {
            if (stars.isEmpty() || stars.pop() < opens.pop()) return false;
        }
        return true;
    }
}
