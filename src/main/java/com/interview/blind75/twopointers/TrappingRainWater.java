package com.interview.blind75.twopointers;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ============================================================
 * PROBLEM : Trapping Rain Water
 * LINK    : https://leetcode.com/problems/trapping-rain-water/
 * DIFFICULTY: Hard
 * PATTERN : Two Pointers
 * ============================================================
 *
 * DESCRIPTION:
 * Given n non-negative integers representing an elevation map where each bar
 * has width 1, compute how much water it can trap after raining.
 *
 * EXAMPLES:
 *   Input : height = [0,1,0,2,1,0,1,3,2,1,2,1]
 *   Output: 6
 *
 *   Input : height = [4,2,0,3,2,5]
 *   Output: 9
 *
 * CONSTRAINTS:
 *   - n == height.length
 *   - 1 <= n <= 2 * 10^4
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Two pointers with running maxima  (primary)
 *   1. l, r at the ends; maxLeft / maxRight = tallest bar seen from each side.
 *   2. Advance the side with the smaller max; water there = thatMax − height.
 *   Intuition: water at i = min(tallest left, tallest right) − height[i]; the
 *   smaller side's max is already the binding limit, so it's final.
 *   TIME O(n) · SPACE O(1)
 *
 * APPROACH 2: Prefix-max and suffix-max arrays
 *   1. leftMax[i] = tallest bar in [0..i]; rightMax[i] = tallest in [i..n−1].
 *   2. water += min(leftMax[i], rightMax[i]) − height[i] for every i.
 *   Intuition: the formula above, computed directly — easiest to reason about.
 *   TIME O(n) · SPACE O(n)
 *
 * APPROACH 3: Monotonic decreasing stack
 *   1. Keep indices of bars with decreasing heights.
 *   2. When a taller bar arrives, pop the "floor"; the water above it is bounded
 *      by the new top (left wall) and the current bar (right wall):
 *      width × (min(leftWall, rightWall) − floor).
 *   Intuition: fills water layer by layer, horizontally rather than per column.
 *   TIME O(n) · SPACE O(n)
 *
 * WHICH TO USE:
 *   Explain #2 first, then compress it into #1 (the expected O(1)-space answer).
 *   #3 is a strong extra if asked for a different technique — the same stack
 *   pattern solves Largest Rectangle in Histogram. Brute force is O(n²).
 * ============================================================
 */
public class TrappingRainWater {

    /** Approach 1 — two pointers with running maxima. TIME O(n) · SPACE O(1) */
    public int trap(int[] height) {
        int l = 0, r = height.length - 1;
        int maxLeft = height[l], maxRight = height[r];
        int totalWater = 0;

        while (l < r) {
            if (maxLeft <= maxRight) {
                l++;
                maxLeft = Math.max(maxLeft, height[l]);
                totalWater += maxLeft - height[l];
            } else {
                r--;
                maxRight = Math.max(maxRight, height[r]);
                totalWater += maxRight - height[r];
            }
        }
        return totalWater;
    }

    /** Approach 2 — prefix-max and suffix-max arrays. TIME O(n) · SPACE O(n) */
    public int trapPrefixArrays(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n], rightMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) rightMax[i] = Math.max(rightMax[i + 1], height[i]);

        int water = 0;
        for (int i = 0; i < n; i++) water += Math.min(leftMax[i], rightMax[i]) - height[i];
        return water;
    }

    /** Approach 3 — monotonic decreasing stack, water filled in layers. TIME O(n) · SPACE O(n) */
    public int trapMonotonicStack(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int water = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int floor = stack.pop();
                if (stack.isEmpty()) break; // no left wall
                int left = stack.peek();
                int width = i - left - 1;
                int bounded = Math.min(height[left], height[i]) - height[floor];
                water += width * bounded;
            }
            stack.push(i);
        }
        return water;
    }
}
