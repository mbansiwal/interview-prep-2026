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
 * APPROACHES
 * ============================================================
 * APPROACH 1: Stack of opening brackets  (primary)
 *   1. Push every opening bracket.
 *   2. On a closing bracket, the stack top must be its matching opener; pop it.
 *   3. Valid iff the stack is empty at the end.
 *   Intuition: the most recently opened bracket must close first (LIFO).
 *   TIME O(n) · SPACE O(n)
 *
 * APPROACH 2: char[] stack of expected closers
 *   1. On an opener, push the closer we now expect: '(' → ')', etc.
 *   2. On a closer, it must equal the top; pop.
 *   3. Early exit: if the stack ever holds more than the remaining characters,
 *      it can't be closed — return false.
 *   Intuition: storing the expected closer removes the matching table; a plain
 *   array avoids boxing.
 *   TIME O(n) · SPACE O(n)
 *
 * WHICH TO USE:
 *   Both are optimal. #1 is the classic; #2 is a tidy optimisation to mention.
 *   A counter alone only works when there's a single bracket type.
 * ============================================================
 */
public class ValidParentheses {

    /** Approach 1 — Deque of opening brackets. TIME O(n) · SPACE O(n) */
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

    /** Approach 2 — char[] stack of expected closers. TIME O(n) · SPACE O(n) */
    public boolean isValidExpectedCloser(String s) {
        char[] stack = new char[s.length()];
        int top = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(' -> stack[top++] = ')';
                case '{' -> stack[top++] = '}';
                case '[' -> stack[top++] = ']';
                default -> {
                    if (top == 0 || stack[--top] != c) return false;
                }
            }
            if (top > s.length() - 1 - i) return false; // not enough chars left to close
        }
        return top == 0;
    }
}
