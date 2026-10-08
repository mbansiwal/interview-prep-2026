package com.interview.blind75.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ============================================================
 * PROBLEM : Combination Sum II
 * LINK    : https://leetcode.com/problems/combination-sum-ii/
 * DIFFICULTY: Medium
 * PATTERN : Backtracking
 * ============================================================
 *
 * DESCRIPTION:
 * Given a collection of candidate numbers (candidates, may have duplicates)
 * and a target number, find all unique combinations that sum to target.
 * Each number in candidates may only be used once in the combination.
 *
 * EXAMPLES:
 *   Input : candidates = [10,1,2,7,6,1,5], target = 8
 *   Output: [[1,1,6],[1,2,5],[1,7],[2,6]]
 *
 *   Input : candidates = [2,5,2,1,2], target = 5
 *   Output: [[1,2,2],[5]]
 *
 * CONSTRAINTS:
 *   - 1 <= candidates.length <= 100
 *   - May contain duplicates; each used at most once
 *
 * ============================================================
 * APPROACH 1: Backtracking + Sort + Skip Duplicates at Same Level
 * ============================================================
 * 1. Sort to group duplicates.
 * 2. At each level, skip candidates[i] if i > start and candidates[i] == candidates[i-1].
 * 3. Prune: candidates[i] > remaining → break (sorted, so every later one is too big).
 * 4. Recurse with i+1 (each element used once).
 * 5. Base case: remaining == 0 → save a copy of the current combination.
 *
 * WHY THIS WORKS:
 * Same duplicate-skip trick as SubsetsII but we recurse i+1 (no reuse).
 * Sorting ensures all duplicates at a given position are tried exactly once.
 *
 * TIME  : O(n * 2^n)
 * SPACE : O(n) recursion depth (excluding output)
 *
 * ALTERNATIVES: generating all 2^n subsets and de-duplicating with a Set costs
 * O(n * 2^n) time plus O(n * 2^n) memory — no materially better alternative.
 *
 * WHICH TO USE: backtracking with the "i > start" duplicate skip.
 * ============================================================
 */
public class CombinationSumII {

    /** Approach 1 — Backtracking + skip duplicates. TIME O(n * 2^n) · SPACE O(n) (sorts input) */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int remaining, int start,
                           List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > remaining) break;
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            current.add(candidates[i]);
            backtrack(candidates, remaining - candidates[i], i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}
