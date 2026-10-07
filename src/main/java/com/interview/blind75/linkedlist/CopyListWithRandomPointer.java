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
 * APPROACH: HashMap Old-to-New Node Mapping
 * ============================================================
 * 1. First pass: create a copy node for every original node, store in map.
 * 2. Second pass: wire copy.next and copy.random using the map.
 *
 * WHY THIS WORKS:
 * Two-pass approach separates node creation from pointer wiring.
 * The HashMap ensures every original→copy mapping is available
 * before we assign any pointers.
 *
 * FOLLOW-UP (O(1) extra space): interleave copies into the list
 * (A→A'→B→B'), set A'.random = A.random.next, then split the two lists apart.
 *
 * TIME  : O(n)
 * SPACE : O(n)
 * ============================================================
 */
public class CopyListWithRandomPointer {

    public static class Node {
        public int val;
        public Node next;
        public Node random;
        public Node(int val) { this.val = val; }
    }

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
}
