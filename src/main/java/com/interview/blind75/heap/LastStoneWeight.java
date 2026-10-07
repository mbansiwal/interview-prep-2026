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
 * APPROACH: Max-Heap (Inverted Min-Heap)
 * ============================================================
 * 1. Add all stones to a max-heap (PriorityQueue with reverse order).
 * 2. While heap has > 1 stone: poll two heaviest.
 * 3. If they differ, push the difference back.
 * 4. Return heap.peek() or 0 if empty.
 *
 * WHY THIS WORKS:
 * There is no strategy to choose: the rules require smashing the two heaviest,
 * so we simply simulate. A max-heap hands us the two heaviest in O(log n) each round.
 *
 * TIME  : O(n log n)
 * SPACE : O(n)
 * ============================================================
 */
public class LastStoneWeight {

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
}
