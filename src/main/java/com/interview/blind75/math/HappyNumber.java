package com.interview.blind75.math;

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
 * APPROACH: Floyd's Tortoise & Hare (no extra memory)
 * ============================================================
 * 1. Treat "n → sumOfSquares(n)" as following a pointer in an implicit linked list.
 * 2. slow moves one step, fast moves two steps.
 * 3. If fast reaches 1 → happy. If slow meets fast (not at 1) → stuck in a cycle → not happy.
 *
 * WHY THIS WORKS:
 * The sequence always ends in a cycle: either the fixed point 1 (1 → 1) or a
 * loop that never contains 1. Like detecting a cycle in a linked list, a fast
 * pointer inside a loop always catches up with the slow one, so we never need
 * to remember the numbers we've seen.
 * (Simpler alternative: store seen numbers in a HashSet — same time, O(log n) space.)
 *
 * TIME  : O(log n) — digit sums shrink quickly to below 243, then cycle within a few steps
 * SPACE : O(1)
 * ============================================================
 */
public class HappyNumber {

    public boolean isHappy(int n) {
        int slow = n;
        int fast = sumOfSquares(n);
        while (fast != 1 && slow != fast) {
            slow = sumOfSquares(slow);
            fast = sumOfSquares(sumOfSquares(fast));
        }
        return fast == 1;
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
