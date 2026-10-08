package com.interview.blind75.heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * ============================================================
 * PROBLEM : Last Stone Weight
 * LINK    : https://leetcode.com/problems/last-stone-weight/
 * DIFFICULTY: Easy
 * PATTERN : Heap
 * ============================================================
 *
 * DESCRIPTION:
 * Each turn, smash the two heaviest stones. If they are equal, both
 * destroyed. If different, the heavier one survives with weight = diff.
 * Return the weight of the last remaining stone (or 0 if none).
 *
 * EXAMPLES:
 *   Input : stones = [2,7,4,1,8,1]
 *   Output: 1
 *
 *   Input : stones = [1]
 *   Output: 1
 *
 * CONSTRAINTS:
 *   - 1 <= stones.length <= 30
 *   - 1 <= stones[i] <= 1000
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Max-Heap simulation
 * 1. Add all stones to a max-heap.
 * 2. While more than one stone: poll the two heaviest; push back the difference if non-zero.
 * 3. Return the remaining stone, or 0.
 * Intuition: the rules force "two heaviest", and a max-heap serves them in O(log n).
 * TIME  : O(n log n)
 * SPACE : O(n)
 *
 * APPROACH 2: Bucket counts (values are small: ≤ 1000)
 * 1. count[w] = number of stones of weight w; walk w from the heaviest down.
 * 2. With no stone "in hand", equal pairs at weight w cancel; an odd one left becomes the stone in hand.
 * 3. With a stone in hand, smash it with a stone of weight w. If the remainder is ≤ w,
 *    drop it back into its bucket (it'll be met again on the way down); otherwise keep holding it.
 * Intuition: the heaviest stone is always either "in hand" or at the current bucket.
 * TIME  : O(n + W), W = max stone weight
 * SPACE : O(W)
 *
 * WHICH TO USE:
 * The heap is the expected answer and works for any weights. Bucket counts win only
 * when weights are small relative to the number of stones (here n ≤ 30, so the heap is simpler).
 * ============================================================
 */
public class LastStoneWeight {

    /** Approach 1 — Max-heap simulation. TIME O(n log n) · SPACE O(n) */
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) maxHeap.offer(stone);

        while (maxHeap.size() > 1) {
            int a = maxHeap.poll();
            int b = maxHeap.poll();
            if (a != b) maxHeap.offer(a - b);
        }
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }

    /** Approach 2 — Bucket counts. TIME O(n + W) · SPACE O(W), W = max weight */
    public int lastStoneWeightBuckets(int[] stones) {
        int maxWeight = 0;
        for (int stone : stones) maxWeight = Math.max(maxWeight, stone);
        int[] count = new int[maxWeight + 1];
        for (int stone : stones) count[stone]++;

        int inHand = 0;
        int weight = maxWeight;
        while (weight > 0) {
            if (count[weight] == 0) {
                weight--;
            } else if (inHand == 0) {
                count[weight] %= 2;                // equal pairs destroy each other
                if (count[weight] == 1) inHand = weight;
                weight--;
            } else {
                count[weight]--;                   // smash the stone in hand with this one
                if (inHand - weight <= weight) {
                    count[inHand - weight]++;      // remainder rejoins the buckets
                    inHand = 0;
                } else {
                    inHand -= weight;              // still the heaviest; keep holding it
                }
            }
        }
        return inHand;
    }
}
