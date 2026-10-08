package com.interview.blind75.linkedlist;

/**
 * ============================================================
 * PROBLEM : Merge Two Sorted Lists
 * LINK    : https://leetcode.com/problems/merge-two-sorted-lists/
 * DIFFICULTY: Easy
 * PATTERN : Linked List
 * ============================================================
 *
 * DESCRIPTION:
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists into one sorted list and return its head.
 *
 * EXAMPLES:
 *   Input : 1→2→4, 1→3→4
 *   Output: 1→1→2→3→4→4
 *
 *   Input : [], []
 *   Output: []
 *
 * CONSTRAINTS:
 *   - 0 <= number of nodes <= 50
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Iterative Dummy-Node Merge
 * 1. Create a dummy node so the result head needs no special case.
 * 2. Compare the current nodes of both lists and attach the smaller one.
 * 3. Advance that list; when one runs out, attach the other's remaining tail.
 * Intuition: both lists are sorted, so the smaller head is always the next value.
 * TIME  : O(m + n)
 * SPACE : O(1)
 *
 * APPROACH 2: Recursive
 * 1. If either list is empty, return the other.
 * 2. Pick the smaller head; set its next to merge(rest of its list, other list).
 * 3. Return the smaller head.
 * Intuition: the merged list = smaller head + merge of everything else.
 * TIME  : O(m + n)
 * SPACE : O(m + n) — recursion stack
 *
 * WHICH TO USE:
 * Iterative is preferred: same time, O(1) space. The recursive version is
 * shorter and a common follow-up, but can overflow the stack on long lists.
 * ============================================================
 */
public class MergeTwoSortedLists {

    /** Approach 1 — Iterative dummy-node merge. TIME O(m+n) · SPACE O(1) · reuses input nodes */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        current.next = (list1 != null) ? list1 : list2;
        return dummy.next;
    }

    /** Approach 2 — Recursive merge. TIME O(m+n) · SPACE O(m+n) recursion stack · reuses input nodes */
    public ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        if (list1.val <= list2.val) {
            list1.next = mergeTwoListsRecursive(list1.next, list2);
            return list1;
        }
        list2.next = mergeTwoListsRecursive(list1, list2.next);
        return list2;
    }
}
