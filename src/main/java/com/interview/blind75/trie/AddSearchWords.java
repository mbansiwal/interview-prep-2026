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
 * APPROACH: Trie with DFS for Wildcard Search
 * ============================================================
 * 1. Trie with addWord works same as ImplementTrie.
 * 2. search uses DFS: for '.', recurse on all non-null children.
 *    For regular chars, follow the exact child if it exists.
 *
 * WHY THIS WORKS:
 * '.' could match any of 26 children at that position, so we must try
 * all of them. DFS with backtracking explores all valid paths.
 *
 * TIME  : O(m) addWord; search O(m) without dots,
 *         worst case O(total trie nodes) with dots (every branch explored)
 * SPACE : O(m * n) total
 * ============================================================
 */
public class AddSearchWords {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private final TrieNode root = new TrieNode();

    public void addWord(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (curr.children[idx] == null) curr.children[idx] = new TrieNode();
            curr = curr.children[idx];
        }
        curr.isEnd = true;
    }

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
