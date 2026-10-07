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
 * APPROACH: Monotonic Decreasing Stack
 * ============================================================
 * 1. Maintain a stack of indices with decreasing temperatures.
 * 2. For each day i, pop all indices where temp[i] > temp[stack.peek()].
 * 3. For each popped index j, answer[j] = i - j.
 * 4. Push current index i onto stack.
 *
 * WHY THIS WORKS:
 * Stack holds indices awaiting a warmer day. When we find one, we resolve
 * all waiting days in one pass. Total pops == total pushes == O(n).
 *
 * TIME  : O(n)
 * SPACE : O(n)
 * ============================================================
 */
public class DailyTemperatures {

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
}
