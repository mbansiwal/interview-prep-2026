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
 * APPROACH: Dummy Node Merge
 * ============================================================
 * 1. Create a dummy node to simplify edge case handling.
 * 2. Compare current nodes in l1 and l2, attach the smaller one.
 * 3. Advance the pointer of whichever list was consumed.
 * 4. Attach remaining tail of non-exhausted list.
 *
 * WHY THIS WORKS:
 * The dummy node avoids a null-check for the result head. Since both
 * lists are sorted, comparing head nodes always picks the global minimum.
 *
 * TIME  : O(m + n)
 * SPACE : O(1)
 * ============================================================
 */
public class MergeTwoSortedLists {

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
}
