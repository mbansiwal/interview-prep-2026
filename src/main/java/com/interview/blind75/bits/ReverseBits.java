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
 * to see the unsigned value. We shift n with >>> (logical, fills with 0) rather
 * than >> (arithmetic, copies the sign bit), so negative inputs don't loop on 1s.
 *
 * ============================================================
 * APPROACH: Shift and OR Bit by Bit
 * ============================================================
 * For each of 32 iterations:
 * 1. Shift result left by 1 to make room.
 * 2. OR the lowest bit of n into result.
 * 3. Shift n right by 1 to process next bit.
 *
 * WHY THIS WORKS:
 * We extract bits from n right-to-left and place them into result left-to-right,
 * effectively reversing the 32-bit representation.
 *
 * TIME  : O(1) — always 32 iterations
 * SPACE : O(1)
 * ============================================================
 */
public class ReverseBits {

    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = (result << 1) | (n & 1);
            n >>>= 1;
        }
        return result;
    }
}
