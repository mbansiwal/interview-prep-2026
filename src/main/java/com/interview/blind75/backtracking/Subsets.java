package com.interview.blind75.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * PROBLEM : Subsets
 * LINK    : https://leetcode.com/problems/subsets/
 * DIFFICULTY: Medium
 * PATTERN : Backtracking
 * ============================================================
 *
 * DESCRIPTION:
 * Given an integer array nums of unique elements, return all possible
 * subsets (the power set). The solution set must not contain duplicate subsets.
 *
 * EXAMPLES:
 *   Input : nums = [1,2,3]
 *   Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 *   Input : nums = [0]
 *   Output: [[],[0]]
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 10
 *   - All elements are unique
 *
 * ============================================================
 * APPROACH: Backtracking — Include/Exclude Each Element
 * ============================================================
 * 1. At each index, add the current subset to the result.
 * 2. Try including each element from index onwards.
 * 3. Backtrack by removing the last added element.
 *
 * WHY THIS WORKS:
 * Every prefix of the recursion tree represents a valid subset.
 * By starting the loop from the current index, we avoid duplicates.
 *
 * TIME  : O(n * 2^n) — 2^n subsets, each taking O(n) to copy
 * SPACE : O(n) — recursion depth
 * ============================================================
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}
