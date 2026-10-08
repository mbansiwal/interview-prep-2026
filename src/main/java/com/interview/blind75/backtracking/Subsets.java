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
 * APPROACHES  (n = nums.length; output itself is O(n * 2^n))
 * ============================================================
 * APPROACH 1: Backtracking (choose the next element to add)
 * 1. Record the current subset (every node of the recursion tree is a subset).
 * 2. Try adding each element from `start` onwards, recurse with i + 1, then remove it.
 * Intuition: starting at `start` keeps elements in index order, so no subset repeats.
 * TIME  : O(n * 2^n) — 2^n subsets, O(n) to copy each
 * SPACE : O(n) recursion + current list (excluding output)
 *
 * APPROACH 2: Iterative cascading
 * 1. Start with [[]].
 * 2. For each number, copy every existing subset and append the number to the copy.
 * Intuition: each new element doubles the collection — with it, or without it.
 * TIME  : O(n * 2^n)
 * SPACE : O(1) extra beyond the output (no recursion)
 *
 * APPROACH 3: Bitmask
 * 1. Each mask from 0 to 2^n - 1 is one subset: bit i set → include nums[i].
 * 2. Build the subset for every mask.
 * Intuition: there are exactly 2^n subsets, so count through them in binary.
 * TIME  : O(n * 2^n)
 * SPACE : O(1) extra beyond the output (n ≤ 30 so the mask fits in an int)
 *
 * WHICH TO USE:
 * Backtracking is the template interviewers want (it generalises to Subsets II,
 * Combination Sum, …). Cascading and bitmask are nice "no recursion" alternatives.
 * ============================================================
 */
public class Subsets {

    /** Approach 1 — Backtracking. TIME O(n * 2^n) · SPACE O(n) recursion (excluding output) */
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

    /** Approach 2 — Iterative cascading. TIME O(n * 2^n) · SPACE O(1) extra (excluding output) */
    public List<List<Integer>> subsetsIterative(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int num : nums) {
            int existing = result.size();
            for (int i = 0; i < existing; i++) {
                List<Integer> extended = new ArrayList<>(result.get(i));
                extended.add(num);
                result.add(extended);
            }
        }
        return result;
    }

    /** Approach 3 — Bitmask. TIME O(n * 2^n) · SPACE O(1) extra (excluding output) */
    public List<List<Integer>> subsetsBitmask(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>(1 << n);
        for (int mask = 0; mask < (1 << n); mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) subset.add(nums[i]);
            }
            result.add(subset);
        }
        return result;
    }
}
