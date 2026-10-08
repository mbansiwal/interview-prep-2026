package com.interview.blind75.intervals;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * ============================================================
 * PROBLEM : Meeting Rooms II
 * LINK    : https://leetcode.com/problems/meeting-rooms-ii/ (Premium)
 *           https://neetcode.io/problems/meeting-schedule-ii
 * DIFFICULTY: Medium
 * PATTERN : Intervals / Heap
 * ============================================================
 *
 * DESCRIPTION:
 * Given an array of meeting time intervals, return the minimum number of
 * conference rooms required.
 *
 * EXAMPLES:
 *   Input : [[0,30],[5,10],[15,20]]  →  Output: 2
 *   Input : [[7,10],[2,4]]           →  Output: 1
 *
 * CONSTRAINTS:
 *   - 1 <= intervals.length <= 10^4
 *
 * ============================================================
 * APPROACHES  (n = number of meetings)
 * ============================================================
 * APPROACH 1: Sort by start + min-heap of end times
 * 1. Sort meetings by start time.
 * 2. Keep a min-heap of end times of meetings currently holding a room.
 * 3. If the earliest-ending room is free by this meeting's start, reuse it (poll).
 *    Always push this meeting's end. The heap size at the end is the room count.
 * Intuition: always hand over the room that frees up first.
 * TIME  : O(n log n)
 * SPACE : O(n) heap (sorts the input in place)
 *
 * APPROACH 2: Chronological ordering (two sorted arrays / sweep line)
 * 1. Copy all start times into one array and all end times into another; sort both.
 * 2. Walk the starts in order with a pointer into the ends:
 *    if the next start is before the earliest unfinished end, we need a new room;
 *    otherwise one meeting has ended, so advance the end pointer and reuse its room.
 * Intuition: only the ORDER of start/end events matters, not which meeting is which —
 * the answer is the maximum number of meetings in progress at once.
 * TIME  : O(n log n) — two sorts, then a linear walk
 * SPACE : O(n) for the two arrays (input untouched)
 *
 * ALTERNATIVE: sweep line with a TreeMap (time → +1 at start, −1 at end), then take the max
 * running sum. Same O(n log n); the most general form (works for "max overlap" questions).
 *
 * WHICH TO USE:
 * The heap is the most common answer and maps directly to "assign rooms". The two-array
 * sweep is just as optimal, has smaller constants and is a great follow-up.
 * ============================================================
 */
public class MeetingRoomsII {

    /** Approach 1 — Sort + min-heap of end times. TIME O(n log n) · SPACE O(n) (sorts input) */
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        for (int[] interval : intervals) {
            if (!endTimes.isEmpty() && endTimes.peek() <= interval[0]) {
                endTimes.poll();
            }
            endTimes.offer(interval[1]);
        }
        return endTimes.size();
    }

    /** Approach 2 — Chronological ordering of starts and ends. TIME O(n log n) · SPACE O(n) */
    public int minMeetingRoomsChronological(int[][] intervals) {
        int n = intervals.length;
        int[] starts = new int[n], ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);

        int rooms = 0, endPointer = 0;
        for (int start : starts) {
            if (start < ends[endPointer]) {
                rooms++;              // nobody has left yet → open a new room
            } else {
                endPointer++;         // a meeting finished → reuse its room
            }
        }
        return rooms;
    }
}
