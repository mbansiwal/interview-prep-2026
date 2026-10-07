package com.interview.blind75.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ============================================================
 * PROBLEM : Min Stack
 * LINK    : https://leetcode.com/problems/min-stack/
 * DIFFICULTY: Medium
 * PATTERN : Stack
 * ============================================================
 *
 * DESCRIPTION:
 * Design a stack that supports push, pop, top, and retrieving the
 * minimum element in constant time.
 *
 * EXAMPLES:
 *   MinStack minStack = new MinStack();
 *   minStack.push(-2); minStack.push(0); minStack.push(-3);
 *   minStack.getMin(); → -3
 *   minStack.pop();
 *   minStack.top();    → 0
 *   minStack.getMin(); → -2
 *
 * CONSTRAINTS:
 *   - -2^31 <= val <= 2^31 - 1
 *   - pop/top/getMin called on non-empty stack
 *
 * ============================================================
 * APPROACH: Two Stacks — Main + Min Tracker
 * ============================================================
 * 1. Maintain two stacks: main stack and minStack.
 * 2. On push: push val to main. Push min(val, minStack.peek()) to minStack.
 * 3. On pop: pop from both stacks simultaneously.
 * 4. getMin: peek at minStack top (always reflects current minimum).
 *
 * WHY THIS WORKS:
 * minStack mirrors the main stack but each level stores the minimum of
 * all elements from bottom to that point. Pop removes the corresponding
 * minimum level too, keeping them in sync.
 *
 * TIME  : O(1) for all operations
 * SPACE : O(n)
 * ============================================================
 */
public class MinStack {

    private final Deque<Integer> stack = new ArrayDeque<>();
    private final Deque<Integer> minStack = new ArrayDeque<>();

    public void push(int val) {
        stack.push(val);
        int currentMin = minStack.isEmpty() ? val : Math.min(val, minStack.peek());
        minStack.push(currentMin);
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
