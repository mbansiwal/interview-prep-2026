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
 * APPROACHES  (n = nums.length)
 * ============================================================
 * APPROACH 1: Backtracking with sort + skip duplicates
 * 1. Sort so equal values are adjacent.
 * 2. Same as Subsets, but at each level skip nums[i] when i > start and nums[i] == nums[i-1].
 * Intuition: at one level, each distinct value may be the "next pick" only once;
 * `i > start` (not `i > 0`) still allows [2,2] deeper in the tree.
 * TIME  : O(n * 2^n)
 * SPACE : O(n) recursion (excluding output)
 *
 * APPROACH 2: Iterative with duplicate handling
 * 1. Sort. Start with [[]].
 * 2. For a new value, extend every existing subset.
 * 3. For a repeated value, extend only the subsets created in the previous step
 *    (those already contain the earlier copy) — extending older ones would repeat subsets.
 * Intuition: the k-th copy of a value can only follow a subset that took the (k-1)-th copy.
 * TIME  : O(n * 2^n)
 * SPACE : O(1) extra beyond the output (plus the sort)
 *
 * WHICH TO USE:
 * Backtracking — the "i > start" skip is the reusable trick (Combination Sum II, Permutations II).
 * The iterative version is a good follow-up if asked for no recursion.
 * Note: both sort `nums` in place.
 * ============================================================
 */
public class SubsetsII {

    /** Approach 1 — Backtracking + skip duplicates. TIME O(n * 2^n) · SPACE O(n) recursion (sorts input) */
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

    /** Approach 2 — Iterative, extend only last step's subsets on duplicates. TIME O(n * 2^n) · SPACE O(1) extra (sorts input) */
    public List<List<Integer>> subsetsWithDupIterative(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        int previousStepStart = 0;
        for (int i = 0; i < nums.length; i++) {
            int from = (i > 0 && nums[i] == nums[i - 1]) ? previousStepStart : 0;
            int existing = result.size();
            for (int j = from; j < existing; j++) {
                List<Integer> extended = new ArrayList<>(result.get(j));
                extended.add(nums[i]);
                result.add(extended);
            }
            previousStepStart = existing;
        }
        return result;
    }
}
