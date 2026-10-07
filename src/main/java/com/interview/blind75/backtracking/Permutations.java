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
 * APPROACH: Backtracking with Used[] Boolean Array
 * ============================================================
 * 1. Use a boolean array `used` to track which elements are in the current permutation.
 * 2. At each level, try each unused element.
 * 3. Mark it used, recurse, then unmark (backtrack).
 * 4. When current.size() == nums.length, add a copy to result.
 *
 * WHY THIS WORKS:
 * Each position in the permutation chooses from all remaining unused elements,
 * giving n! permutations. The used[] array prevents duplicates within one path.
 *
 * TIME  : O(n * n!) — n! permutations, each takes O(n) to copy
 * SPACE : O(n) — recursion depth + used array
 * ============================================================
 */
public class Permutations {

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
}
