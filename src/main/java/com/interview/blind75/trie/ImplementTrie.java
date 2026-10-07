package com.interview.blind75.trie;

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
 * APPROACH: TrieNode with 26-Child Array
 * ============================================================
 * 1. Each TrieNode has TrieNode[26] children and a boolean isEnd.
 * 2. insert: traverse chars, creating nodes as needed; mark isEnd=true.
 * 3. search: traverse chars; if any missing return false; check isEnd.
 * 4. startsWith: traverse chars; if any missing return false.
 *
 * WHY THIS WORKS:
 * An array of 26 children allows O(1) child lookup by character index.
 * isEnd distinguishes inserted words from mere prefixes.
 *
 * TIME  : O(m) per operation where m = word length
 * SPACE : O(m * n) total where n = number of words
 * ============================================================
 */
public class ImplementTrie {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private final TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (curr.children[idx] == null) curr.children[idx] = new TrieNode();
            curr = curr.children[idx];
        }
        curr.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = traverse(word);
        return node != null && node.isEnd;
    }

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
}
