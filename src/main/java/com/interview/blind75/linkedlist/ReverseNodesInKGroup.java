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
 * APPROACH: Iterative Group Reversal with a Dummy Node
 * ============================================================
 * 1. A dummy node sits before head; groupPrev = the node just before the current group.
 * 2. Walk k steps from groupPrev to find the group's last node (kth). If fewer
 *    than k nodes remain, stop — the tail stays in its original order.
 * 3. Reverse the k nodes in place, pointing the group's first node at groupNext
 *    (the node after the group).
 * 4. Link groupPrev to the new group head (kth). The old first node is now the
 *    group's tail, so it becomes groupPrev for the next round.
 *
 * WHY THIS WORKS:
 * Each group is an ordinary in-place list reversal; the only extra work is
 * stitching it back between groupPrev and groupNext. A loop instead of
 * recursion meets LeetCode's follow-up of O(1) extra memory.
 *
 * TIME  : O(n) — each node is visited twice (find kth, then reverse)
 * SPACE : O(1)
 * ============================================================
 */
public class ReverseNodesInKGroup {

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

    private ListNode getKth(ListNode node, int k) {
        while (node != null && k > 0) {
            node = node.next;
            k--;
        }
        return node;
    }
}
