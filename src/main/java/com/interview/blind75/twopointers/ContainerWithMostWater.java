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
 * APPROACHES
 * ============================================================
 * APPROACH 1: Greedy two pointers  (primary — the only optimal approach)
 *   1. Start with the widest container: l = 0, r = n − 1.
 *   2. area = min(h[l], h[r]) × (r − l); track the max.
 *   3. Move the pointer at the shorter wall inward.
 *   Intuition: the shorter wall caps the area; moving the taller one can only
 *   shrink the width without raising that cap, so it can never help.
 *   TIME O(n) · SPACE O(1)
 *
 * ALTERNATIVES:
 *   Brute force over every pair is O(n²) time, O(1) space — no materially
 *   better alternative to the two-pointer greedy.
 * ============================================================
 */
public class ContainerWithMostWater {

    /** Approach 1 — greedy two pointers. TIME O(n) · SPACE O(1) */
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
