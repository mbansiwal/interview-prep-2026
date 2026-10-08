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
 * APPROACHES
 * ============================================================
 * Brute force (product of every subarray) is O(n²).
 *
 * APPROACH 1: Track running max AND min                    → maxProduct
 *   1. curMax / curMin = largest / smallest product of a subarray ending here.
 *   2. A negative number swaps their roles, so swap first, then
 *      curMax = max(num, curMax·num), curMin = min(num, curMin·num).
 *   3. Restarting at num handles zeros.
 *   Intuition: the most negative product can become the largest after one more negative.
 *   TIME  : O(n)    SPACE : O(1)
 *
 * APPROACH 2: Prefix and suffix product scan               → maxProductPrefixSuffix
 *   1. Multiply left→right (prefix) and right→left (suffix) at the same time.
 *   2. Reset a running product to 1 after it hits 0.
 *   3. Answer = largest prefix or suffix product seen.
 *   Intuition: between zeros, with an odd number of negatives the best subarray
 *   drops either everything up to the first negative or everything after the last
 *   one — i.e. it's a prefix or a suffix of that segment.
 *   TIME  : O(n)    SPACE : O(1)
 *
 * WHICH TO USE:
 * Approach 1 is the canonical DP answer (and generalises the Kadane idea);
 * Approach 2 is a neat, easy-to-verify alternative worth mentioning.
 * ============================================================
 */
public class MaximumProductSubarray {

    /** Approach 1 — Running max and min products. TIME O(n) · SPACE O(1) */
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

    /** Approach 2 — Prefix and suffix product scan. TIME O(n) · SPACE O(1) */
    public int maxProductPrefixSuffix(int[] nums) {
        int n = nums.length;
        int result = Integer.MIN_VALUE, prefix = 1, suffix = 1;
        for (int i = 0; i < n; i++) {
            prefix = (prefix == 0 ? 1 : prefix) * nums[i];
            suffix = (suffix == 0 ? 1 : suffix) * nums[n - 1 - i];
            result = Math.max(result, Math.max(prefix, suffix));
        }
        return result;
    }
}
