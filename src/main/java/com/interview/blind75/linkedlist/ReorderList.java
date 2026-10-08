package com.interview.blind75.linkedlist;

import java.util.ArrayList;
import java.util.List;

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
 * APPROACHES
 * ============================================================
 * APPROACH 1: Find Middle + Reverse Second Half + Merge
 * 1. Find the middle node using slow/fast pointers.
 * 2. Reverse the second half of the list in-place.
 * 3. Merge the first half and the reversed second half, alternating nodes.
 * Intuition: reversing the second half lets us walk it from Ln backwards while
 * walking the first half forwards — alternating gives L0, Ln, L1, Ln-1, …
 * TIME  : O(n)
 * SPACE : O(1)
 *
 * APPROACH 2: Array of Nodes + Two Pointers
 * 1. Copy every node reference into an ArrayList (random access).
 * 2. Use i from the front and j from the back; link nodes[i]→nodes[j]→nodes[i+1].
 * 3. Move i forward and j backward until they meet; set the last node's next to null.
 * Intuition: with random access, "one from the front, one from the back" is trivial.
 * TIME  : O(n)
 * SPACE : O(n) — the node array
 *
 * WHICH TO USE:
 * Approach 1 is what interviewers expect: it combines three core linked-list
 * techniques with O(1) space. Approach 2 is simpler to get right under pressure
 * and a good stepping stone, but uses O(n) extra memory.
 * ============================================================
 */
public class ReorderList {

    /** Approach 1 — Middle + reverse + merge. TIME O(n) · SPACE O(1) · mutates input */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondHalf = reverseList(slow.next);
        slow.next = null;

        ListNode first = head, second = secondHalf;
        while (second != null) {
            ListNode tmp1 = first.next, tmp2 = second.next;
            first.next = second;
            second.next = tmp1;
            first = tmp1;
            second = tmp2;
        }
    }

    /** Approach 2 — Node array + two pointers. TIME O(n) · SPACE O(n) · mutates input */
    public void reorderListWithArray(ListNode head) {
        if (head == null || head.next == null) return;

        List<ListNode> nodes = new ArrayList<>();
        for (ListNode cur = head; cur != null; cur = cur.next) nodes.add(cur);

        int i = 0, j = nodes.size() - 1;
        while (i < j) {
            nodes.get(i).next = nodes.get(j);
            i++;
            if (i == j) break;
            nodes.get(j).next = nodes.get(i);
            j--;
        }
        nodes.get(i).next = null;
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
