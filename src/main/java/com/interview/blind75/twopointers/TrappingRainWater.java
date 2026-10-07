package com.interview.blind75.twopointers;

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
 * APPROACH: Two Pointers with Running Max
 * ============================================================
 * 1. Use left and right pointers. Track maxLeft and maxRight.
 * 2. Process the side with the smaller max (bottleneck side).
 * 3. Water at position = max(0, minBound - height[i]).
 * 4. Move the pointer inward and update maxLeft or maxRight.
 *
 * WHY THIS WORKS:
 * Water level at any position is bounded by min(maxLeft, maxRight).
 * By processing the smaller-bound side, we know the exact water
 * that can be trapped above each bar.
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class TrappingRainWater {

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
}
