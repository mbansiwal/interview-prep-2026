package com.interview.blind75.trie;

/**
 * ============================================================
 * PROBLEM : Design Add and Search Words Data Structure
 * LINK    : https://leetcode.com/problems/design-add-and-search-words-data-structure/
 * DIFFICULTY: Medium
 * PATTERN : Trie
 * ============================================================
 *
 * DESCRIPTION:
 * Design a data structure that supports addWord(word) and
 * search(word) where search can contain '.' that matches any letter.
 *
 * EXAMPLES:
 *   addWord("bad"); addWord("dad"); addWord("mad");
 *   search("pad") → false; search(".ad") → true; search("b..") → true
 *
 * CONSTRAINTS:
 *   - 1 <= word.length <= 25
 *   - word in addWord contains lowercase letters
 *   - word in search contains '.' or lowercase letters
 *
 * ============================================================
 * APPROACHES  (m = word length, N = total characters added)
 * ============================================================
 * APPROACH 1: Trie with DFS for the '.' Wildcard
 * 1. addWord inserts into a trie exactly like Implement Trie.
 * 2. search walks the trie with DFS: a letter follows its one child;
 *    '.' tries every non-null child at that position.
 * 3. At the end of the pattern, the node must be the end of a word.
 * Intuition: '.' can match any letter, so branch on all children — the trie prunes
 * every path that no stored word follows.
 * TIME  : addWord O(m); search O(m) without dots, worst case O(N) with dots (every branch explored)
 * SPACE : O(26 · N) for the trie; O(m) recursion stack for search
 *
 * ALTERNATIVES: storing words in a list (or bucketed by length) and comparing
 * character by character is O(words · m) per search — no materially better
 * alternative to Approach 1.
 * ============================================================
 */
public class AddSearchWords {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private final TrieNode root = new TrieNode();

    /** Approach 1 — Trie insert. TIME O(m) · SPACE O(m) new nodes worst case */
    public void addWord(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (curr.children[idx] == null) curr.children[idx] = new TrieNode();
            curr = curr.children[idx];
        }
        curr.isEnd = true;
    }

    /** Approach 1 — Trie DFS with wildcard branching. TIME O(m) no dots, O(N) worst case · SPACE O(m) recursion */
    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int i, TrieNode node) {
        if (i == word.length()) return node.isEnd;
        char c = word.charAt(i);
        if (c == '.') {
            for (TrieNode child : node.children) {
                if (child != null && dfs(word, i + 1, child)) return true;
            }
            return false;
        }
        int idx = c - 'a';
        return node.children[idx] != null && dfs(word, i + 1, node.children[idx]);
    }
}
