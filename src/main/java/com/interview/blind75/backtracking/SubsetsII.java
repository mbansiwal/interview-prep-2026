package com.interview.blind75.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ============================================================
 * PROBLEM : Subsets II
 * LINK    : https://leetcode.com/problems/subsets-ii/
 * DIFFICULTY: Medium
 * PATTERN : Backtracking
 * ============================================================
 *
 * DESCRIPTION:
 * Given an integer array nums that may contain duplicates, return all possible
 * subsets (the power set). The solution set must not contain duplicate subsets.
 *
 * EXAMPLES:
 *   Input : nums = [1,2,2]
 *   Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 *   Input : nums = [0]
 *   Output: [[],[0]]
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 10
 *   - May contain duplicates
 *
 * ============================================================
 * APPROACH: Backtracking with Sort + Skip Duplicates
 * ============================================================
 * 1. Sort the array so duplicates are adjacent.
 * 2. At each level, skip candidates[i] if it equals candidates[i-1]
 *    AND i > start (we've already branched on this value at this level).
 * 3. Otherwise, same backtracking as Subsets.
 *
 * WHY THIS WORKS:
 * Sorting groups duplicates. The skip condition ensures that at each recursion
 * level, each distinct value is only used as the "first pick" once.
 * i > start (not i > 0) is crucial: it allows reuse in deeper levels.
 *
 * TIME  : O(n * 2^n)
 * SPACE : O(n)
 * ============================================================
 */
public class SubsetsII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, int start, List<Integer> current,
                           List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}
