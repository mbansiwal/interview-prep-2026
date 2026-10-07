package com.interview.blind75.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ============================================================
 * PROBLEM : Valid Parentheses
 * LINK    : https://leetcode.com/problems/valid-parentheses/
 * DIFFICULTY: Easy
 * PATTERN : Stack
 * ============================================================
 *
 * DESCRIPTION:
 * Given a string s containing just '(', ')', '{', '}', '[', ']',
 * determine if the input string is valid. Open brackets must be
 * closed by the same type and in the correct order.
 *
 * EXAMPLES:
 *   Input : s = "()"
 *   Output: true
 *
 *   Input : s = "()[]{}"
 *   Output: true
 *
 *   Input : s = "(]"
 *   Output: false
 *
 * CONSTRAINTS:
 *   - 1 <= s.length <= 10^4
 *
 * ============================================================
 * APPROACH: Stack-Based Matching
 * ============================================================
 * 1. For each character in s:
 *    - If opening bracket, push it onto the stack.
 *    - If closing bracket, check if stack top is the matching opener.
 *    - If mismatch or stack empty, return false.
 * 2. At the end, stack must be empty (all brackets matched).
 *
 * WHY THIS WORKS:
 * The stack preserves the LIFO order needed to match nested brackets.
 * Unmatched openers left in the stack signal an invalid string.
 *
 * TIME  : O(n)
 * SPACE : O(n)
 * ============================================================
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }
        return stack.isEmpty();
    }
}
