package com.interview.blind75.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ============================================================
 * PROBLEM : Longest Consecutive Sequence
 * LINK    : https://leetcode.com/problems/longest-consecutive-sequence/
 * DIFFICULTY: Medium
 * PATTERN : Arrays & Hashing
 * ============================================================
 *
 * DESCRIPTION:
 * Given an unsorted array of integers nums, return the length of the
 * longest consecutive elements sequence. Must run in O(n) time.
 *
 * EXAMPLES:
 *   Input : nums = [100,4,200,1,3,2]
 *   Output: 4  (sequence: [1,2,3,4])
 *
 *   Input : nums = [0,3,7,2,5,8,4,6,0,1]
 *   Output: 9
 *
 * CONSTRAINTS:
 *   - 0 <= nums.length <= 10^5
 *   - -10^9 <= nums[i] <= 10^9
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: HashSet + count only from sequence starts  (primary)
 *   1. Put every number in a HashSet.
 *   2. A number starts a sequence if (num − 1) is not in the set.
 *   3. From each start, count num+1, num+2, … while present; track the max.
 *   Intuition: counting only from starts means each number is visited O(1) times.
 *   TIME O(n) · SPACE O(n)
 *
 * APPROACH 2: Sort, then scan
 *   1. Sort the array.
 *   2. Walk it: equal neighbour → skip, neighbour + 1 → extend run, otherwise reset.
 *   Intuition: after sorting, consecutive values are adjacent.
 *   TIME O(n log n) · SPACE O(log n) sort stack (mutates input)
 *
 * WHICH TO USE:
 *   The problem demands O(n), so #1 is the answer. #2 is a fine starting point
 *   to mention, and wins if memory is tight. Remember duplicates in #2.
 * ============================================================
 */
public class LongestConsecutiveSequence {

    /** Approach 1 — HashSet, count from sequence starts. TIME O(n) · SPACE O(n) */
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) numSet.add(num);

        int maxLength = 0;
        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int length = 1;
                while (numSet.contains(num + length)) length++;
                maxLength = Math.max(maxLength, length);
            }
        }
        return maxLength;
    }

    /** Approach 2 — sort then scan runs. TIME O(n log n) · SPACE O(log n), mutates input */
    public int longestConsecutiveSorting(int[] nums) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        int best = 1, run = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) continue;
            run = (nums[i] == nums[i - 1] + 1) ? run + 1 : 1;
            best = Math.max(best, run);
        }
        return best;
    }
}
