package com.interview.blind75.bits;

/**
 * ============================================================
 * PROBLEM : Single Number
 * LINK    : https://leetcode.com/problems/single-number/
 * DIFFICULTY: Easy
 * PATTERN : Bit Manipulation
 * ============================================================
 *
 * DESCRIPTION:
 * Given a non-empty array where every element appears twice except for one,
 * find the single element. Must be O(n) time and O(1) space.
 *
 * EXAMPLES:
 *   Input : [2,2,1]       →  Output: 1
 *   Input : [4,1,2,1,2]   →  Output: 4
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 3 * 10^4
 *
 * ============================================================
 * APPROACH: XOR All Elements
 * ============================================================
 * XOR properties: a ^ a = 0, a ^ 0 = a, XOR is commutative & associative.
 * XOR-ing all elements cancels all duplicates, leaving the single number.
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class SingleNumber {

    public int singleNumber(int[] nums) {
        int result = 0;
        for (int n : nums) result ^= n;
        return result;
    }
}
