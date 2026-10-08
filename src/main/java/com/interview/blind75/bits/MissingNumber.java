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
 * APPROACHES
 * ============================================================
 * Sorting and scanning is O(n log n); a HashSet of seen values is O(n) time but
 * O(n) space. Both approaches below are O(n) time, O(1) space.
 *
 * APPROACH 1: XOR indices with values                      → missingNumber
 *   1. Start with result = n, then XOR in every index i and every value nums[i].
 *   Intuition: every number 0..n appears once among the indices (0..n-1, plus n
 *   as the start value) and once among the values — except the missing one.
 *   Since a ^ a = 0, everything else cancels out.
 *   TIME  : O(n)    SPACE : O(1)
 *
 * APPROACH 2: Gauss sum                                    → missingNumberGauss
 *   1. Expected total of 0..n is n(n+1)/2; subtract every value.
 *   Intuition: whatever the array's total is short by is the missing number.
 *   (With n <= 10^4 this can't overflow; for huge n, XOR avoids the risk.)
 *   TIME  : O(n)    SPACE : O(1)
 *
 * WHICH TO USE:
 * Gauss is the quickest to explain; XOR is the "bit manipulation" answer and has
 * no overflow concern. Mention both.
 * ============================================================
 */
public class MissingNumber {

    /** Approach 1 — XOR indices with values. TIME O(n) · SPACE O(1) */
    public int missingNumber(int[] nums) {
        int result = nums.length;
        for (int i = 0; i < nums.length; i++) {
            result ^= i ^ nums[i];
        }
        return result;
    }

    /** Approach 2 — Gauss sum minus actual sum. TIME O(n) · SPACE O(1) */
    public int missingNumberGauss(int[] nums) {
        int n = nums.length;
        int missing = n * (n + 1) / 2;
        for (int num : nums) missing -= num;
        return missing;
    }
}
