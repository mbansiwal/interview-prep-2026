package com.interview.blind75.twopointers;

import java.util.*;

/**
 * ============================================================
 * PROBLEM : 3Sum
 * LINK    : https://leetcode.com/problems/3sum/
 * DIFFICULTY: Medium
 * PATTERN : Two Pointers
 * ============================================================
 *
 * DESCRIPTION:
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j != k and nums[i] + nums[j] + nums[k] == 0.
 * The solution set must not contain duplicate triplets.
 *
 * EXAMPLES:
 *   Input : nums = [-1,0,1,2,-1,-4]
 *   Output: [[-1,-1,2],[-1,0,1]]
 *
 *   Input : nums = [0,0,0]
 *   Output: [[0,0,0]]
 *
 * CONSTRAINTS:
 *   - 3 <= nums.length <= 3000
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Sort + two pointers per fixed element  (primary)
 *   1. Sort. Fix i (skip a value equal to the previous i).
 *   2. l = i+1, r = n−1: move l/r toward a pair summing to −nums[i].
 *   3. After a hit, skip equal values on both sides to avoid duplicate triplets.
 *   Intuition: sorting turns the inner search into Two Sum II, and makes dedupe easy.
 *   TIME O(n²) · SPACE O(log n) sort stack, output excluded (mutates input)
 *
 * APPROACH 2: Sort + HashSet per fixed element
 *   1. Sort. Fix i (skip repeated values of nums[i]).
 *   2. Scan j > i; if (−nums[i] − nums[j]) was seen earlier in this scan, record
 *      the triplet; add nums[j] to the seen set.
 *   3. Collect triplets in a Set to drop duplicates.
 *   Intuition: the inner loop is the classic hash-based Two Sum.
 *   TIME O(n²) · SPACE O(n) for the seen set + result set (mutates input)
 *
 * WHICH TO USE:
 *   #1 is expected: same O(n²) time with O(1) extra space and cleaner dedupe.
 *   #2 is useful when you can't rely on pointer movement (e.g. Two Sum variants
 *   on unsorted data). Brute force (all triples) is O(n³).
 * ============================================================
 */
public class ThreeSum {

    /** Approach 1 — sort + two pointers. TIME O(n²) · SPACE O(log n) extra, mutates input */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return result;
    }

    /** Approach 2 — sort + HashSet Two Sum per pivot. TIME O(n²) · SPACE O(n), mutates input */
    public List<List<Integer>> threeSumHashSet(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> triplets = new LinkedHashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            Set<Integer> seen = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                int complement = -nums[i] - nums[j];
                // sorted input ⇒ nums[i] ≤ complement ≤ nums[j], so the triplet is already ordered
                if (seen.contains(complement)) triplets.add(List.of(nums[i], complement, nums[j]));
                seen.add(nums[j]);
            }
        }
        return new ArrayList<>(triplets);
    }
}
