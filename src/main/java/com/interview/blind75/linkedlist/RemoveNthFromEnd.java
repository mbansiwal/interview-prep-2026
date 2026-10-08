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
 * APPROACHES
 * ============================================================
 * APPROACH 1: One Pass — Two Pointers with N+1 Gap
 * 1. Use a dummy node before head so removing the head needs no special case.
 * 2. Advance fast by n+1 steps from dummy.
 * 3. Move slow and fast together until fast is null; slow is now the predecessor.
 * 4. Unlink: slow.next = slow.next.next.
 * Intuition: a fixed gap of n+1 means slow stops just before the target.
 * TIME  : O(n) — single pass
 * SPACE : O(1)
 *
 * APPROACH 2: Two Passes — Count Length First
 * 1. Walk the list once to count its length L.
 * 2. The target is at index L-n from the front; walk L-n steps from the dummy.
 * 3. Unlink the next node.
 * Intuition: "nth from the end" is just "(L-n)th from the start" once you know L.
 * TIME  : O(n) — two passes
 * SPACE : O(1)
 *
 * WHICH TO USE:
 * Both are O(n)/O(1). Interviewers usually ask "can you do it in one pass?",
 * which is Approach 1. Approach 2 is a fine first answer.
 * ============================================================
 */
public class RemoveNthFromEnd {

    /** Approach 1 — One pass with n+1 gap. TIME O(n) · SPACE O(1) · mutates input */
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

    /** Approach 2 — Two passes (count length). TIME O(n) · SPACE O(1) · mutates input */
    public ListNode removeNthFromEndTwoPass(ListNode head, int n) {
        int length = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) length++;

        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        for (int i = 0; i < length - n; i++) prev = prev.next;
        prev.next = prev.next.next;
        return dummy.next;
    }
}
