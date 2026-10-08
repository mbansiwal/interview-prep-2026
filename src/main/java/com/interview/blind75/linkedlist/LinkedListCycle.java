package com.interview.blind75.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * ============================================================
 * PROBLEM : Linked List Cycle
 * LINK    : https://leetcode.com/problems/linked-list-cycle/
 * DIFFICULTY: Easy
 * PATTERN : Linked List
 * ============================================================
 *
 * DESCRIPTION:
 * Given head of a linked list, determine if it has a cycle.
 * A cycle exists if some node's next pointer leads back to a previous node.
 *
 * EXAMPLES:
 *   Input : 3→2→0→-4→(back to 2), pos=1
 *   Output: true
 *
 *   Input : 1→2→(back to 1), pos=0
 *   Output: true
 *
 *   Input : 1, pos=-1
 *   Output: false
 *
 * CONSTRAINTS:
 *   - 0 <= number of nodes <= 10^4
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Floyd's Tortoise and Hare
 * 1. Start slow and fast at head.
 * 2. Move slow by 1 and fast by 2 each step.
 * 3. If fast reaches null, there is no cycle; if slow == fast, there is one.
 * Intuition: inside a cycle, fast gains one node per step, so it must catch slow.
 * TIME  : O(n)
 * SPACE : O(1)
 *
 * APPROACH 2: HashSet of Visited Nodes
 * 1. Walk the list, adding each node (by reference) to a set.
 * 2. If a node is already in the set, the list loops back → cycle.
 * 3. Reaching null means no cycle.
 * Intuition: a cycle is exactly "visiting the same node twice".
 * TIME  : O(n)
 * SPACE : O(n) — the set
 *
 * WHICH TO USE:
 * The HashSet version is the natural first idea; the follow-up "O(1) memory?"
 * is answered by Floyd's algorithm, which is the expected final answer.
 * ============================================================
 */
public class LinkedListCycle {

    /** Approach 1 — Floyd's tortoise and hare. TIME O(n) · SPACE O(1) */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /** Approach 2 — HashSet of visited nodes. TIME O(n) · SPACE O(n) */
    public boolean hasCycleWithSet(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        for (ListNode curr = head; curr != null; curr = curr.next) {
            if (!seen.add(curr)) return true;
        }
        return false;
    }
}
