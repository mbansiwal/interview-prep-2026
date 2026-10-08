package com.interview.blind75.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ============================================================
 * PROBLEM : Evaluate Reverse Polish Notation
 * LINK    : https://leetcode.com/problems/evaluate-reverse-polish-notation/
 * DIFFICULTY: Medium
 * PATTERN : Stack
 * ============================================================
 *
 * DESCRIPTION:
 * Evaluate an arithmetic expression in Reverse Polish Notation (postfix).
 * Valid operators: +, -, *, /. Integer division truncates toward zero.
 *
 * EXAMPLES:
 *   Input : tokens = ["2","1","+","3","*"]
 *   Output: 9   ((2+1)*3)
 *
 *   Input : tokens = ["4","13","5","/","+"]
 *   Output: 6   (4+(13/5))
 *
 * CONSTRAINTS:
 *   - 1 <= tokens.length <= 10^4
 *   - Division by zero will not occur
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Stack of operands (Deque)  (primary)
 *   1. Number → push. Operator → pop b, pop a, push (a op b).
 *   2. The final stack top is the answer.
 *   Intuition: in postfix, an operator always applies to the two most recent values.
 *   TIME O(n) · SPACE O(n)
 *
 * APPROACH 2: int[] used as the stack
 *   1. Same algorithm, with an int array and a top index.
 *   Intuition: avoids boxing every Integer; the stack never exceeds the token count.
 *   TIME O(n) · SPACE O(n)
 *
 * WHICH TO USE:
 *   #1 in interviews; #2 if asked to optimise constants. Watch operand order for
 *   '−' and '/', and that Java's '/' truncates toward zero as the problem requires.
 * ============================================================
 */
public class EvaluateRPN {

    /** Approach 1 — Deque stack. TIME O(n) · SPACE O(n) */
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens) {
            switch (token) {
                case "+" -> { int b = stack.pop(); stack.push(stack.pop() + b); }
                case "-" -> { int b = stack.pop(); stack.push(stack.pop() - b); }
                case "*" -> { int b = stack.pop(); stack.push(stack.pop() * b); }
                case "/" -> { int b = stack.pop(); stack.push(stack.pop() / b); }
                default  -> stack.push(Integer.parseInt(token));
            }
        }
        return stack.peek();
    }

    /** Approach 2 — int[] as the stack (no boxing). TIME O(n) · SPACE O(n) */
    public int evalRPNArrayStack(String[] tokens) {
        int[] stack = new int[tokens.length];
        int top = 0;
        for (String token : tokens) {
            switch (token) {
                case "+" -> { int b = stack[--top]; stack[top - 1] += b; }
                case "-" -> { int b = stack[--top]; stack[top - 1] -= b; }
                case "*" -> { int b = stack[--top]; stack[top - 1] *= b; }
                case "/" -> { int b = stack[--top]; stack[top - 1] /= b; }
                default  -> stack[top++] = Integer.parseInt(token);
            }
        }
        return stack[0];
    }
}
