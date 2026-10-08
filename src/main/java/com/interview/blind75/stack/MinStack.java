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
 * APPROACHES
 * ============================================================
 * APPROACH 1: Two stacks — values + running minimum  (primary: MinStack)
 *   1. push(v): push v; push min(v, current min) onto minStack.
 *   2. pop(): pop both. getMin(): minStack's top.
 *   Intuition: minStack[i] is the minimum of everything at or below level i.
 *   TIME O(1) every operation · SPACE O(n) — two entries per element
 *
 * APPROACH 2: One stack of (value, minSoFar) pairs  (MinStack.PairStack)
 *   1. Each entry stores its value and the minimum at the time it was pushed.
 *   Intuition: the same information as #1, kept together in one structure.
 *   TIME O(1) every operation · SPACE O(n)
 *
 * APPROACH 3: One stack of differences + a min variable  (MinStack.DiffStack)
 *   1. Store diff = value − min (as long). If diff < 0, the new value is the new min.
 *   2. pop(): if diff < 0, restore min = min − diff (the old min).
 *   3. top(): diff < 0 ? min : min + diff.
 *   Intuition: a negative diff marks "min changed here" and encodes the old min,
 *   so no second stack is needed.
 *   TIME O(1) every operation · SPACE O(n) values, O(1) extra beyond them
 *
 * WHICH TO USE:
 *   #1 or #2 is the expected answer. #3 answers "can you do it with one stack and
 *   O(1) extra space?" — use long to avoid overflow.
 * ============================================================
 */
public class MinStack {

    private final Deque<Integer> stack = new ArrayDeque<>();
    private final Deque<Integer> minStack = new ArrayDeque<>();

    /** Approach 1 — two stacks. TIME O(1) · SPACE O(n) */
    public void push(int val) {
        stack.push(val);
        int currentMin = minStack.isEmpty() ? val : Math.min(val, minStack.peek());
        minStack.push(currentMin);
    }

    /** TIME O(1) · SPACE O(1) */
    public void pop() {
        stack.pop();
        minStack.pop();
    }

    /** TIME O(1) · SPACE O(1) */
    public int top() {
        return stack.peek();
    }

    /** TIME O(1) · SPACE O(1) */
    public int getMin() {
        return minStack.peek();
    }

    /** Approach 2 — one stack of {value, minSoFar} pairs. TIME O(1) per op · SPACE O(n) */
    public static class PairStack {
        private final Deque<int[]> stack = new ArrayDeque<>();

        /** TIME O(1) · SPACE O(1) per element */
        public void push(int val) {
            int min = stack.isEmpty() ? val : Math.min(val, stack.peek()[1]);
            stack.push(new int[]{val, min});
        }

        /** TIME O(1) · SPACE O(1) */
        public void pop() { stack.pop(); }

        /** TIME O(1) · SPACE O(1) */
        public int top() { return stack.peek()[0]; }

        /** TIME O(1) · SPACE O(1) */
        public int getMin() { return stack.peek()[1]; }
    }

    /** Approach 3 — one stack of (value − min) diffs. TIME O(1) per op · SPACE O(n), O(1) extra */
    public static class DiffStack {
        private final Deque<Long> diffs = new ArrayDeque<>();
        private long min;

        /** TIME O(1) · SPACE O(1) per element */
        public void push(int val) {
            if (diffs.isEmpty()) {
                diffs.push(0L);
                min = val;
            } else {
                long diff = val - min;
                diffs.push(diff);
                if (diff < 0) min = val;
            }
        }

        /** TIME O(1) · SPACE O(1) */
        public void pop() {
            long diff = diffs.pop();
            if (diff < 0) min = min - diff; // restore the previous minimum
        }

        /** TIME O(1) · SPACE O(1) */
        public int top() {
            long diff = diffs.peek();
            return (int) (diff < 0 ? min : min + diff);
        }

        /** TIME O(1) · SPACE O(1) */
        public int getMin() { return (int) min; }
    }
}
