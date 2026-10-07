package com.interview.blind75.linkedlist;

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
 * APPROACH: Floyd's Tortoise and Hare
 * ============================================================
 * 1. Initialize slow=head, fast=head.
 * 2. Move slow by 1, fast by 2 each iteration.
 * 3. If fast or fast.next is null, no cycle.
 * 4. If slow == fast, there is a cycle.
 *
 * WHY THIS WORKS:
 * In a cycle, the fast pointer laps the slow pointer. They will
 * eventually meet because fast closes the gap by 1 node each step.
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
