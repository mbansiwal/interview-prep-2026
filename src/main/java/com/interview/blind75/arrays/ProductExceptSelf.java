package com.interview.blind75.arrays;

/**
 * ============================================================
 * PROBLEM : Product of Array Except Self
 * LINK    : https://leetcode.com/problems/product-of-array-except-self/
 * DIFFICULTY: Medium
 * PATTERN : Arrays & Hashing
 * ============================================================
 *
 * DESCRIPTION:
 * Given an integer array nums, return an array answer such that
 * answer[i] equals the product of all elements of nums except nums[i].
 * Must run in O(n) time and without using division.
 *
 * EXAMPLES:
 *   Input : nums = [1,2,3,4]
 *   Output: [24,12,8,6]
 *
 *   Input : nums = [-1,1,0,-3,3]
 *   Output: [0,0,9,0,0]
 *
 * CONSTRAINTS:
 *   - 2 <= nums.length <= 10^5
 *   - No division operation allowed
 *
 * ============================================================
 * APPROACH: Prefix and Suffix Products
 * ============================================================
 * 1. Initialize result array where result[i] = prefix product of all elements before i.
 * 2. Pass left-to-right: result[i] = result[i-1] * nums[i-1].
 * 3. Maintain a running suffix product, pass right-to-left.
 * 4. Multiply each result[i] by the suffix product, then update suffix.
 *
 * WHY THIS WORKS:
 * Product except self = (product of all elements left of i) * (product of all elements right of i).
 * Two passes compute both without needing extra arrays.
 *
 * TIME  : O(n)
 * SPACE : O(1) — output array doesn't count as extra space
 * ============================================================
 */
public class ProductExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        int suffixProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }

        return result;
    }
}
