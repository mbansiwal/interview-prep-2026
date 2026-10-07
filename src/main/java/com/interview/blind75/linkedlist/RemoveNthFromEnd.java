package com.interview.blind75.linkedlist;

/**
 * ============================================================
 * PROBLEM : Remove Nth Node From End of List
 * LINK    : https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * DIFFICULTY: Medium
 * PATTERN : Linked List
 * ============================================================
 *
 * DESCRIPTION:
 * Given the head of a linked list, remove the nth node from the
 * end of the list and return its head.
 *
 * EXAMPLES:
 *   Input : 1→2→3→4→5, n=2
 *   Output: 1→2→3→5
 *
 *   Input : 1, n=1
 *   Output: []
 *
 * CONSTRAINTS:
 *   - 1 <= n <= number of nodes
 *
 * ============================================================
 * APPROACH: Two Pointers with N+1 Gap
 * ============================================================
 * 1. Use a dummy node before head to handle edge cases.
 * 2. Advance fast pointer by n+1 steps from dummy.
 * 3. Move both slow and fast until fast reaches null.
 * 4. slow.next is the node to remove; set slow.next = slow.next.next.
 *
 * WHY THIS WORKS:
 * When fast reaches the end, slow is exactly n nodes behind. The +1
 * offset means slow is at the predecessor of the node to remove.
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy, slow = dummy;

        for (int i = 0; i <= n; i++) fast = fast.next;

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
