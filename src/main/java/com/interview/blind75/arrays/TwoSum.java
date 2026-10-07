package com.interview.blind75.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * PROBLEM : Two Sum
 * LINK    : https://leetcode.com/problems/two-sum/
 * DIFFICULTY: Easy
 * PATTERN : Arrays & Hashing
 * ============================================================
 *
 * DESCRIPTION:
 * Given an array of integers nums and an integer target, return
 * indices of the two numbers such that they add up to target.
 * You may assume exactly one solution exists, and you may not
 * use the same element twice.
 *
 * EXAMPLES:
 *   Input : nums = [2,7,11,15], target = 9
 *   Output: [0,1]
 *
 *   Input : nums = [3,2,4], target = 6
 *   Output: [1,2]
 *
 * CONSTRAINTS:
 *   - 2 <= nums.length <= 10^4
 *   - Exactly one valid answer exists
 *
 * ============================================================
 * APPROACH: HashMap Complement Lookup
 * ============================================================
 * 1. Create a HashMap mapping each value to its index.
 * 2. For each number at index i, compute complement = target - num.
 * 3. If complement is already in the map, return [map.get(complement), i].
 * 4. Otherwise, store num -> i in the map and continue.
 *
 * WHY THIS WORKS:
 * We trade space for time: instead of scanning for the complement
 * with a nested loop (O(n²)), we do a O(1) map lookup per element.
 *
 * TIME  : O(n)
 * SPACE : O(n)
 * ============================================================
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexByValue = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (indexByValue.containsKey(complement)) {
                return new int[]{indexByValue.get(complement), i};
            }
            indexByValue.put(nums[i], i);
        }
        return new int[]{};
    }
}
