package com.interview.blind75.heap;

import java.util.PriorityQueue;

/**
 * ============================================================
 * PROBLEM : K Closest Points to Origin
 * LINK    : https://leetcode.com/problems/k-closest-points-to-origin/
 * DIFFICULTY: Medium
 * PATTERN : Heap
 * ============================================================
 *
 * DESCRIPTION:
 * Given an array of points on the X-Y plane and integer k, return the
 * k closest points to the origin (0,0). Euclidean distance is used.
 *
 * EXAMPLES:
 *   Input : points = [[1,3],[-2,2]], k = 1
 *   Output: [[-2,2]]
 *
 *   Input : points = [[3,3],[5,-1],[-2,4]], k = 2
 *   Output: [[3,3],[-2,4]]
 *
 * CONSTRAINTS:
 *   - 1 <= k <= points.length <= 10^4
 *
 * ============================================================
 * APPROACH: Max-Heap of Size k
 * ============================================================
 * 1. Use a max-heap ordered by squared distance (avoid sqrt).
 * 2. For each point, add to heap. If size > k, poll the farthest.
 * 3. Remaining heap contains the k closest points.
 *
 * WHY THIS WORKS:
 * Maintaining only the k closest means evicting the current farthest
 * whenever we exceed k. Max-heap gives us the farthest in O(log k).
 *
 * TIME  : O(n log k)
 * SPACE : O(k)
 *
 * FOLLOW-UP: Quickselect on squared distance — O(n) average. Partition the
 * points around a pivot distance until the first k slots hold the k closest.
 * ============================================================
 */
public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(b[0]*b[0] + b[1]*b[1], a[0]*a[0] + a[1]*a[1]));

        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) maxHeap.poll();
        }

        return maxHeap.toArray(new int[k][]);
    }
}
