package com.interview.blind75.arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * ============================================================
 * PROBLEM : Contains Duplicate
 * LINK    : https://leetcode.com/problems/contains-duplicate/
 * DIFFICULTY: Easy
 * PATTERN : Arrays & Hashing
 * ============================================================
 *
 * DESCRIPTION:
 * Given an integer array nums, return true if any value appears
 * at least twice in the array, and false if every element is distinct.
 *
 * EXAMPLES:
 *   Input : nums = [1,2,3,1]
 *   Output: true
 *
 *   Input : nums = [1,2,3,4]
 *   Output: false
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 10^5
 *   - -10^9 <= nums[i] <= 10^9
 *
 * ============================================================
 * APPROACH: HashSet Membership Check
 * ============================================================
 * 1. Create an empty HashSet to track seen numbers.
 * 2. For each number, try seen.add(num).
 * 3. Set.add returns false if the value was already present → duplicate → return true.
 * 4. If we finish without finding a duplicate, return false.
 *
 * WHY THIS WORKS:
 * HashSet gives O(1) average insert + lookup. Set.add returns false when
 * the value is already present, so one call both checks and inserts.
 *
 * TIME  : O(n)
 * SPACE : O(n)
 * ============================================================
 */
public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                return true;
            }
        }
        return false;
    }
}
