package com.interview.blind75.bits;

/**
 * ============================================================
 * PROBLEM : Missing Number
 * LINK    : https://leetcode.com/problems/missing-number/
 * DIFFICULTY: Easy
 * PATTERN : Bit Manipulation / Math
 * ============================================================
 *
 * DESCRIPTION:
 * Given an array nums containing n distinct numbers in range [0, n],
 * return the only number in the range that is missing from the array.
 *
 * EXAMPLES:
 *   Input : [3,0,1]  →  Output: 2
 *   Input : [0,1]    →  Output: 2
 *   Input : [9,6,4,2,3,5,7,0,1]  →  Output: 8
 *
 * CONSTRAINTS:
 *   - n == nums.length
 *   - All nums are distinct in range [0, n]
 *
 * ============================================================
 * APPROACH: XOR — Cancel All Present Numbers
 * ============================================================
 * XOR all indices 0..n with all values. Present numbers cancel out.
 * The missing number is what's left.
 *
 * WHY THIS WORKS:
 * Every number 0..n appears once among the indices (0..n-1, plus n as the
 * starting value) and once among the values — except the missing one, which
 * appears only once in total. Since a ^ a = 0, everything else cancels out.
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class MissingNumber {

    public int missingNumber(int[] nums) {
        int result = nums.length;
        for (int i = 0; i < nums.length; i++) {
            result ^= i ^ nums[i];
        }
        return result;
    }
}
