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
 * APPROACH: Classic Binary Search
 * ============================================================
 * 1. Initialize left=0, right=n-1.
 * 2. Compute mid = left + (right-left)/2  (avoids overflow).
 * 3. If nums[mid] == target, return mid.
 * 4. If nums[mid] < target, search right half: left = mid+1.
 * 5. Else search left half: right = mid-1.
 * 6. Return -1 if not found.
 *
 * WHY THIS WORKS:
 * Each iteration halves the search space. An element can only be
 * in the correct half based on comparison with the midpoint.
 *
 * TIME  : O(log n)
 * SPACE : O(1)
 * ============================================================
 */
public class BinarySearch {

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
}
