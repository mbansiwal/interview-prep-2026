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
 * APPROACHES
 * ============================================================
 * Java has no unsigned int, so inputs with the top bit set arrive as negative
 * numbers — every approach must treat n as a raw 32-bit pattern.
 *
 * APPROACH 1: n & (n-1) clears the lowest set bit          → hammingWeight
 *   1. Repeat n &= (n - 1), counting, until n == 0.
 *   Intuition: n - 1 flips the lowest 1-bit and everything below it, so the AND
 *   removes exactly one 1-bit per iteration.
 *   TIME  : O(k), k = number of set bits (≤ 32)    SPACE : O(1)
 *
 * APPROACH 2: Check every bit with a logical shift         → hammingWeightShift
 *   1. 32 times: add (n & 1), then n >>>= 1.
 *   Intuition: the straightforward version; >>> (not >>) so negatives don't
 *   keep pulling in 1s from the sign bit.
 *   TIME  : O(32) = O(1)    SPACE : O(1)
 *
 * APPROACH 3: Parallel (SWAR) popcount                     → hammingWeightParallel
 *   1. Add adjacent bits in pairs, then pairs into nibbles, then nibbles into bytes
 *      (masks 0x55555555, 0x33333333, 0x0F0F0F0F).
 *   2. Multiply by 0x01010101 to sum the four bytes into the top byte; shift down.
 *   Intuition: counts many bits at once inside one register — what Integer.bitCount does.
 *   TIME  : O(1), about a dozen operations, no loop    SPACE : O(1)
 *
 * WHICH TO USE:
 * Approach 1 is the expected interview answer. Mention Approach 3 (or
 * Integer.bitCount) for "this is called millions of times" follow-ups.
 * ============================================================
 */
public class NumberOf1Bits {

    /** Approach 1 — n & (n-1) loop. TIME O(k) · SPACE O(1) */
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }

    /** Approach 2 — Test each of the 32 bits. TIME O(1) · SPACE O(1) */
    public int hammingWeightShift(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += n & 1;
            n >>>= 1;
        }
        return count;
    }

    /** Approach 3 — Parallel SWAR popcount. TIME O(1) · SPACE O(1) */
    public int hammingWeightParallel(int n) {
        n = n - ((n >>> 1) & 0x55555555);                // 2-bit sums
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333); // 4-bit sums
        n = (n + (n >>> 4)) & 0x0F0F0F0F;                // 8-bit sums
        return (n * 0x01010101) >>> 24;                  // add the 4 bytes
    }
}
