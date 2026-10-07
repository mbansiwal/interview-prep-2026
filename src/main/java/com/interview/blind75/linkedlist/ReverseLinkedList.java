package com.interview.blind75.linkedlist;

/**
 * ============================================================
 * PROBLEM : Reverse Linked List
 * LINK    : https://leetcode.com/problems/reverse-linked-list/
 * DIFFICULTY: Easy
 * PATTERN : Linked List
 * ============================================================
 *
 * DESCRIPTION:
 * Given the head of a singly linked list, reverse the list,
 * and return the reversed list.
 *
 * EXAMPLES:
 *   Input : 1→2→3→4→5
 *   Output: 5→4→3→2→1
 *
 *   Input : 1→2
 *   Output: 2→1
 *
 * CONSTRAINTS:
 *   - 0 <= number of nodes <= 5000
 *   - -5000 <= Node.val <= 5000
 *
 * ============================================================
 * APPROACH: Iterative Three-Pointer Reversal
 * ============================================================
 * 1. Initialize prev=null, curr=head.
 * 2. At each step: save next=curr.next, point curr.next=prev.
 * 3. Advance: prev=curr, curr=next.
 * 4. Return prev (new head after loop).
 *
 * WHY THIS WORKS:
 * We reverse one link at a time. After processing all nodes, prev
 * points to the old tail, which is now the new head.
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
