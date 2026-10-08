package com.interview.blind75.bits;

/**
 * ============================================================
 * PROBLEM : Reverse Bits
 * LINK    : https://leetcode.com/problems/reverse-bits/
 * DIFFICULTY: Easy
 * PATTERN : Bit Manipulation
 * ============================================================
 *
 * DESCRIPTION:
 * Reverse the bits of a given 32-bit unsigned integer.
 *
 * EXAMPLES:
 *   Input : 00000010100101000001111010011100  →  Output: 964176192
 *   Input : 11111111111111111111111111111101  →  Output: 3221225471
 *
 * JAVA NOTE:
 * Java has no unsigned int. The bit pattern returned is correct, but 3221225471
 * (> Integer.MAX_VALUE) prints as -1073741825. Use Integer.toUnsignedString(r)
 * to see the unsigned value. We shift with >>> (logical, fills with 0) rather
 * than >> (arithmetic, copies the sign bit), so negative inputs don't loop on 1s.
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Shift and OR, one bit at a time              → reverseBits
 *   1. 32 times: result = (result << 1) | (n & 1); then n >>>= 1.
 *   Intuition: read n's bits right-to-left and write them into result left-to-right.
 *   TIME  : O(32) = O(1)    SPACE : O(1)
 *
 * APPROACH 2: Divide and conquer with masks                → reverseBitsMasks
 *   1. Swap the two 16-bit halves, then the bytes inside each half,
 *      then the nibbles, then bit pairs, then adjacent bits.
 *   2. Each step is one shift-and-mask on the whole word.
 *   Intuition: reversing = swapping halves recursively, done for all positions in
 *   parallel — 5 steps for 32 bits (log₂ 32). This is what Integer.reverse does.
 *   TIME  : O(log 32) = O(1), no loop    SPACE : O(1)
 *
 * WHICH TO USE:
 * Approach 1 in the interview. Approach 2 answers "if this function is called many
 * times, how would you optimise it?" (another answer: cache reversed bytes in a
 * 256-entry table and combine four lookups).
 * ============================================================
 */
public class ReverseBits {

    /** Approach 1 — Bit-by-bit shift and OR. TIME O(1) (32 steps) · SPACE O(1) */
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = (result << 1) | (n & 1);
            n >>>= 1;
        }
        return result;
    }

    /** Approach 2 — Divide-and-conquer mask swaps. TIME O(1) (5 steps) · SPACE O(1) */
    public int reverseBitsMasks(int n) {
        n = (n >>> 16) | (n << 16);                                // swap 16-bit halves
        n = ((n & 0xFF00FF00) >>> 8) | ((n & 0x00FF00FF) << 8);    // swap bytes
        n = ((n & 0xF0F0F0F0) >>> 4) | ((n & 0x0F0F0F0F) << 4);    // swap nibbles
        n = ((n & 0xCCCCCCCC) >>> 2) | ((n & 0x33333333) << 2);    // swap bit pairs
        n = ((n & 0xAAAAAAAA) >>> 1) | ((n & 0x55555555) << 1);    // swap adjacent bits
        return n;
    }
}
