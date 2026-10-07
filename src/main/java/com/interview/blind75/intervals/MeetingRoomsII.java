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
 * APPROACH: Sort + Min-Heap of End Times
 * ============================================================
 * 1. Sort meetings by start time.
 * 2. Use a min-heap tracking end times of currently active meetings.
 * 3. For each meeting: if heap top (earliest ending) <= this meeting's start,
 *    reuse that room (poll). Always push current end time.
 * 4. Heap size = number of rooms needed.
 *
 * WHY THIS WORKS:
 * We greedily reuse the room that frees up earliest. The heap size at the end
 * is the peak concurrent meetings.
 *
 * TIME  : O(n log n)
 * SPACE : O(n)
 * ============================================================
 */
public class MeetingRoomsII {

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
}
