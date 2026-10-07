package com.interview.blind75.intervals;

import java.util.Arrays;

/**
 * ============================================================
 * PROBLEM : Non-overlapping Intervals
 * LINK    : https://leetcode.com/problems/non-overlapping-intervals/
 * DIFFICULTY: Medium
 * PATTERN : Greedy / Intervals
 * ============================================================
 *
 * DESCRIPTION:
 * Given an array of intervals, return the minimum number of intervals
 * you need to remove to make the rest non-overlapping.
 *
 * EXAMPLES:
 *   Input : [[1,2],[2,3],[3,4],[1,3]]  →  Output: 1  (remove [1,3])
 *   Input : [[1,2],[1,2],[1,2]]        →  Output: 2
 *   Input : [[1,2],[2,3]]              →  Output: 0
 *
 * CONSTRAINTS:
 *   - 1 <= intervals.length <= 10^5
 *
 * ============================================================
 * APPROACH: Greedy — Sort by End, Greedily Keep Non-Overlapping
 * ============================================================
 * Sort by end time. Keep a `prevEnd` (end of last kept interval).
 * For each interval:
 * - If start >= prevEnd: no overlap, keep it, update prevEnd.
 * - Else: overlap, remove this one (increment count).
 *
 * WHY THIS WORKS:
 * Sorting by end time and keeping earliest-ending intervals is the classic
 * Activity Selection problem. It maximizes the number of kept intervals,
 * minimizing removals.
 *
 * TIME  : O(n log n)
 * SPACE : O(1) extra, plus the sort's own space (O(log n) to O(n))
 * ============================================================
 */
public class NonOverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int prevEnd = Integer.MIN_VALUE, removals = 0;
        for (int[] interval : intervals) {
            if (interval[0] >= prevEnd) {
                prevEnd = interval[1];
            } else {
                removals++;
            }
        }
        return removals;
    }
}
