package com.interview.blind75.linkedlist;

/**
 * ============================================================
 * PROBLEM : Reorder List
 * LINK    : https://leetcode.com/problems/reorder-list/
 * DIFFICULTY: Medium
 * PATTERN : Linked List
 * ============================================================
 *
 * DESCRIPTION:
 * Given the head of a singly linked list L0→L1→…→Ln-1→Ln, reorder it
 * to: L0→Ln→L1→Ln-1→L2→Ln-2→… Modify in-place without altering values.
 *
 * EXAMPLES:
 *   Input : 1→2→3→4
 *   Output: 1→4→2→3
 *
 *   Input : 1→2→3→4→5
 *   Output: 1→5→2→4→3
 *
 * CONSTRAINTS:
 *   - 1 <= number of nodes <= 5 * 10^4
 *
 * ============================================================
 * APPROACH: Find Middle + Reverse Second Half + Merge
 * ============================================================
 * 1. Find the middle node using slow/fast pointers.
 * 2. Reverse the second half of the list in-place.
 * 3. Merge the first half and reversed second half, alternating nodes.
 *
 * WHY THIS WORKS:
 * The target order takes one node from the front, then one from the back.
 * Reversing the second half lets us walk it from Ln backwards while walking
 * the first half forwards — alternating the two gives L0, Ln, L1, Ln-1, …
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class ReorderList {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // Step 1: Find middle
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse second half
        ListNode secondHalf = reverseList(slow.next);
        slow.next = null;

        // Step 3: Merge two halves
        ListNode first = head, second = secondHalf;
        while (second != null) {
            ListNode tmp1 = first.next, tmp2 = second.next;
            first.next = second;
            second.next = tmp1;
            first = tmp1;
            second = tmp2;
        }
    }

    private ListNode reverseList(ListNode head) {
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
