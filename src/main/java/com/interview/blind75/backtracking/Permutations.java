package com.interview.blind75.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * PROBLEM : Permutations
 * LINK    : https://leetcode.com/problems/permutations/
 * DIFFICULTY: Medium
 * PATTERN : Backtracking
 * ============================================================
 *
 * DESCRIPTION:
 * Given an array nums of distinct integers, return all possible permutations.
 *
 * EXAMPLES:
 *   Input : nums = [1,2,3]
 *   Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 *   Input : nums = [0,1]
 *   Output: [[0,1],[1,0]]
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 6
 *   - All elements are distinct
 *
 * ============================================================
 * APPROACHES  (n = nums.length; output itself is O(n * n!))
 * ============================================================
 * APPROACH 1: Backtracking with a used[] array
 * 1. Track which elements are already in the current permutation.
 * 2. At each position try every unused element: mark, recurse, unmark.
 * 3. When the permutation is full, save a copy.
 * Intuition: position 1 has n choices, position 2 has n-1, … → n! leaves.
 * TIME  : O(n * n!)
 * SPACE : O(n) recursion + used[] + current (excluding output)
 *
 * APPROACH 2: In-place swapping
 * 1. Fix positions left to right: for position `first`, swap each element from first..n-1 into it.
 * 2. Recurse on first + 1, then swap back.
 * 3. When first == n, the array itself is a permutation — copy it.
 * Intuition: the prefix [0, first) is decided; the suffix is the pool of unused elements.
 * TIME  : O(n * n!)
 * SPACE : O(n) recursion only — no used[] or separate path list (works on a copy of the input)
 *
 * WHICH TO USE:
 * used[] is the clearest and extends naturally to Permutations II (duplicates).
 * Swapping saves the extra arrays; mention it as the space-lean variant.
 * ============================================================
 */
public class Permutations {

    /** Approach 1 — Backtracking with used[]. TIME O(n * n!) · SPACE O(n) (excluding output) */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> current,
                           List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            current.add(nums[i]);
            backtrack(nums, used, current, result);
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }

    /** Approach 2 — In-place swapping. TIME O(n * n!) · SPACE O(n) recursion (excluding output) */
    public List<List<Integer>> permuteSwap(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteFrom(nums.clone(), 0, result);
        return result;
    }

    private void permuteFrom(int[] arr, int first, List<List<Integer>> result) {
        if (first == arr.length) {
            List<Integer> permutation = new ArrayList<>(arr.length);
            for (int value : arr) permutation.add(value);
            result.add(permutation);
            return;
        }
        for (int i = first; i < arr.length; i++) {
            swap(arr, first, i);
            permuteFrom(arr, first + 1, result);
            swap(arr, first, i);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
    }
}
