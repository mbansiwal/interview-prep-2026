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
 * APPROACHES
 * ============================================================
 * APPROACH 1: One-pass binary search, picking the sorted half  (primary)
 *   1. At each step one half of [left, right] is sorted.
 *   2. If the target lies within the sorted half's range, search there;
 *      otherwise search the other half.
 *   Intuition: a rotated array is two sorted runs, and one side of mid is always clean.
 *   TIME O(log n) · SPACE O(1)
 *
 * APPROACH 2: Find the rotation point, then a normal binary search
 *   1. Binary-search for the index of the minimum (as in LC 153).
 *   2. The target is in [pivot, n−1] if it's between nums[pivot] and nums[n−1];
 *      otherwise it's in [0, pivot−1].
 *   3. Run a standard binary search on that range.
 *   Intuition: splits a tricky search into two simple, familiar ones.
 *   TIME O(log n) — two binary searches · SPACE O(1)
 *
 * WHICH TO USE:
 *   #1 is the elegant expected answer. #2 is easier to get right under pressure
 *   and reuses Find Minimum in Rotated Sorted Array — a fine choice to say aloud.
 * ============================================================
 */
public class SearchInRotatedSortedArray {

    /** Approach 1 — one-pass, choose the sorted half. TIME O(log n) · SPACE O(1) */
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

    /** Approach 2 — find the pivot (minimum), then binary search one run. TIME O(log n) · SPACE O(1) */
    public int searchFindPivot(int[] nums, int target) {
        int n = nums.length;
        int lo = 0, hi = n - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) lo = mid + 1;
            else hi = mid;
        }
        int pivot = lo;

        int left, right;
        if (target >= nums[pivot] && target <= nums[n - 1]) {
            left = pivot;
            right = n - 1;
        } else {
            left = 0;
            right = pivot - 1;
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}
