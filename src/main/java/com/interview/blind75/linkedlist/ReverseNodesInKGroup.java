package com.interview.blind75.linkedlist;

/**
 * ============================================================
 * PROBLEM : Reverse Nodes in k-Group
 * LINK    : https://leetcode.com/problems/reverse-nodes-in-k-group/
 * DIFFICULTY: Hard
 * PATTERN : Linked List
 * ============================================================
 *
 * DESCRIPTION:
 * Given a linked list, reverse the nodes of the list k at a time,
 * and return the modified list. If the number of nodes is not a
 * multiple of k, the last remaining nodes stay in their original order.
 *
 * EXAMPLES:
 *   Input : 1→2→3→4→5, k=2
 *   Output: 2→1→4→3→5
 *
 *   Input : 1→2→3→4→5, k=3
 *   Output: 3→2→1→4→5
 *
 * CONSTRAINTS:
 *   - 1 <= k <= number of nodes <= 5000
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Iterative Group Reversal with a Dummy Node
 * 1. groupPrev = the node just before the current group (starts at a dummy).
 * 2. Walk k steps to find the group's last node (kth); if fewer than k remain, stop.
 * 3. Reverse the k nodes in place, pointing the group's first node at groupNext.
 * 4. Link groupPrev to kth; the old first node becomes groupPrev for the next round.
 * Intuition: each group is an ordinary list reversal, stitched between groupPrev and groupNext.
 * TIME  : O(n) — each node visited twice (find kth, then reverse)
 * SPACE : O(1)
 *
 * APPROACH 2: Recursive
 * 1. Check that k nodes exist from head; if not, return head unchanged.
 * 2. Reverse those k nodes; the old head is now the group's tail.
 * 3. Set oldHead.next = reverseKGroup(the rest), and return the new group head.
 * Intuition: "reverse the first group, then trust recursion for the rest".
 * TIME  : O(n)
 * SPACE : O(n/k) — one recursion frame per group
 *
 * WHICH TO USE:
 * The recursive version is shorter and easier to write first. LeetCode's follow-up
 * asks for O(1) extra memory, which is the iterative Approach 1.
 * ============================================================
 */
public class ReverseNodesInKGroup {

    /** Approach 1 — Iterative group reversal. TIME O(n) · SPACE O(1) · mutates input */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode groupPrev = dummy;

        while (true) {
            ListNode kth = getKth(groupPrev, k);
            if (kth == null) break;
            ListNode groupNext = kth.next;

            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;
            while (curr != groupNext) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            ListNode oldFirst = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = oldFirst;
        }
        return dummy.next;
    }

    /** Approach 2 — Recursive, one frame per group. TIME O(n) · SPACE O(n/k) recursion stack · mutates input */
    public ListNode reverseKGroupRecursive(ListNode head, int k) {
        ListNode probe = head;
        for (int i = 0; i < k; i++) {
            if (probe == null) return head;
            probe = probe.next;
        }

        ListNode prev = null, curr = head;
        for (int i = 0; i < k; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head.next = reverseKGroupRecursive(curr, k);
        return prev;
    }

    private ListNode getKth(ListNode node, int k) {
        while (node != null && k > 0) {
            node = node.next;
            k--;
        }
        return node;
    }
}
