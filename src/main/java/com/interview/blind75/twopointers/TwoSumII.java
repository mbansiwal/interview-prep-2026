package com.interview.blind75.twopointers;

/**
 * ============================================================
 * PROBLEM : Two Sum II - Input Array Is Sorted
 * LINK    : https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 * DIFFICULTY: Medium
 * PATTERN : Two Pointers
 * ============================================================
 *
 * DESCRIPTION:
 * Given a 1-indexed sorted integer array, find two numbers that add
 * to target. Return their 1-based indices. Each input has exactly
 * one solution and you may not use the same element twice.
 *
 * EXAMPLES:
 *   Input : numbers = [2,7,11,15], target = 9
 *   Output: [1,2]
 *
 *   Input : numbers = [2,3,4], target = 6
 *   Output: [1,3]
 *
 * CONSTRAINTS:
 *   - 2 <= numbers.length <= 3 * 10^4
 *   - numbers is sorted in non-decreasing order
 *   - Only one valid answer exists
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Two pointers from both ends  (primary)
 *   1. l = 0, r = n − 1.
 *   2. Sum too small → l++; too big → r--; equal → return [l+1, r+1].
 *   Intuition: in a sorted array, moving l raises the sum and moving r lowers it.
 *   TIME O(n) · SPACE O(1)
 *
 * APPROACH 2: Binary search for each complement
 *   1. For each i, binary-search numbers[i+1..n-1] for target − numbers[i].
 *   Intuition: uses the sorted order directly, one element at a time.
 *   TIME O(n log n) · SPACE O(1)
 *
 * WHICH TO USE:
 *   #1 is optimal and expected. #2 is a reasonable first idea to mention.
 *   A HashMap (as in Two Sum) also works in O(n) but wastes the sorted input
 *   and uses O(n) space — the problem asks for constant extra space.
 * ============================================================
 */
public class TwoSumII {

    /** Approach 1 — two pointers. TIME O(n) · SPACE O(1) */
    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) return new int[]{l + 1, r + 1};
            else if (sum < target) l++;
            else r--;
        }
        return new int[]{};
    }

    /** Approach 2 — binary search each complement. TIME O(n log n) · SPACE O(1) */
    public int[] twoSumBinarySearch(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int want = target - numbers[i];
            int lo = i + 1, hi = numbers.length - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (numbers[mid] == want) return new int[]{i + 1, mid + 1};
                if (numbers[mid] < want) lo = mid + 1;
                else hi = mid - 1;
            }
        }
        return new int[]{};
    }
}
