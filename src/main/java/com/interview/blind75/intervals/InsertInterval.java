package com.interview.blind75.intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * PROBLEM : Insert Interval
 * LINK    : https://leetcode.com/problems/insert-interval/
 * DIFFICULTY: Medium
 * PATTERN : Intervals
 * ============================================================
 *
 * DESCRIPTION:
 * Given a sorted list of non-overlapping intervals and a new interval,
 * insert the new interval and merge if necessary. Return the resulting list.
 *
 * EXAMPLES:
 *   Input : intervals=[[1,3],[6,9]], newInterval=[2,5]  →  [[1,5],[6,9]]
 *   Input : intervals=[[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval=[4,8]
 *   Output: [[1,2],[3,10],[12,16]]
 *
 * CONSTRAINTS:
 *   - intervals is sorted by start time, non-overlapping
 *
 * ============================================================
 * APPROACH: Three-Phase Linear Scan
 * ============================================================
 * Phase 1: Add all intervals that end before newInterval starts (no overlap).
 * Phase 2: Merge all intervals overlapping with newInterval (expand bounds).
 * Phase 3: Add remaining intervals.
 *
 * WHY THIS WORKS:
 * An interval i overlaps with new if i[1] >= new[0] AND i[0] <= new[1].
 * We expand the new interval to cover all overlapping ones, then insert once.
 *
 * TIME  : O(n)
 * SPACE : O(n)
 * ============================================================
 */
public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0, n = intervals.length;

        while (i < n && intervals[i][1] < newInterval[0]) result.add(intervals[i++]);

        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        while (i < n) result.add(intervals[i++]);

        return result.toArray(new int[0][]);
    }
}
