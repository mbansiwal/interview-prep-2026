package com.interview.blind75.linkedlist;

/**
 * ============================================================
 * PROBLEM : Add Two Numbers
 * LINK    : https://leetcode.com/problems/add-two-numbers/
 * DIFFICULTY: Medium
 * PATTERN : Linked List
 * ============================================================
 *
 * DESCRIPTION:
 * Given two non-empty linked lists representing non-negative integers
 * (digits stored in reverse order), add the two numbers and return
 * the sum as a linked list.
 *
 * EXAMPLES:
 *   Input : (2→4→3) + (5→6→4)
 *   Output: 7→0→8  (342 + 465 = 807)
 *
 *   Input : (9→9→9→9) + (9→9→9)
 *   Output: 8→9→9→0→1
 *
 * CONSTRAINTS:
 *   - 1 <= nodes <= 100
 *   - 0 <= Node.val <= 9
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Simulate Addition with Carry
 * 1. Traverse both lists together; sum = digit1 + digit2 + carry.
 * 2. New digit = sum % 10, new carry = sum / 10; append a node for the digit.
 * 3. Keep going while either list or the carry remains.
 * Intuition: the head holds the ones digit, so walking from the head is the same
 * as adding by hand from the rightmost column, carrying as we go.
 * TIME  : O(max(m,n))
 * SPACE : O(max(m,n)) — the output list; O(1) extra
 *
 * ALTERNATIVES: converting to integers overflows (up to 100 digits); a recursive
 * version has the same time but O(max(m,n)) stack — no materially better alternative.
 * ============================================================
 */
public class AddTwoNumbers {

    /** Approach 1 — Digit-by-digit addition with carry. TIME O(max(m,n)) · SPACE O(1) extra (output O(max(m,n))) */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) { sum += l1.val; l1 = l1.next; }
            if (l2 != null) { sum += l2.val; l2 = l2.next; }
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
        }
        return dummy.next;
    }
}
