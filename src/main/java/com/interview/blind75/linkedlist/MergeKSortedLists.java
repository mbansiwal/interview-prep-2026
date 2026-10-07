package com.interview.blind75.linkedlist;

import java.util.PriorityQueue;

/**
 * ============================================================
 * PROBLEM : Merge k Sorted Lists
 * LINK    : https://leetcode.com/problems/merge-k-sorted-lists/
 * DIFFICULTY: Hard
 * PATTERN : Linked List + Heap
 * ============================================================
 *
 * DESCRIPTION:
 * You are given an array of k linked-lists, each sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return its head.
 *
 * EXAMPLES:
 *   Input : [[1,4,5],[1,3,4],[2,6]]
 *   Output: 1→1→2→3→4→4→5→6
 *
 *   Input : []
 *   Output: []
 *
 * CONSTRAINTS:
 *   - k == lists.length
 *   - 0 <= k <= 10^4
 *
 * ============================================================
 * APPROACH: Min-Heap (Priority Queue)
 * ============================================================
 * 1. Insert the head of each non-null list into a min-heap ordered by value.
 * 2. Poll the minimum node, append to result.
 * 3. If the polled node has a next, push it to the heap.
 * 4. Continue until heap is empty.
 *
 * WHY THIS WORKS:
 * The heap always holds the current minimum across all k lists.
 * Each poll extracts the globally smallest unprocessed element.
 *
 * TIME  : O(n log k) where n = total nodes
 * SPACE : O(k)
 * ============================================================
 */
public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node : lists) {
            if (node != null) heap.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            curr.next = node;
            curr = curr.next;
            if (node.next != null) heap.offer(node.next);
        }
        return dummy.next;
    }
}
