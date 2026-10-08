package com.interview.blind75.binarysearch;

/**
 * ============================================================
 * PROBLEM : Find Minimum in Rotated Sorted Array
 * LINK    : https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * DIFFICULTY: Medium
 * PATTERN : Binary Search
 * ============================================================
 *
 * DESCRIPTION:
 * Given the sorted rotated array nums of unique elements, return the
 * minimum element in O(log n) time.
 *
 * EXAMPLES:
 *   Input : nums = [3,4,5,1,2]
 *   Output: 1
 *
 *   Input : nums = [4,5,6,7,0,1,2]
 *   Output: 0
 *
 * CONSTRAINTS:
 *   - n == nums.length
 *   - 1 <= n <= 5000
 *   - All values are unique
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Binary search comparing mid with the right end  (primary — the only optimal approach)
 *   1. left = 0, right = n − 1.
 *   2. nums[mid] > nums[right] → the drop (minimum) is right of mid: left = mid + 1.
 *   3. Otherwise mid..right is sorted, so the minimum is at mid or to its left: right = mid.
 *   4. When left == right, that's the minimum.
 *   Intuition: comparing with the right end always tells which half holds the rotation point.
 *   TIME O(log n) · SPACE O(1)
 *
 * ALTERNATIVES:
 *   Linear scan for the minimum is O(n). No materially better alternative to
 *   the binary search (values are distinct, so there is no O(n) worst case).
 * ============================================================
 */
public class FindMinInRotatedSortedArray {

    /** Approach 1 — binary search against the right end. TIME O(log n) · SPACE O(1) */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) left = mid + 1;
            else right = mid;
        }
        return nums[left];
    }
}
