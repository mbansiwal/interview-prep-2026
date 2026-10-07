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
 * APPROACH: Fast Power (Binary Exponentiation)
 * ============================================================
 * If n is negative: x = 1/x, n = -n (use long to avoid overflow on INT_MIN).
 * Recursively:
 * - If n is even: pow(x*x, n/2)
 * - If n is odd: x * pow(x*x, n/2)
 *
 * WHY THIS WORKS:
 * x^n = (x^2)^(n/2), reducing n by half each step → O(log n) multiplications.
 *
 * TIME  : O(log n)
 * SPACE : O(log n) — recursion stack
 * ============================================================
 */
public class PowXN {

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
}
