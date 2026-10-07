package com.interview.blind75.math;

/**
 * ============================================================
 * PROBLEM : Plus One
 * LINK    : https://leetcode.com/problems/plus-one/
 * DIFFICULTY: Easy
 * PATTERN : Math / Array
 * ============================================================
 *
 * DESCRIPTION:
 * Given a non-empty array representing a non-negative integer (each element is
 * a digit), increment the integer by one and return the resulting array.
 *
 * EXAMPLES:
 *   Input : [1,2,3]  →  Output: [1,2,4]
 *   Input : [4,3,2,1]  →  Output: [4,3,2,2]
 *   Input : [9,9,9]  →  Output: [1,0,0,0]
 *
 * CONSTRAINTS:
 *   - 1 <= digits.length <= 100
 *   - All digits 0-9
 *
 * ============================================================
 * APPROACH: Iterate from Right, Handle Carry
 * ============================================================
 * 1. From rightmost digit, add 1. If no carry (digit < 9 after increment), done.
 * 2. If digit was 9, set to 0 and carry over.
 * 3. If all digits were 9, prepend 1.
 *
 * WHY THIS WORKS:
 * Carry propagates left only when a digit overflows from 9 to 10.
 * The worst case (all 9s) requires a new leading digit.
 *
 * TIME  : O(n)
 * SPACE : O(n) — worst case new array
 * ============================================================
 */
public class PlusOne {

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }
}
