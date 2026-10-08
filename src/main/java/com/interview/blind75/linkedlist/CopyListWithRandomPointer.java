package com.interview.blind75.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * PROBLEM : Copy List with Random Pointer
 * LINK    : https://leetcode.com/problems/copy-list-with-random-pointer/
 * DIFFICULTY: Medium
 * PATTERN : Linked List
 * ============================================================
 *
 * DESCRIPTION:
 * A linked list of length n is given such that each node contains an
 * additional random pointer, which could point to any node in the list,
 * or null. Return a deep copy of the list.
 *
 * EXAMPLES:
 *   Input : [[7,null],[13,0],[11,4],[10,2],[1,0]]
 *   Output: same structure, deep copied
 *
 * CONSTRAINTS:
 *   - 0 <= n <= 1000
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: HashMap Old → New Mapping (two passes)
 * 1. First pass: create a copy of every node and store original → copy in a map.
 * 2. Second pass: set copy.next = map.get(orig.next), copy.random = map.get(orig.random).
 * Intuition: separate "create nodes" from "wire pointers" so every target exists.
 * TIME  : O(n)
 * SPACE : O(n) — the map
 *
 * APPROACH 2: Interleave Copies In Place (no map)
 * 1. Insert each copy right after its original: A→A'→B→B'→…
 * 2. Set randoms: A'.random = A.random == null ? null : A.random.next.
 * 3. Split the woven list back into the original and the copy.
 * Intuition: the copy of X always sits at X.next, so the list itself is the map.
 * TIME  : O(n) — three passes
 * SPACE : O(1) extra (besides the output nodes); temporarily mutates input, restored at the end
 *
 * WHICH TO USE:
 * The HashMap version is the clearest and the usual first answer. Interviewers
 * frequently follow up with "can you do it without the map?" → Approach 2.
 * ============================================================
 */
public class CopyListWithRandomPointer {

    public static class Node {
        public int val;
        public Node next;
        public Node random;
        public Node(int val) { this.val = val; }
    }

    /** Approach 1 — HashMap old→new. TIME O(n) · SPACE O(n) */
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Map<Node, Node> map = new HashMap<>();

        Node curr = head;
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            Node copy = map.get(curr);
            copy.next   = map.get(curr.next);
            copy.random = map.get(curr.random);
            curr = curr.next;
        }

        return map.get(head);
    }

    /** Approach 2 — Interleave copies, then split. TIME O(n) · SPACE O(1) extra · input restored */
    public Node copyRandomListInterleaved(Node head) {
        if (head == null) return null;

        for (Node curr = head; curr != null; curr = curr.next.next) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
        }

        for (Node curr = head; curr != null; curr = curr.next.next) {
            curr.next.random = (curr.random == null) ? null : curr.random.next;
        }

        Node copyHead = head.next;
        for (Node curr = head; curr != null; curr = curr.next) {
            Node copy = curr.next;
            curr.next = copy.next;
            copy.next = (copy.next == null) ? null : copy.next.next;
        }
        return copyHead;
    }
}
