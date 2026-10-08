package com.interview.blind75.bits;

/**
 * ============================================================
 * PROBLEM : Sum of Two Integers
 * LINK    : https://leetcode.com/problems/sum-of-two-integers/
 * DIFFICULTY: Medium
 * PATTERN : Bit Manipulation
 * ============================================================
 *
 * DESCRIPTION:
 * Given two integers a and b, return the sum without using + or - operators.
 *
 * EXAMPLES:
 *   Input : a=1, b=2  →  Output: 3
 *   Input : a=2, b=3  →  Output: 5
 *
 * CONSTRAINTS:
 *   - -1000 <= a, b <= 1000
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * Core identity for both: a + b = (a ^ b) + ((a & b) << 1)
 * — XOR adds without carrying; AND finds where both bits are 1, i.e. the carries,
 * which shift one place left. Repeat until there is no carry.
 * Java ints are 32-bit two's complement, so negatives just work and the carry
 * eventually shifts out of bit 31 — no mask is needed (unlike Python).
 *
 * APPROACH 1: Iterative carry loop                         → getSum
 *   1. While b != 0: carry = (a & b) << 1; a = a ^ b; b = carry.
 *   Intuition: each round moves every carry at least one bit left, so ≤ 32 rounds.
 *   TIME  : O(32) = O(1)    SPACE : O(1)
 *
 * APPROACH 2: Recursive                                    → getSumRecursive
 *   1. getSum(a, b) = b == 0 ? a : getSum(a ^ b, (a & b) << 1).
 *   Intuition: the same identity written as a one-liner.
 *   TIME  : O(32) = O(1)    SPACE : O(32) = O(1) recursion depth
 *
 * WHICH TO USE:
 * Approach 1 — same idea, no call stack. Be ready to walk through one example
 * (e.g. 2 + 3) bit by bit.
 * ============================================================
 */
public class SumOfTwoIntegers {

    /** Approach 1 — Iterative XOR/AND-carry loop. TIME O(1) (≤32 rounds) · SPACE O(1) */
    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }

    /** Approach 2 — Recursive XOR/AND-carry. TIME O(1) (≤32 calls) · SPACE O(1) (≤32 frames) */
    public int getSumRecursive(int a, int b) {
        return b == 0 ? a : getSumRecursive(a ^ b, (a & b) << 1);
    }
}
