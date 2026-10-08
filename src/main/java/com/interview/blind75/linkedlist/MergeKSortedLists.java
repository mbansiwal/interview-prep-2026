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
 * APPROACHES  (n = total nodes, k = number of lists)
 * ============================================================
 * APPROACH 1: Min-Heap (Priority Queue)
 * 1. Push the head of each non-null list into a min-heap ordered by value.
 * 2. Poll the smallest node, append it to the result, and push its next (if any).
 * 3. Repeat until the heap is empty.
 * Intuition: the heap always holds the smallest remaining node of every list.
 * TIME  : O(n log k)
 * SPACE : O(k) — the heap
 *
 * APPROACH 2: Divide & Conquer (pairwise merging)
 * 1. Merge lists in pairs: (0,1), (2,3), … — the number of lists halves each round.
 * 2. Repeat until one list remains (use an interval = 1, 2, 4, … loop).
 * 3. Each pairwise merge is the classic "merge two sorted lists".
 * Intuition: like merge sort — log k rounds, each round touches every node once.
 * TIME  : O(n log k)
 * SPACE : O(1) extra — iterative merging in place (no heap, no recursion)
 *
 * WHICH TO USE:
 * Both are O(n log k). The heap is the most common answer and works well for
 * streaming input. Divide & conquer uses O(1) extra memory and is a strong
 * follow-up. (Merging lists one by one is O(n·k) — too slow for large k.)
 * ============================================================
 */
public class MergeKSortedLists {

    /** Approach 1 — Min-heap. TIME O(n log k) · SPACE O(k) · reuses input nodes */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
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

    /** Approach 2 — Divide & conquer pairwise merge. TIME O(n log k) · SPACE O(1) extra · mutates the array and nodes */
    public ListNode mergeKListsDivideConquer(ListNode[] lists) {
        if (lists.length == 0) return null;
        for (int interval = 1; interval < lists.length; interval *= 2) {
            for (int i = 0; i + interval < lists.length; i += interval * 2) {
                lists[i] = mergeTwo(lists[i], lists[i + interval]);
            }
        }
        return lists[0];
    }

    private ListNode mergeTwo(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) { curr.next = a; a = a.next; }
            else                { curr.next = b; b = b.next; }
            curr = curr.next;
        }
        curr.next = (a != null) ? a : b;
        return dummy.next;
    }
}
