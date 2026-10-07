package com.interview.blind75.arrays;

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
 * APPROACH: HashSet with Sequence Start Detection
 * ============================================================
 * 1. Add all numbers to a HashSet for O(1) lookups.
 * 2. For each number, check if (num - 1) is NOT in the set.
 *    If so, num is the start of a new sequence.
 * 3. From the start, count how long the consecutive sequence extends.
 * 4. Track and return the maximum length found.
 *
 * WHY THIS WORKS:
 * Only processing sequence starts prevents re-counting. Each element
 * belongs to exactly one sequence, so total inner loop runs are O(n).
 *
 * TIME  : O(n)
 * SPACE : O(n)
 * ============================================================
 */
public class LongestConsecutiveSequence {

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
}
