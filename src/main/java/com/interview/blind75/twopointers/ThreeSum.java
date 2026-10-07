package com.interview.blind75.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ============================================================
 * PROBLEM : 3Sum
 * LINK    : https://leetcode.com/problems/3sum/
 * DIFFICULTY: Medium
 * PATTERN : Two Pointers
 * ============================================================
 *
 * DESCRIPTION:
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j != k and nums[i] + nums[j] + nums[k] == 0.
 * The solution set must not contain duplicate triplets.
 *
 * EXAMPLES:
 *   Input : nums = [-1,0,1,2,-1,-4]
 *   Output: [[-1,-1,2],[-1,0,1]]
 *
 *   Input : nums = [0,0,0]
 *   Output: [[0,0,0]]
 *
 * CONSTRAINTS:
 *   - 3 <= nums.length <= 3000
 *
 * ============================================================
 * APPROACH: Sort + Two Pointers per Fixed Element
 * ============================================================
 * 1. Sort the array to enable two-pointer and duplicate skipping.
 * 2. Fix element i from 0 to n-3.
 * 3. Skip i if nums[i] == nums[i-1] (duplicate pivot).
 * 4. Use two pointers l=i+1, r=n-1 to find pairs summing to -nums[i].
 * 5. Skip duplicates after finding a valid triplet.
 *
 * WHY THIS WORKS:
 * Sorting lets us use two pointers for the inner search in O(n). Skipping
 * duplicate pivots and duplicate pair values ensures unique triplets.
 *
 * TIME  : O(n²)
 * SPACE : O(1) — output excluded
 * ============================================================
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return result;
    }
}
