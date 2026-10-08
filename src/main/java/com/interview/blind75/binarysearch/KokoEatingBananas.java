package com.interview.blind75.binarysearch;

/**
 * ============================================================
 * PROBLEM : Koko Eating Bananas
 * LINK    : https://leetcode.com/problems/koko-eating-bananas/
 * DIFFICULTY: Medium
 * PATTERN : Binary Search
 * ============================================================
 *
 * DESCRIPTION:
 * Koko has n piles of bananas and h hours to eat them.
 * Find the minimum eating speed k (bananas/hour) such that
 * Koko can finish all piles within h hours.
 *
 * EXAMPLES:
 *   Input : piles = [3,6,7,11], h = 8
 *   Output: 4
 *
 *   Input : piles = [30,11,23,4,20], h = 5
 *   Output: 30
 *
 * CONSTRAINTS:
 *   - 1 <= piles.length <= h
 *   - 1 <= piles[i] <= 10^9
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Binary search on the answer (eating speed)  (primary — the only optimal approach)
 *   1. The speed k lies in [1, max(piles)].
 *   2. canFinish(k): hours = Σ ceil(pile / k) ≤ h. It's monotonic in k.
 *   3. Binary-search for the smallest k where canFinish is true.
 *   Intuition: if Koko can finish at speed k, she can at any faster speed —
 *   so "can finish" flips from false to true exactly once.
 *   TIME O(n · log max(piles)) · SPACE O(1)
 *
 * ALTERNATIVES:
 *   Brute force — try k = 1, 2, 3, … until it works — is O(n · max(piles)).
 *   No materially better alternative to binary search on the answer.
 * ============================================================
 */
public class KokoEatingBananas {

    /** Approach 1 — binary search on speed. TIME O(n · log max(piles)) · SPACE O(1) */
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 0;
        for (int pile : piles) right = Math.max(right, pile);

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinish(piles, mid, h)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    // TIME O(n): total hours at this speed, using ceil(a / b) = (a + b − 1) / b
    private boolean canFinish(int[] piles, int speed, int h) {
        long hours = 0;
        for (int pile : piles) hours += (pile + speed - 1) / speed;
        return hours <= h;
    }
}
