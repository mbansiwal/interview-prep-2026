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
 * APPROACH: XOR for Sum, AND for Carry
 * ============================================================
 * - a ^ b gives the sum without carry (XOR = addition without propagation).
 * - (a & b) << 1 gives the carry bits.
 * - Repeat until carry is 0.
 *
 * WHY THIS WORKS:
 * Binary addition: sum = XOR, carry = AND shifted left. Iterating until
 * no carry remains produces the correct result.
 * Java ints are 32-bit two's complement, so negative numbers just work and the
 * carry eventually shifts out of bit 31 — no mask is needed (unlike Python).
 *
 * TIME  : O(1) — at most 32 iterations for 32-bit integers
 * SPACE : O(1)
 * ============================================================
 */
public class SumOfTwoIntegers {

    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }
}
