package com.interview.blind75.dp;

/**
 * ============================================================
 * PROBLEM : Maximum Product Subarray
 * LINK    : https://leetcode.com/problems/maximum-product-subarray/
 * DIFFICULTY: Medium
 * PATTERN : Dynamic Programming (1D)
 * ============================================================
 *
 * DESCRIPTION:
 * Given an integer array nums, find the contiguous subarray which has the
 * largest product and return the product.
 *
 * EXAMPLES:
 *   Input : nums = [2,3,-2,4]   →  Output: 6  ([2,3])
 *   Input : nums = [-2,0,-1]    →  Output: 0
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 2 * 10^4
 *   - May contain negatives and zeros
 *
 * ============================================================
 * APPROACH: Track Both Max and Min Running Products
 * ============================================================
 * A negative number flips max to min and vice versa.
 * Track curMax and curMin at each step.
 * When nums[i] is negative: swap curMax and curMin first, then multiply.
 * Reset to nums[i] if product through here is worse (handles zeros).
 *
 * WHY THIS WORKS:
 * Negatives can turn a large negative into a large positive.
 * By tracking both extremes, we capture this possibility.
 * Reset on each number handles the case where starting fresh is better.
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        int curMax = nums[0], curMin = nums[0], result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) { int t = curMax; curMax = curMin; curMin = t; }
            curMax = Math.max(nums[i], curMax * nums[i]);
            curMin = Math.min(nums[i], curMin * nums[i]);
            result = Math.max(result, curMax);
        }
        return result;
    }
}
