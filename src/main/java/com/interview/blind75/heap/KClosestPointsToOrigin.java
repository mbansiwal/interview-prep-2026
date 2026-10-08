package com.interview.blind75.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

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
 * The answer may be returned in any order.
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
 * APPROACHES  (all compare squared distance x²+y², never sqrt)
 * ============================================================
 * APPROACH 1: Max-Heap of Size k
 * 1. Push each point into a max-heap ordered by distance.
 * 2. If the heap holds more than k points, evict the farthest.
 * 3. What remains are the k closest.
 * Intuition: the heap top is always the worst of the current best k.
 * TIME  : O(n log k)
 * SPACE : O(k)
 *
 * APPROACH 2: Quickselect
 * 1. Partition points around a random pivot distance (closer ones to the left).
 * 2. If the pivot lands at index k-1 we're done; otherwise continue on one side only.
 * 3. Return the first k points.
 * Intuition: like quicksort, but we only need the k smallest, not full order.
 * TIME  : O(n) average, O(n²) worst case
 * SPACE : O(1) extra (mutates the input array), O(k) for the output copy
 *
 * APPROACH 3: Sort by distance
 * 1. Sort all points by distance and take the first k.
 * TIME  : O(n log n)
 * SPACE : O(log n) sort stack + O(k) output (copies the input to avoid mutating it: O(n))
 *
 * WHICH TO USE:
 * Heap is the safe default and works for streams or huge inputs (only k in memory).
 * Quickselect is the "can you beat n log k?" follow-up. Sorting is the 1-line baseline.
 * ============================================================
 */
public class KClosestPointsToOrigin {

    /** Approach 1 — Max-heap of size k. TIME O(n log k) · SPACE O(k) */
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(distance(b), distance(a)));

        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) maxHeap.poll();
        }

        return maxHeap.toArray(new int[k][]);
    }

    /** Approach 2 — Quickselect. TIME O(n) avg, O(n²) worst · SPACE O(1) extra (mutates input) */
    public int[][] kClosestQuickselect(int[][] points, int k) {
        Random random = new Random(42);
        int lo = 0, hi = points.length - 1;
        while (lo < hi) {
            int pivotIndex = partition(points, lo, hi, lo + random.nextInt(hi - lo + 1));
            if (pivotIndex == k - 1) break;
            if (pivotIndex < k - 1) lo = pivotIndex + 1;
            else hi = pivotIndex - 1;
        }
        return Arrays.copyOf(points, k);
    }

    /** Approach 3 — Sort by distance. TIME O(n log n) · SPACE O(n) (sorted copy) */
    public int[][] kClosestSort(int[][] points, int k) {
        int[][] sorted = points.clone();
        Arrays.sort(sorted, (a, b) -> Integer.compare(distance(a), distance(b)));
        return Arrays.copyOf(sorted, k);
    }

    // Lomuto partition: points closer than the pivot end up on its left.
    private int partition(int[][] points, int lo, int hi, int pivotIndex) {
        int pivotDistance = distance(points[pivotIndex]);
        swap(points, pivotIndex, hi);
        int store = lo;
        for (int i = lo; i < hi; i++) {
            if (distance(points[i]) < pivotDistance) swap(points, i, store++);
        }
        swap(points, store, hi);
        return store;
    }

    private int distance(int[] p) {
        return p[0] * p[0] + p[1] * p[1];
    }

    private void swap(int[][] a, int i, int j) {
        int[] t = a[i]; a[i] = a[j]; a[j] = t;
    }
}
