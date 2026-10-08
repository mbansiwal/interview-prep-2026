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
 * APPROACHES  (n = intervals.length)
 * ============================================================
 * APPROACH 1: Greedy — sort by END, keep the earliest-finishing intervals
 * 1. Sort by end time; track prevEnd (end of the last kept interval).
 * 2. If an interval starts at/after prevEnd, keep it and move prevEnd; otherwise remove it.
 * Intuition: classic activity selection — finishing earliest leaves the most room
 * for everything after it, which maximises kept intervals (so minimises removals).
 * TIME  : O(n log n)
 * SPACE : O(1) extra, plus the sort's own space (O(log n) to O(n)) — sorts the input in place
 *
 * APPROACH 2: Greedy — sort by START, on overlap drop the one that ends later
 * 1. Sort by start time; track prevEnd of the last kept interval.
 * 2. No overlap → keep it (prevEnd = its end). Overlap → count a removal and keep
 *    whichever of the two ends earlier (prevEnd = min(prevEnd, its end)).
 * Intuition: same exchange argument — between two clashing intervals, the earlier-ending
 * one can never be a worse choice.
 * TIME  : O(n log n)
 * SPACE : O(1) extra, plus the sort — sorts the input in place
 *
 * WHICH TO USE:
 * Sort-by-end is the textbook answer and the easiest to prove. Sort-by-start is handy when
 * you've already sorted by start (as for Merge Intervals) — just remember the min(prevEnd, end).
 * ============================================================
 */
public class NonOverlappingIntervals {

    /** Approach 1 — Sort by end, activity selection. TIME O(n log n) · SPACE O(1) extra (sorts input) */
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

    /** Approach 2 — Sort by start, keep the earlier end on overlap. TIME O(n log n) · SPACE O(1) extra (sorts input) */
    public int eraseOverlapIntervalsByStart(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int prevEnd = intervals[0][1], removals = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= prevEnd) {
                prevEnd = intervals[i][1];
            } else {
                removals++;
                prevEnd = Math.min(prevEnd, intervals[i][1]);
            }
        }
        return removals;
    }
}
