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
 * APPROACHES
 * ============================================================
 * APPROACH 1: Prefix in the output, running suffix  (primary)
 *   1. Left-to-right: result[i] = product of everything before i.
 *   2. Right-to-left: multiply result[i] by a running product of everything after i.
 *   Intuition: answer[i] = (product left of i) × (product right of i).
 *   TIME O(n) · SPACE O(1) extra (output array not counted)
 *
 * APPROACH 2: Separate prefix and suffix arrays
 *   1. prefix[i] = product of nums[0..i-1]; suffix[i] = product of nums[i+1..n-1].
 *   2. result[i] = prefix[i] × suffix[i].
 *   Intuition: the same idea written out explicitly — easiest to explain first.
 *   TIME O(n) · SPACE O(n) for the two helper arrays
 *
 * WHICH TO USE:
 *   Explain #2 first (clearest), then fold the suffix array into a variable to
 *   reach #1 — that's the follow-up interviewers ask for. Division is
 *   disallowed, and would break on zeros anyway.
 * ============================================================
 */
public class ProductExceptSelf {

    /** Approach 1 — prefix in output + running suffix. TIME O(n) · SPACE O(1) extra */
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

    /** Approach 2 — explicit prefix and suffix arrays. TIME O(n) · SPACE O(n) */
    public int[] productExceptSelfPrefixSuffixArrays(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        prefix[0] = 1;
        for (int i = 1; i < n; i++) prefix[i] = prefix[i - 1] * nums[i - 1];
        suffix[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) suffix[i] = suffix[i + 1] * nums[i + 1];

        int[] result = new int[n];
        for (int i = 0; i < n; i++) result[i] = prefix[i] * suffix[i];
        return result;
    }
}
