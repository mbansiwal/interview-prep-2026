package com.interview.blind75.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ============================================================
 * PROBLEM : Daily Temperatures
 * LINK    : https://leetcode.com/problems/daily-temperatures/
 * DIFFICULTY: Medium
 * PATTERN : Stack (Monotonic)
 * ============================================================
 *
 * DESCRIPTION:
 * Given an array temperatures, return an array answer where answer[i]
 * is the number of days you have to wait after the ith day to get a
 * warmer temperature. If no future day is warmer, answer[i] = 0.
 *
 * EXAMPLES:
 *   Input : temperatures = [73,74,75,71,69,72,76,73]
 *   Output: [1,1,4,2,1,1,0,0]
 *
 *   Input : temperatures = [30,40,50,60]
 *   Output: [1,1,1,0]
 *
 * CONSTRAINTS:
 *   - 1 <= temperatures.length <= 10^5
 *   - 30 <= temperatures[i] <= 100
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Monotonic decreasing stack of indices  (primary)
 *   1. Scan left to right, keeping indices whose temperatures are decreasing.
 *   2. A warmer day pops every cooler index; answer[j] = i − j.
 *   Intuition: each index waits on the stack until its first warmer day arrives.
 *   TIME O(n) — each index pushed and popped once · SPACE O(n)
 *
 * APPROACH 2: Backward scan with jumps (no stack)
 *   1. Scan right to left, tracking the hottest temperature seen so far.
 *   2. If today is at least the hottest, answer stays 0.
 *   3. Otherwise, start at i+1 and jump by answer[j] until a warmer day is found.
 *   Intuition: answer[j] already tells us the next day warmer than j, so we skip
 *   every day that can't be the answer.
 *   TIME O(n) amortized · SPACE O(1) extra (output excluded)
 *
 * WHICH TO USE:
 *   #1 is the textbook monotonic-stack answer. #2 is the follow-up for O(1)
 *   extra space. Brute force (scan forward from each day) is O(n²).
 * ============================================================
 */
public class DailyTemperatures {

    /** Approach 1 — monotonic decreasing stack. TIME O(n) · SPACE O(n) */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int j = stack.pop();
                answer[j] = i - j;
            }
            stack.push(i);
        }
        return answer;
    }

    /** Approach 2 — backward scan, jumping via known answers. TIME O(n) amortized · SPACE O(1) extra */
    public int[] dailyTemperaturesBackwardJumps(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        int hottest = 0;
        for (int i = n - 1; i >= 0; i--) {
            int t = temperatures[i];
            if (t >= hottest) {
                hottest = t;
                continue; // nothing warmer to the right
            }
            int days = 1;
            while (temperatures[i + days] <= t) days += answer[i + days];
            answer[i] = days;
        }
        return answer;
    }
}
