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
 * APPROACHES
 * ============================================================
 * APPROACH 1: Monotonic increasing stack of (start, height)  (primary)
 *   1. Scan bars; while the stack top is taller than the current bar, pop it and
 *      compute its area up to i. The current bar inherits the popped start.
 *   2. After the scan, every bar left on the stack extends to the end.
 *   Intuition: a bar's rectangle ends at the first shorter bar to its right and
 *   starts just after the first shorter bar to its left.
 *   TIME O(n) · SPACE O(n)
 *
 * APPROACH 2: Nearest-smaller boundary arrays
 *   1. leftSmaller[i] = index of the nearest shorter bar to the left (or −1),
 *      found by jumping: j = leftSmaller[j] while heights[j] ≥ heights[i].
 *   2. rightSmaller[i] likewise to the right (or n).
 *   3. area(i) = heights[i] × (rightSmaller[i] − leftSmaller[i] − 1).
 *   Intuition: the same boundaries as #1, computed explicitly — easy to verify.
 *   TIME O(n) amortized · SPACE O(n)
 *
 * WHICH TO USE:
 *   #1 is the classic one-pass answer. #2 is easier to explain step by step.
 *   Brute force (expand from every bar) is O(n²); divide and conquer on the
 *   minimum bar is O(n log n) average.
 * ============================================================
 */
public class LargestRectangleInHistogram {

    /** Approach 1 — monotonic increasing stack. TIME O(n) · SPACE O(n) */
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

    /** Approach 2 — nearest-smaller boundary arrays with jumps. TIME O(n) amortized · SPACE O(n) */
    public int largestRectangleAreaBoundaries(int[] heights) {
        int n = heights.length;
        int[] leftSmaller = new int[n], rightSmaller = new int[n];
        for (int i = 0; i < n; i++) {
            int j = i - 1;
            while (j >= 0 && heights[j] >= heights[i]) j = leftSmaller[j];
            leftSmaller[i] = j;
        }
        for (int i = n - 1; i >= 0; i--) {
            int j = i + 1;
            while (j < n && heights[j] >= heights[i]) j = rightSmaller[j];
            rightSmaller[i] = j;
        }
        int best = 0;
        for (int i = 0; i < n; i++) {
            best = Math.max(best, heights[i] * (rightSmaller[i] - leftSmaller[i] - 1));
        }
        return best;
    }
}
