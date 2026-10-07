package com.interview.blind75.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * PROBLEM : Generate Parentheses
 * LINK    : https://leetcode.com/problems/generate-parentheses/
 * DIFFICULTY: Medium
 * PATTERN : Stack / Backtracking
 * ============================================================
 *
 * DESCRIPTION:
 * Given n pairs of parentheses, generate all combinations of well-formed
 * parentheses.
 *
 * EXAMPLES:
 *   Input : n = 3
 *   Output: ["((()))","(()())","(())()","()(())","()()()"]
 *
 *   Input : n = 1
 *   Output: ["()"]
 *
 * CONSTRAINTS:
 *   - 1 <= n <= 8
 *
 * ============================================================
 * APPROACH: Backtracking with Open/Close Counters
 * ============================================================
 * 1. Recurse with current string, count of open and close brackets used.
 * 2. Add '(' if open < n (can still open more).
 * 3. Add ')' if close < open (can close only what's been opened).
 * 4. When string length == 2*n, add to result.
 *
 * WHY THIS WORKS:
 * Only two valid moves exist at any point. Tracking open/close counts
 * naturally prunes all invalid states without needing a validator.
 *
 * TIME  : O(4^n / √n · n) — Catalan(n) ≈ 4^n/(n√n) results, each costs O(n)
 *         to build; commonly quoted as O(4^n/√n)
 * SPACE : O(n) recursion depth (output list excluded)
 *
 * NOTE: Grouped under Stack on NeetCode, but the pattern is pure backtracking.
 * ============================================================
 */
public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder sb, int open, int close, int n) {
        if (sb.length() == 2 * n) {
            result.add(sb.toString());
            return;
        }
        if (open < n) {
            sb.append('(');
            backtrack(result, sb, open + 1, close, n);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(')');
            backtrack(result, sb, open, close + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
