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
 * APPROACHES  (n = intervals.length; input is sorted and non-overlapping)
 * ============================================================
 * APPROACH 1: Three-phase linear scan
 * Phase 1: copy intervals that end before newInterval starts.
 * Phase 2: absorb every interval that overlaps newInterval (widen its bounds).
 * Phase 3: copy the rest.
 * Intuition: intervals i and new overlap iff i[1] >= new[0] AND i[0] <= new[1].
 * TIME  : O(n)
 * SPACE : O(n) for the output (note: widens the passed-in newInterval array)
 *
 * APPROACH 2: Binary search for the overlap range
 * 1. lo = first interval whose end >= new.start; hi = first interval whose start > new.end.
 * 2. Intervals [lo, hi) overlap newInterval — merge them with it into one.
 * 3. Output = intervals[0, lo) + merged + intervals[hi, n).
 * Intuition: because the list is sorted and disjoint, both boundaries are monotone, so
 * finding where the overlap starts and ends is O(log n).
 * TIME  : O(log n) to locate + O(n) to build the output array → O(n) overall
 * SPACE : O(n) for the output (does not modify the inputs)
 *
 * WHICH TO USE:
 * The linear scan is the expected, bug-resistant answer — the output copy is O(n) anyway.
 * Mention binary search when the data lives in a structure that can splice in O(log n)
 * (e.g. a TreeMap of start → end), where it makes inserts truly logarithmic.
 * ============================================================
 */
public class InsertInterval {

    /** Approach 1 — Three-phase linear scan. TIME O(n) · SPACE O(n) output (mutates newInterval) */
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

    /** Approach 2 — Binary search for the overlap range. TIME O(n) (O(log n) search) · SPACE O(n) output */
    public int[][] insertBinarySearch(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int lo = firstIndex(intervals, newInterval[0], true);    // first end >= new start
        int hi = firstIndex(intervals, newInterval[1], false);   // first start > new end

        int start = newInterval[0], end = newInterval[1];
        if (lo < hi) {
            start = Math.min(start, intervals[lo][0]);
            end = Math.max(end, intervals[hi - 1][1]);
        }

        int[][] result = new int[n - (hi - lo) + 1][];
        int k = 0;
        for (int i = 0; i < lo; i++) result[k++] = intervals[i];
        result[k++] = new int[]{start, end};
        for (int i = hi; i < n; i++) result[k++] = intervals[i];
        return result;
    }

    // byEnd: first i with intervals[i][1] >= target; otherwise first i with intervals[i][0] > target.
    private int firstIndex(int[][] intervals, int target, boolean byEnd) {
        int lo = 0, hi = intervals.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            boolean matches = byEnd ? intervals[mid][1] >= target : intervals[mid][0] > target;
            if (matches) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}
