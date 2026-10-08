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
 * APPROACHES
 * ============================================================
 * APPROACH 1: Iterative Three-Pointer Reversal
 * 1. Initialize prev=null, curr=head.
 * 2. At each step: save next=curr.next, point curr.next=prev.
 * 3. Advance: prev=curr, curr=next. Return prev.
 * Intuition: flip one link at a time; prev ends on the old tail (new head).
 * TIME  : O(n)
 * SPACE : O(1)
 *
 * APPROACH 2: Recursive
 * 1. Base case: empty list or single node → return it.
 * 2. Recursively reverse head.next; it returns the new head.
 * 3. Point the old next node back at head (head.next.next = head), then cut head.next.
 * Intuition: assume the rest is already reversed; attach head at its end.
 * TIME  : O(n)
 * SPACE : O(n) — recursion stack, one frame per node
 *
 * WHICH TO USE:
 * Iterative is the expected answer (O(1) space, no stack-overflow risk on long
 * lists). Interviewers often ask for the recursive version as a follow-up.
 * ============================================================
 */
public class ReverseLinkedList {

    /** Approach 1 — Iterative three-pointer. TIME O(n) · SPACE O(1) · mutates input */
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

    /** Approach 2 — Recursive. TIME O(n) · SPACE O(n) recursion stack · mutates input */
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
