package com.interview.blind75.binarysearch;

/**
 * ============================================================
 * PROBLEM : Search in Rotated Sorted Array
 * LINK    : https://leetcode.com/problems/search-in-rotated-sorted-array/
 * DIFFICULTY: Medium
 * PATTERN : Binary Search
 * ============================================================
 *
 * DESCRIPTION:
 * Given a rotated sorted array with unique values and an integer target,
 * return the index of target, or -1 if it is not present.
 *
 * EXAMPLES:
 *   Input : nums = [4,5,6,7,0,1,2], target = 0
 *   Output: 4
 *
 *   Input : nums = [4,5,6,7,0,1,2], target = 3
 *   Output: -1
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 5000
 *   - All values are unique
 *   - O(log n) required
 *
 * ============================================================
 * APPROACH: Binary Search with Sorted-Half Identification
 * ============================================================
 * 1. Determine which half is sorted by comparing nums[left] and nums[mid].
 * 2. If left half is sorted AND target is in that range, search left.
 * 3. Otherwise search right.
 * 4. Mirror logic for right half being sorted.
 *
 * WHY THIS WORKS:
 * In a rotated array, at least one half is always sorted. By identifying
 * the sorted half, we can decide which half contains the target.
 *
 * TIME  : O(log n)
 * SPACE : O(1)
 * ============================================================
 */
public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;

            if (nums[left] <= nums[mid]) {
                // left half is sorted
                if (target >= nums[left] && target < nums[mid]) right = mid - 1;
                else left = mid + 1;
            } else {
                // right half is sorted
                if (target > nums[mid] && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            }
        }
        return -1;
    }
}
