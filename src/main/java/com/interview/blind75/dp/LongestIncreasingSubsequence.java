package com.interview.blind75.dp;

/**
 * ============================================================
 * PROBLEM : Longest Increasing Subsequence
 * LINK    : https://leetcode.com/problems/longest-increasing-subsequence/
 * DIFFICULTY: Medium
 * PATTERN : Dynamic Programming / Binary Search (Patience Sort)
 * ============================================================
 *
 * DESCRIPTION:
 * Given an integer array nums, return the length of the longest strictly
 * increasing subsequence.
 *
 * EXAMPLES:
 *   Input : [10,9,2,5,3,7,101,18]  →  Output: 4  ([2,3,7,101])
 *   Input : [0,1,0,3,2,3]          →  Output: 4
 *   Input : [7,7,7,7,7,7,7]        →  Output: 1
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 2500
 *
 * ============================================================
 * APPROACH: Binary Search + Tails Array (Patience Sort, O(n log n))
 * ============================================================
 * Maintain a `tails` array where tails[i] = smallest tail element of
 * all increasing subsequences of length i+1.
 * For each num:
 * - Binary search for leftmost position in tails where tails[pos] >= num.
 * - Replace tails[pos] with num (or append if pos == length).
 *
 * WHY THIS WORKS:
 * tails is always sorted. Replacing with a smaller value keeps future
 * options open. The length of tails at the end is the LIS length.
 * Note: tails does NOT represent an actual LIS subsequence, just the length.
 *
 * TIME  : O(n log n)
 * SPACE : O(n)
 * ============================================================
 */
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int num : nums) {
            int lo = 0, hi = size;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (tails[mid] < num) lo = mid + 1;
                else hi = mid;
            }
            tails[lo] = num;
            if (lo == size) size++;
        }
        return size;
    }
}
