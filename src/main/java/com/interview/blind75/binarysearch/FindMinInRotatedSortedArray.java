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
 * APPROACH: Binary Search on Rotation Point
 * ============================================================
 * 1. If nums[mid] > nums[right], minimum is in right half: left = mid+1.
 * 2. Otherwise minimum is in left half (including mid): right = mid.
 * 3. When left == right, we found the minimum.
 *
 * WHY THIS WORKS:
 * The right half of the rotation is always "lower" than the left half.
 * Comparing mid with right tells us which side the rotation boundary
 * (and minimum) is on.
 *
 * TIME  : O(log n)
 * SPACE : O(1)
 * ============================================================
 */
public class FindMinInRotatedSortedArray {

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
