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
 * APPROACH: Binary Search on Answer Space
 * ============================================================
 * 1. Search space for k is [1, max(piles)].
 * 2. For a given k, total hours = sum(ceil(pile/k)).
 * 3. If hours <= h, k is feasible → try smaller (right = mid).
 * 4. If hours > h, k is too slow → try larger (left = mid+1).
 *
 * WHY THIS WORKS:
 * The feasibility function is monotone: higher k is always feasible
 * if lower k is. Binary search finds the minimum valid k.
 *
 * TIME  : O(n log m)  where m = max(piles)
 * SPACE : O(1)
 * ============================================================
 */
public class KokoEatingBananas {

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

    private boolean canFinish(int[] piles, int speed, int h) {
        long hours = 0;
        for (int pile : piles) hours += (pile + speed - 1) / speed;
        return hours <= h;
    }
}
