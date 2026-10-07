package com.interview.blind75.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ============================================================
 * PROBLEM : Largest Rectangle in Histogram
 * LINK    : https://leetcode.com/problems/largest-rectangle-in-histogram/
 * DIFFICULTY: Hard
 * PATTERN : Stack (Monotonic)
 * ============================================================
 *
 * DESCRIPTION:
 * Given an array heights representing the histogram's bar heights where
 * each bar has width 1, return the area of the largest rectangle.
 *
 * EXAMPLES:
 *   Input : heights = [2,1,5,6,2,3]
 *   Output: 10
 *
 *   Input : heights = [2,4]
 *   Output: 4
 *
 * CONSTRAINTS:
 *   - 1 <= heights.length <= 10^5
 *   - 0 <= heights[i] <= 10^4
 *
 * ============================================================
 * APPROACH: Monotonic Increasing Stack
 * ============================================================
 * 1. Maintain a stack of (startIndex, height) pairs.
 * 2. For each bar, if shorter than stack top, pop and calculate area.
 *    The bar extends back to the start index of popped bars.
 * 3. After processing all bars, pop remaining stack entries using n as right boundary.
 *
 * WHY THIS WORKS:
 * Each popped bar's width extends from its stored startIndex to the current
 * position. The monotonic property ensures we compute the maximum possible
 * width for each height.
 *
 * TIME  : O(n)
 * SPACE : O(n)
 * ============================================================
 */
public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Deque<int[]> stack = new ArrayDeque<>(); // [startIndex, height]

        for (int i = 0; i < heights.length; i++) {
            int start = i;
            while (!stack.isEmpty() && stack.peek()[1] > heights[i]) {
                int[] top = stack.pop();
                maxArea = Math.max(maxArea, top[1] * (i - top[0]));
                start = top[0];
            }
            stack.push(new int[]{start, heights[i]});
        }

        for (int[] entry : stack) {
            maxArea = Math.max(maxArea, entry[1] * (heights.length - entry[0]));
        }
        return maxArea;
    }
}
