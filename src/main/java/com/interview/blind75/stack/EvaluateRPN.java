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
 * APPROACH: Stack-Based Postfix Evaluation
 * ============================================================
 * 1. For each token:
 *    - If it's a number, push to stack.
 *    - If it's an operator, pop two numbers (b first, then a), apply operator, push result.
 * 2. Final answer is the only value remaining on the stack.
 *
 * WHY THIS WORKS:
 * RPN eliminates parentheses — operands always appear before their operator.
 * The stack naturally holds pending operands in the right order.
 *
 * TIME  : O(n)
 * SPACE : O(n)
 * ============================================================
 */
public class EvaluateRPN {

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
}
