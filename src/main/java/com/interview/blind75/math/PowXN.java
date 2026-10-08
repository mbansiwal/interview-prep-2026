package com.interview.blind75.math;

/**
 * ============================================================
 * PROBLEM : Pow(x, n)
 * LINK    : https://leetcode.com/problems/powx-n/
 * DIFFICULTY: Medium
 * PATTERN : Math / Fast Exponentiation
 * ============================================================
 *
 * DESCRIPTION:
 * Implement pow(x, n), which calculates x raised to the power n.
 *
 * EXAMPLES:
 *   Input : x=2.00, n=10   →  Output: 1024.0
 *   Input : x=2.10, n=3    →  Output: 9.261
 *   Input : x=2.00, n=-2   →  Output: 0.25
 *
 * CONSTRAINTS:
 *   - -100.0 < x < 100.0
 *   - n is an integer in [-2^31, 2^31-1]
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * Both: if n < 0, use x = 1/x and n = -n — widened to long first, because
 * -Integer.MIN_VALUE doesn't fit in an int. Multiplying x by itself n times is
 * O(n), up to 2^31 steps — too slow.
 *
 * APPROACH 1: Recursive binary exponentiation              → myPow
 *   1. pow(x, n) = pow(x², n/2), times one extra x when n is odd.
 *   2. Base case: n == 0 → 1.
 *   Intuition: halving the exponent each step gives O(log n) multiplications.
 *   TIME  : O(log n)    SPACE : O(log n) recursion stack
 *
 * APPROACH 2: Iterative binary exponentiation (square-and-multiply) → myPowIterative
 *   1. Walk the bits of n from least significant upward.
 *   2. When the current bit is 1, multiply the result by the current power of x;
 *      square that power every step.
 *   Intuition: x^13 = x^8 · x^4 · x^1, because 13 = 1101 in binary.
 *   TIME  : O(log n)    SPACE : O(1)
 *
 * WHICH TO USE:
 * Either. Approach 1 is easier to derive out loud; Approach 2 removes the stack.
 * The Integer.MIN_VALUE edge case is the part interviewers watch for.
 * ============================================================
 */
public class PowXN {

    /** Approach 1 — Recursive binary exponentiation. TIME O(log n) · SPACE O(log n) */
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) { x = 1 / x; N = -N; }
        return fastPow(x, N);
    }

    private double fastPow(double x, long n) {
        if (n == 0) return 1.0;
        double half = fastPow(x * x, n / 2);
        return n % 2 == 0 ? half : x * half;
    }

    /** Approach 2 — Iterative square-and-multiply over the bits of n. TIME O(log n) · SPACE O(1) */
    public double myPowIterative(double x, int n) {
        long N = n;
        if (N < 0) { x = 1 / x; N = -N; }
        double result = 1.0, power = x;
        while (N > 0) {
            if ((N & 1) == 1) result *= power;
            power *= power;
            N >>= 1;
        }
        return result;
    }
}
