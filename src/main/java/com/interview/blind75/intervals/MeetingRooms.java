package com.interview.blind75.intervals;

import java.util.Arrays;

/**
 * ============================================================
 * PROBLEM : Meeting Rooms
 * LINK    : https://leetcode.com/problems/meeting-rooms/ (Premium)
 *           https://neetcode.io/problems/meeting-schedule
 * DIFFICULTY: Easy
 * PATTERN : Intervals
 * ============================================================
 *
 * DESCRIPTION:
 * Given an array of meeting time intervals, determine if a person can attend
 * all meetings (i.e., no two meetings overlap).
 *
 * EXAMPLES:
 *   Input : [[0,30],[5,10],[15,20]]  →  Output: false  ([0,30] overlaps [5,10])
 *   Input : [[7,10],[2,4]]           →  Output: true
 *
 * CONSTRAINTS:
 *   - 0 <= intervals.length <= 10^4
 *
 * ============================================================
 * APPROACH: Sort by Start, Check Adjacent Overlaps
 * ============================================================
 * 1. Sort by start time.
 * 2. If any intervals[i][0] < intervals[i-1][1], they overlap → false.
 *
 * WHY THIS WORKS:
 * After sorting by start, overlapping intervals are adjacent.
 * Overlap iff next start < previous end.
 *
 * TIME  : O(n log n)
 * SPACE : O(1) extra, plus the sort's own space (O(log n) to O(n))
 * ============================================================
 */
public class MeetingRooms {

    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) return false;
        }
        return true;
    }
}
