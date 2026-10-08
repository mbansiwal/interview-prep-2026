package com.interview.blind75.greedy;

/**
 * ============================================================
 * PROBLEM : Maximum Subarray
 * LINK    : https://leetcode.com/problems/maximum-subarray/
 * DIFFICULTY: Medium
 * PATTERN : Greedy / Kadane's Algorithm
 * ============================================================
 *
 * DESCRIPTION:
 * Given an integer array nums, find the contiguous subarray with the
 * largest sum and return the sum.
 *
 * EXAMPLES:
 *   Input : [-2,1,-3,4,-1,2,1,-5,4]  →  Output: 6 ([4,-1,2,1])
 *   Input : [1]                        →  Output: 1
 *   Input : [5,4,-1,7,8]              →  Output: 23
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 10^5
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * Brute force (sum every subarray) is O(n²) with running sums, O(n³) without.
 *
 * APPROACH 1: Kadane's algorithm                           → maxSubArray
 *   1. Keep currentSum = best sum of a subarray ending here.
 *   2. If currentSum < 0, drop it (start fresh); add num; update maxSum.
 *   Intuition: a negative running sum can only make whatever follows smaller.
 *   TIME  : O(n)    SPACE : O(1)
 *
 * APPROACH 2: Prefix sum minus the smallest earlier prefix → maxSubArrayPrefix
 *   1. sum(i..j) = prefix[j+1] - prefix[i].
 *   2. Scan once, keeping the minimum prefix seen so far; best = max(prefix - minPrefix).
 *   Intuition: like "best time to buy and sell stock" on the prefix-sum curve.
 *   TIME  : O(n)    SPACE : O(1)
 *
 * APPROACH 3: Divide and conquer                           → maxSubArrayDivideConquer
 *   1. Best subarray is entirely in the left half, entirely in the right half,
 *      or crosses the middle.
 *   2. The crossing case = best suffix of the left + best prefix of the right (O(n)).
 *   Intuition: the classic follow-up LeetCode asks for; also the basis of segment trees.
 *   TIME  : O(n log n)    SPACE : O(log n) recursion stack
 *
 * WHICH TO USE:
 * Kadane (Approach 1). Be ready to sketch Approach 3 — "try the divide-and-conquer
 * approach, which is more subtle" is the official follow-up.
 * ============================================================
 */
public class MaximumSubarray {

    /** Approach 1 — Kadane's algorithm. TIME O(n) · SPACE O(1) */
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0], currentSum = 0;
        for (int num : nums) {
            if (currentSum < 0) currentSum = 0;
            currentSum += num;
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    /** Approach 2 — Prefix sum minus minimum earlier prefix. TIME O(n) · SPACE O(1) */
    public int maxSubArrayPrefix(int[] nums) {
        int prefix = 0, minPrefix = 0, best = Integer.MIN_VALUE;
        for (int num : nums) {
            prefix += num;
            best = Math.max(best, prefix - minPrefix);
            minPrefix = Math.min(minPrefix, prefix);
        }
        return best;
    }

    /** Approach 3 — Divide and conquer. TIME O(n log n) · SPACE O(log n) */
    public int maxSubArrayDivideConquer(int[] nums) {
        return best(nums, 0, nums.length - 1);
    }

    private int best(int[] nums, int lo, int hi) {
        if (lo == hi) return nums[lo];
        int mid = (lo + hi) >>> 1;

        int leftSuffix = Integer.MIN_VALUE, sum = 0;
        for (int i = mid; i >= lo; i--) {
            sum += nums[i];
            leftSuffix = Math.max(leftSuffix, sum);
        }
        int rightPrefix = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= hi; i++) {
            sum += nums[i];
            rightPrefix = Math.max(rightPrefix, sum);
        }
        int crossing = leftSuffix + rightPrefix;
        return Math.max(crossing, Math.max(best(nums, lo, mid), best(nums, mid + 1, hi)));
    }
}
