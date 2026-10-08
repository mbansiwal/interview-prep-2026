package com.interview.blind75.linkedlist;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
 * APPROACHES
 * ============================================================
 * APPROACH 1: HashMap + Hand-Built Doubly Linked List (this class)
 * 1. The list keeps access order: right after head = most recent, right before tail = least recent.
 * 2. Sentinel head/tail nodes remove null checks on insert/remove.
 * 3. get(): look up the node, move it to the front.
 * 4. put(): update + move to front, or insert at front; if over capacity, evict the node before tail.
 * Intuition: the map finds a node in O(1); the list moves or evicts it in O(1).
 * TIME  : O(1) get and put
 * SPACE : O(capacity)
 *
 * APPROACH 2: LinkedHashMap in Access Order (LRUCache.LinkedHashMapCache)
 * 1. Create LinkedHashMap(capacity, 0.75f, accessOrder = true): every get/put moves the key to the end.
 * 2. Override removeEldestEntry to return true when size() > capacity.
 * 3. get() → getOrDefault(key, -1); put() → put(key, value).
 * Intuition: Java's LinkedHashMap is literally a hash map + doubly linked list.
 * TIME  : O(1) get and put
 * SPACE : O(capacity)
 *
 * WHICH TO USE:
 * Interviewers almost always want Approach 1 — the point is to show you can build
 * the map + doubly-linked-list structure. Mention Approach 2 as what you'd use in
 * production Java code.
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
    private final Node head = new Node(0, 0);
    private final Node tail = new Node(0, 0);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    /** Approach 1 — HashMap + doubly linked list. TIME O(1) · SPACE O(capacity) */
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        moveToFront(node);
        return node.val;
    }

    /** Approach 1 — HashMap + doubly linked list. TIME O(1) · SPACE O(capacity) */
    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.val = value;
            moveToFront(node);
            return;
        }
        if (map.size() == capacity) {
            Node lru = tail.prev;
            remove(lru);
            map.remove(lru.key);
        }
        node = new Node(key, value);
        insertFront(node);
        map.put(key, node);
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

    /** Approach 2 — LinkedHashMap with access order + removeEldestEntry. TIME O(1) · SPACE O(capacity) */
    public static class LinkedHashMapCache {
        private final Map<Integer, Integer> cache;

        public LinkedHashMapCache(int capacity) {
            this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    return size() > capacity;
                }
            };
        }

        /** TIME O(1) · SPACE O(1) */
        public int get(int key) {
            return cache.getOrDefault(key, -1);
        }

        /** TIME O(1) · SPACE O(1) amortized */
        public void put(int key, int value) {
            cache.put(key, value);
        }
    }
}
