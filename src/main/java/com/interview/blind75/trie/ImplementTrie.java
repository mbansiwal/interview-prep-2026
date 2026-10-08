package com.interview.blind75.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * PROBLEM : Implement Trie (Prefix Tree)
 * LINK    : https://leetcode.com/problems/implement-trie-prefix-tree/
 * DIFFICULTY: Medium
 * PATTERN : Trie
 * ============================================================
 *
 * DESCRIPTION:
 * Implement a trie (prefix tree) with insert, search, and startsWith methods.
 * search returns true only if the word was inserted.
 * startsWith returns true if any inserted word starts with the prefix.
 *
 * EXAMPLES:
 *   insert("apple"); search("apple") → true
 *   search("app") → false; startsWith("app") → true
 *
 * CONSTRAINTS:
 *   - 1 <= word.length <= 2000
 *   - word and prefix consist of lowercase English letters
 *
 * ============================================================
 * APPROACHES  (m = length of the word/prefix, N = total characters inserted)
 * ============================================================
 * APPROACH 1: TrieNode with a 26-Slot Child Array (this class)
 * 1. Each node has TrieNode[26] children and an isEnd flag.
 * 2. insert: walk the characters, creating missing children; mark the last node isEnd.
 * 3. search: walk the characters; fail on a missing child; the last node must be isEnd.
 * 4. startsWith: same walk, but any surviving node means the prefix exists.
 * Intuition: index c - 'a' gives O(1) child lookup; isEnd separates words from prefixes.
 * TIME  : O(m) per operation
 * SPACE : O(26 · N) worst case — every node reserves 26 slots, even unused ones
 *
 * APPROACH 2: TrieNode with a HashMap of Children (ImplementTrie.HashMapTrie)
 * 1. Each node stores Map<Character, Node> children instead of an array.
 * 2. Same insert / search / startsWith walks, using get / computeIfAbsent.
 * Intuition: only allocate children that actually exist.
 * TIME  : O(m) per operation (expected — hashing has a higher constant factor)
 * SPACE : O(N) — one map entry per real edge
 *
 * WHICH TO USE:
 * The array version is the standard interview answer for lowercase a–z: fastest and
 * simplest. Choose the HashMap version for large or unknown alphabets (Unicode,
 * mixed case) or very sparse tries, where 26 slots per node wastes memory.
 * ============================================================
 */
public class ImplementTrie {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private final TrieNode root = new TrieNode();

    /** Approach 1 — Array children. TIME O(m) · SPACE O(m) new nodes worst case */
    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (curr.children[idx] == null) curr.children[idx] = new TrieNode();
            curr = curr.children[idx];
        }
        curr.isEnd = true;
    }

    /** Approach 1 — Array children. TIME O(m) · SPACE O(1) */
    public boolean search(String word) {
        TrieNode node = traverse(word);
        return node != null && node.isEnd;
    }

    /** Approach 1 — Array children. TIME O(m) · SPACE O(1) */
    public boolean startsWith(String prefix) {
        return traverse(prefix) != null;
    }

    private TrieNode traverse(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (curr.children[idx] == null) return null;
            curr = curr.children[idx];
        }
        return curr;
    }

    /** Approach 2 — HashMap children; works for any alphabet. TIME O(m) per op · SPACE O(N) total */
    public static class HashMapTrie {

        private static class Node {
            final Map<Character, Node> children = new HashMap<>();
            boolean isEnd = false;
        }

        private final Node root = new Node();

        /** TIME O(m) · SPACE O(m) new nodes worst case */
        public void insert(String word) {
            Node curr = root;
            for (char c : word.toCharArray()) curr = curr.children.computeIfAbsent(c, k -> new Node());
            curr.isEnd = true;
        }

        /** TIME O(m) · SPACE O(1) */
        public boolean search(String word) {
            Node node = traverse(word);
            return node != null && node.isEnd;
        }

        /** TIME O(m) · SPACE O(1) */
        public boolean startsWith(String prefix) {
            return traverse(prefix) != null;
        }

        private Node traverse(String word) {
            Node curr = root;
            for (char c : word.toCharArray()) {
                curr = curr.children.get(c);
                if (curr == null) return null;
            }
            return curr;
        }
    }
}
