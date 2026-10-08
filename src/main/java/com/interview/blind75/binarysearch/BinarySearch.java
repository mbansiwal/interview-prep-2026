package com.interview.blind75.binarysearch;

/**
 * ============================================================
 * PROBLEM : Binary Search
 * LINK    : https://leetcode.com/problems/binary-search/
 * DIFFICULTY: Easy
 * PATTERN : Binary Search
 * ============================================================
 *
 * DESCRIPTION:
 * Given an array of integers nums sorted in ascending order, and
 * an integer target, write an algorithm to search target in nums.
 * If target exists, return its index. Otherwise, return -1.
 *
 * EXAMPLES:
 *   Input : nums = [-1,0,3,5,9,12], target = 9
 *   Output: 4
 *
 *   Input : nums = [-1,0,3,5,9,12], target = 2
 *   Output: -1
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 10^4
 *   - All integers are unique
 *   - nums is sorted in ascending order
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Iterative binary search  (primary)
 *   1. left = 0, right = n − 1.
 *   2. mid = left + (right − left) / 2  (avoids int overflow).
 *   3. Equal → return mid; smaller → search right half; larger → left half.
 *   Intuition: each comparison discards half of the remaining range.
 *   TIME O(log n) · SPACE O(1)
 *
 * APPROACH 2: Recursive binary search
 *   1. Same steps, recursing on the half that can still contain the target.
 *   Intuition: the same algorithm expressed as divide and conquer.
 *   TIME O(log n) · SPACE O(log n) recursion stack
 *
 * WHICH TO USE:
 *   #1 in production and interviews (no stack, no overflow). #2 is fine to show
 *   the recursive structure. Linear scan is O(n).
 * ============================================================
 */
public class BinarySearch {

    /** Approach 1 — iterative. TIME O(log n) · SPACE O(1) */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    /** Approach 2 — recursive. TIME O(log n) · SPACE O(log n) recursion */
    public int searchRecursive(int[] nums, int target) {
        return searchRange(nums, target, 0, nums.length - 1);
    }

    private int searchRange(int[] nums, int target, int left, int right) {
        if (left > right) return -1;
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) return mid;
        return nums[mid] < target
                ? searchRange(nums, target, mid + 1, right)
                : searchRange(nums, target, left, mid - 1);
    }
}
