package com.interview.blind75.bits;

/**
 * ============================================================
 * PROBLEM : Number of 1 Bits
 * LINK    : https://leetcode.com/problems/number-of-1-bits/
 * DIFFICULTY: Easy
 * PATTERN : Bit Manipulation
 * ============================================================
 *
 * DESCRIPTION:
 * Write a function that takes an unsigned integer and returns the number of
 * '1' bits it has (also called the Hamming weight).
 *
 * EXAMPLES:
 *   Input : 00000000000000000000000000001011  →  Output: 3
 *   Input : 00000000000000000000000010000000  →  Output: 1
 *   Input : 11111111111111111111111111111101  →  Output: 31
 *
 * ============================================================
 * APPROACH: n & (n-1) Trick — Remove Lowest Set Bit
 * ============================================================
 * n & (n-1) clears the lowest set bit of n.
 * Count how many times we can do this before n becomes 0.
 *
 * WHY THIS WORKS:
 * n-1 flips all bits from the lowest set bit down. ANDing with n clears
 * exactly that lowest set bit, reducing the count of 1-bits by 1 each time.
 *
 * TIME  : O(k) — k = number of set bits (at most 32)
 * SPACE : O(1)
 * ============================================================
 */
public class NumberOf1Bits {

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}
