package com.interview.blind75.twopointers;

/**
 * ============================================================
 * PROBLEM : Container With Most Water
 * LINK    : https://leetcode.com/problems/container-with-most-water/
 * DIFFICULTY: Medium
 * PATTERN : Two Pointers
 * ============================================================
 *
 * DESCRIPTION:
 * Given n non-negative integers representing heights of lines, find two
 * lines that together with the x-axis form a container holding the most water.
 *
 * EXAMPLES:
 *   Input : height = [1,8,6,2,5,4,8,3,7]
 *   Output: 49
 *
 *   Input : height = [1,1]
 *   Output: 1
 *
 * CONSTRAINTS:
 *   - n == height.length
 *   - 2 <= n <= 10^5
 *
 * ============================================================
 * APPROACH: Greedy Two Pointers
 * ============================================================
 * 1. Start with left=0, right=n-1 (widest container).
 * 2. Compute area = min(h[l], h[r]) * (r - l).
 * 3. Move the pointer with the shorter height inward.
 *    (Moving the taller one can only decrease area.)
 * 4. Track maximum area across all iterations.
 *
 * WHY THIS WORKS:
 * The bottleneck is always the shorter wall. Moving the shorter pointer
 * is the only chance to find a taller wall that may increase area.
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int maxWater = 0;
        while (l < r) {
            int water = Math.min(height[l], height[r]) * (r - l);
            maxWater = Math.max(maxWater, water);
            if (height[l] <= height[r]) l++;
            else r--;
        }
        return maxWater;
    }
}
