package com.interview.blind75.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ============================================================
 * PROBLEM : Combination Sum
 * LINK    : https://leetcode.com/problems/combination-sum/
 * DIFFICULTY: Medium
 * PATTERN : Backtracking
 * ============================================================
 *
 * DESCRIPTION:
 * Given an array of distinct integers candidates and a target integer target,
 * return all unique combinations where the chosen numbers sum to target.
 * You may reuse the same number unlimited times.
 *
 * EXAMPLES:
 *   Input : candidates = [2,3,6,7], target = 7
 *   Output: [[2,2,3],[7]]
 *
 *   Input : candidates = [2,3,5], target = 8
 *   Output: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * CONSTRAINTS:
 *   - 1 <= candidates.length <= 30
 *   - All elements are distinct and positive
 *
 * ============================================================
 * APPROACH: Backtracking with Reuse
 * ============================================================
 * 1. Sort candidates so we can stop early.
 * 2. Loop i from `start`; try adding candidates[i] and recurse with remaining - candidates[i].
 * 3. Recurse with the SAME index i (not i+1) — that is what allows reuse.
 * 4. Base case: remaining == 0 → save a copy of the current combination.
 * 5. Prune: candidates[i] > remaining → break (sorted, so every later one is too big).
 *
 * WHY THIS WORKS:
 * - Recursing with i lets the same number be picked again (reuse).
 * - Never looking back before `start` keeps each combination in non-decreasing
 *   order, so [2,3] is generated but [3,2] never is — no duplicates.
 *
 * TIME  : O(n^(t/m)) — n candidates, t target, m minimum candidate value
 * SPACE : O(t/m) — max recursion depth
 * ============================================================
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
            current.add(candidates[i]);
            backtrack(candidates, remaining - candidates[i], i, current, result);
            current.remove(current.size() - 1);
        }
    }
}
