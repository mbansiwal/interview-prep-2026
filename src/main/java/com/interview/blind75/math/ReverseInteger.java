package com.interview.blind75.math;

/**
 * ============================================================
 * PROBLEM : Reverse Integer
 * LINK    : https://leetcode.com/problems/reverse-integer/
 * DIFFICULTY: Medium
 * PATTERN : Math (digit manipulation)
 * ============================================================
 *
 * DESCRIPTION:
 * Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes overflow outside the 32-bit signed range, return 0.
 *
 * EXAMPLES:
 *   Input : 123   →  Output: 321
 *   Input : -123  →  Output: -321
 *   Input : 120   →  Output: 21
 *
 * CONSTRAINTS:
 *   - -2^31 <= x <= 2^31 - 1
 *
 * ============================================================
 * APPROACH 1: Pop and push digits, check overflow first   → reverse
 * ============================================================
 * 1. Pop the last digit: digit = x % 10, x /= 10.
 * 2. Before pushing: check if result would overflow 32-bit int.
 * 3. Push digit: result = result * 10 + digit.
 *
 * WHY THIS WORKS:
 * Java silently wraps around on int overflow (no exception), so we must check
 * BEFORE multiplying. We may not use long (problem forbids 64-bit storage).
 * Overflow if result > MAX/10, or result == MAX/10 and digit > 7 (MAX ends in 7);
 * symmetric for negatives with MIN/10 and digit < -8 (MIN ends in 8).
 *
 * TIME  : O(log |x|) — number of digits
 * SPACE : O(1)
 *
 * ALTERNATIVES: accumulating in a long (then range-checking) or reversing the
 * string are also O(log |x|), but both use 64-bit/extra storage the problem rules
 * out — no materially better alternative.
 * ============================================================
 */
public class ReverseInteger {

    /** Approach 1 — Pop/push digits with a pre-multiply overflow check. TIME O(log |x|) · SPACE O(1) */
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) return 0;
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && digit < -8)) return 0;
            result = result * 10 + digit;
        }
        return result;
    }
}
