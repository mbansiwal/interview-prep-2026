package com.interview.blind75.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * PROBLEM : LRU Cache
 * LINK    : https://leetcode.com/problems/lru-cache/
 * DIFFICULTY: Medium
 * PATTERN : Linked List + HashMap
 * ============================================================
 *
 * DESCRIPTION:
 * Design a data structure that follows the Least Recently Used (LRU)
 * cache eviction policy. Implement get(key) and put(key, value),
 * both in O(1) time. Capacity is fixed at construction.
 *
 * EXAMPLES:
 *   LRUCache cache = new LRUCache(2);
 *   cache.put(1,1); cache.put(2,2);
 *   cache.get(1) → 1
 *   cache.put(3,3); // evicts key 2
 *   cache.get(2) → -1 (not found)
 *
 * CONSTRAINTS:
 *   - 1 <= capacity <= 3000
 *   - 0 <= key <= 10^4
 *
 * ============================================================
 * APPROACH: HashMap + Doubly Linked List
 * ============================================================
 * 1. DLL maintains access order: head=MRU, tail=LRU.
 * 2. Sentinel head/tail nodes simplify insertion/deletion.
 * 3. get(): move node to MRU position (after head).
 * 4. put(): if exists, update and move to MRU. If capacity full, evict LRU (before tail).
 *
 * WHY THIS WORKS:
 * HashMap provides O(1) key lookup. DLL provides O(1) node removal and
 * insertion. Together they achieve O(1) for all cache operations.
 *
 * TIME  : O(1) get and put
 * SPACE : O(capacity)
 * ============================================================
 */
public class LRUCache {

    private static class Node {
        int key, val;
        Node prev, next;
        Node(int key, int val) { this.key = key; this.val = val; }
    }

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private final Node head = new Node(0, 0); // MRU sentinel
    private final Node tail = new Node(0, 0); // LRU sentinel

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        moveToFront(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            moveToFront(node);
        } else {
            if (map.size() == capacity) {
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }
            Node node = new Node(key, value);
            insertFront(node);
            map.put(key, node);
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void moveToFront(Node node) {
        remove(node);
        insertFront(node);
    }
}
