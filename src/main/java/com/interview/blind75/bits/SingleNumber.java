package com.interview.blind75.bits;

import java.util.HashSet;
import java.util.Set;

/**
 * ============================================================
 * PROBLEM : Single Number
 * LINK    : https://leetcode.com/problems/single-number/
 * DIFFICULTY: Easy
 * PATTERN : Bit Manipulation
 * ============================================================
 *
 * DESCRIPTION:
 * Given a non-empty array where every element appears twice except for one,
 * find the single element. Must be O(n) time and O(1) space.
 *
 * EXAMPLES:
 *   Input : [2,2,1]       →  Output: 1
 *   Input : [4,1,2,1,2]   →  Output: 4
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 3 * 10^4
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * Sorting and checking neighbours is O(n log n). A HashMap of counts is O(n)/O(n).
 *
 * APPROACH 1: XOR everything                               → singleNumber
 *   1. result = 0; result ^= every element.
 *   Intuition: a ^ a = 0, a ^ 0 = a, and XOR is commutative/associative, so
 *   every pair cancels and only the single number survives.
 *   TIME  : O(n)    SPACE : O(1)
 *
 * APPROACH 2: HashSet toggle                               → singleNumberHashSet
 *   1. Add a number the first time it's seen, remove it the second time.
 *   2. The one element left in the set is the answer.
 *   Intuition: the obvious "who's unpaired?" approach — correct, but O(n) space,
 *   which this problem explicitly forbids.
 *   TIME  : O(n)    SPACE : O(n)
 *
 * WHICH TO USE:
 * Approach 1 is the only one that meets the O(1)-space requirement. Start from
 * Approach 2 if you need to, then show how XOR removes the set.
 * ============================================================
 */
public class SingleNumber {

    /** Approach 1 — XOR all elements. TIME O(n) · SPACE O(1) */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int n : nums) result ^= n;
        return result;
    }

    /** Approach 2 — HashSet add/remove toggle. TIME O(n) · SPACE O(n) */
    public int singleNumberHashSet(int[] nums) {
        Set<Integer> unpaired = new HashSet<>();
        for (int n : nums) {
            if (!unpaired.add(n)) unpaired.remove(n);
        }
        return unpaired.iterator().next();
    }
}
