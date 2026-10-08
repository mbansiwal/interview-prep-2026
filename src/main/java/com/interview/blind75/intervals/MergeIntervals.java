package com.interview.blind75.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ============================================================
 * PROBLEM : Merge Intervals
 * LINK    : https://leetcode.com/problems/merge-intervals/
 * DIFFICULTY: Medium
 * PATTERN : Intervals
 * ============================================================
 *
 * DESCRIPTION:
 * Given an array of intervals, merge all overlapping intervals and return
 * an array of the non-overlapping intervals.
 *
 * EXAMPLES:
 *   Input : [[1,3],[2,6],[8,10],[15,18]]  →  [[1,6],[8,10],[15,18]]
 *   Input : [[1,4],[4,5]]                →  [[1,5]]
 *
 * CONSTRAINTS:
 *   - 1 <= intervals.length <= 10^4
 *
 * ============================================================
 * APPROACH 1: Sort + Linear Merge
 * ============================================================
 * 1. Sort intervals by start time.
 * 2. Maintain a `current` interval. For each next interval:
 *    - If it overlaps (next[0] <= current[1]): extend current[1].
 *    - Else: add current to result, start new current.
 * 3. Add the last current interval.
 *
 * WHY THIS WORKS:
 * After sorting, overlapping intervals are adjacent. Greedy extension
 * handles all chains of overlapping intervals.
 *
 * TIME  : O(n log n) — the sort dominates; the merge pass is O(n)
 * SPACE : O(n) for the output, plus the sort's own space (sorts and edits the input in place)
 *
 * ALTERNATIVES: comparing every pair is O(n²). If coordinates are small integers, a
 * counting/sweep array over the range gives O(n + R) time and O(R) space — rarely worth it.
 * No materially better general alternative: any comparison-based solution needs O(n log n).
 *
 * WHICH TO USE: sort + linear merge.
 * ============================================================
 */
public class MergeIntervals {

    /** Approach 1 — Sort + linear merge. TIME O(n log n) · SPACE O(n) (sorts/mutates input) */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> result = new ArrayList<>();
        int[] current = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= current[1]) {
                current[1] = Math.max(current[1], intervals[i][1]);
            } else {
                result.add(current);
                current = intervals[i];
            }
        }
        result.add(current);
        return result.toArray(new int[0][]);
    }
}
