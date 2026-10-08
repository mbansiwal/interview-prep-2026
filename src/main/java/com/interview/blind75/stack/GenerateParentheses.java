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
 * APPROACHES
 * ============================================================
 * APPROACH 1: Backtracking with open/close counters  (primary)
 *   1. Build the string one char at a time.
 *   2. Add '(' if open < n; add ')' if close < open.
 *   3. When length == 2n, record it.
 *   Intuition: the two rules allow exactly the valid prefixes, so nothing is
 *   ever generated and thrown away.
 *   TIME O(4ⁿ/√n · n) — Catalan(n) results, each O(n) to build · SPACE O(n) recursion (output excluded)
 *
 * APPROACH 2: Dynamic programming by "closure number"
 *   1. Every valid string is "(" + A + ")" + B, where A has c pairs and B has n−1−c.
 *   2. Build dp[0..n] bottom-up: dp[0] = [""], dp[k] from all splits c = 0..k−1.
 *   Intuition: the first '(' closes somewhere; whatever is inside and after are
 *   smaller valid strings.
 *   TIME O(4ⁿ/√n · n) — same output size · SPACE O(4ⁿ/√n · n) — keeps all dp[k] lists
 *
 * WHICH TO USE:
 *   #1 is expected (backtracking is the point of the problem). #2 is a nice
 *   alternative proof of structure; it trades memory for no recursion.
 *   Brute force (all 2^(2n) strings, then validate) is O(2^(2n) · n).
 * ============================================================
 */
public class GenerateParentheses {

    /** Approach 1 — backtracking with open/close counts. TIME O(4ⁿ/√n · n) · SPACE O(n) recursion */
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

    /** Approach 2 — DP by closure number: "(" + A + ")" + B. TIME O(4ⁿ/√n · n) · SPACE O(4ⁿ/√n · n) */
    public List<String> generateParenthesisDp(int n) {
        List<List<String>> dp = new ArrayList<>();
        dp.add(List.of(""));
        for (int k = 1; k <= n; k++) {
            List<String> current = new ArrayList<>();
            for (int c = 0; c < k; c++) {
                for (String inside : dp.get(c)) {
                    for (String after : dp.get(k - 1 - c)) {
                        current.add("(" + inside + ")" + after);
                    }
                }
            }
            dp.add(current);
        }
        return dp.get(n);
    }
}
