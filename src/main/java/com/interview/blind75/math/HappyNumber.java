package com.interview.blind75.math;

import java.util.HashSet;
import java.util.Set;

/**
 * ============================================================
 * PROBLEM : Happy Number
 * LINK    : https://leetcode.com/problems/happy-number/
 * DIFFICULTY: Easy
 * PATTERN : Math / Floyd's Cycle Detection
 * ============================================================
 *
 * DESCRIPTION:
 * Starting with a positive integer, repeatedly replace the number with the sum
 * of the squares of its digits. If this reaches 1 the number is happy; if it
 * loops forever without reaching 1 it is not. Return true if n is happy.
 *
 * EXAMPLES:
 *   Input : 19  →  Output: true  (1²+9²=82 → 8²+2²=68 → 6²+8²=100 → 1²+0²+0²=1)
 *   Input : 2   →  Output: false (2 → 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4 ...)
 *
 * CONSTRAINTS:
 *   - 1 <= n <= 2^31 - 1
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * The sequence always ends in a cycle: either the fixed point 1 (1 → 1) or a loop
 * that never contains 1. Both approaches detect which. Once n < 1000 every value
 * stays below 243, so only O(log n) distinct numbers are ever visited.
 *
 * APPROACH 1: Floyd's tortoise & hare                      → isHappy
 *   1. Treat n → sumOfSquares(n) as following a pointer in an implicit linked list.
 *   2. slow moves one step, fast moves two.
 *   3. fast reaches 1 → happy; slow meets fast elsewhere → cycle without 1 → not happy.
 *   Intuition: a fast pointer inside a loop always catches the slow one.
 *   TIME  : O(log n)    SPACE : O(1)
 *
 * APPROACH 2: HashSet of seen numbers                      → isHappyHashSet
 *   1. Keep stepping; stop at 1 (happy) or at a number already in the set (cycle).
 *   Intuition: the most direct "have I been here before?" check.
 *   TIME  : O(log n)    SPACE : O(log n)
 *
 * WHICH TO USE:
 * Approach 2 is the obvious first answer; offer Floyd (Approach 1) when asked to
 * drop the extra memory — that's the follow-up interviewers look for.
 * ============================================================
 */
public class HappyNumber {

    /** Approach 1 — Floyd's tortoise & hare. TIME O(log n) · SPACE O(1) */
    public boolean isHappy(int n) {
        int slow = n;
        int fast = sumOfSquares(n);
        while (fast != 1 && slow != fast) {
            slow = sumOfSquares(slow);
            fast = sumOfSquares(sumOfSquares(fast));
        }
        return fast == 1;
    }

    /** Approach 2 — HashSet of seen numbers. TIME O(log n) · SPACE O(log n) */
    public boolean isHappyHashSet(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && seen.add(n)) n = sumOfSquares(n);
        return n == 1;
    }

    private int sumOfSquares(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }
}
