package com.interview.blind75.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * PROBLEM : Word Search II
 * LINK    : https://leetcode.com/problems/word-search-ii/
 * DIFFICULTY: Hard
 * PATTERN : Trie + Backtracking
 * ============================================================
 *
 * DESCRIPTION:
 * Given an m×n board of characters and a list of strings words, return
 * all words on the board. Each word is found by sequentially adjacent cells
 * (up, down, left, right). Same cell cannot be reused within a word.
 *
 * EXAMPLES:
 *   Input : board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]
 *           words = ["oath","pea","eat","rain"]
 *   Output: ["eat","oath"]
 *
 * CONSTRAINTS:
 *   - 1 <= rows, cols <= 12
 *   - words.length <= 3 * 10^4
 *
 * ============================================================
 * APPROACHES  (R×C = board size, L = max word length, W = number of words)
 * ============================================================
 * APPROACH 1: Trie of All Words + One Board DFS, with Pruning
 * 1. Build a trie from all words; store the full word at its last node.
 * 2. DFS from every cell, walking the board and the trie together; mark cells
 *    visited with '#' and restore them when backtracking.
 * 3. When a trie node holds a word, add it to the result and clear it (no duplicates).
 * 4. On the way back, delete a child that has no word and no children left (leaf pruning).
 * Intuition: one DFS looks for every word at once and stops as soon as no word
 * starts with the current path; pruning stops re-walking fully-found branches.
 * TIME  : O(R · C · 4 · 3^(L-1)) worst case — 4 directions first, then at most 3 (can't go back)
 * SPACE : O(W · L) for the trie + O(L) recursion stack
 *
 * ALTERNATIVES: running Word Search I separately for each word is
 * O(W · R · C · 4 · 3^(L-1)) and times out — no materially better alternative to Approach 1.
 * ============================================================
 */
public class WordSearchII {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
    }

    /** Approach 1 — Trie + board DFS with pruning. TIME O(R·C·4·3^(L-1)) · SPACE O(W·L) · board restored after use */
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        List<String> result = new ArrayList<>();
        int rows = board.length, cols = board[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dfs(board, r, c, root, result);
            }
        }
        return result;
    }

    private void dfs(char[][] board, int r, int c, TrieNode node, List<String> result) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) return;
        char ch = board[r][c];
        if (ch == '#' || node.children[ch - 'a'] == null) return;

        TrieNode parent = node;
        node = node.children[ch - 'a'];
        if (node.word != null) {
            result.add(node.word);
            node.word = null;
        }

        board[r][c] = '#';
        dfs(board, r + 1, c, node, result);
        dfs(board, r - 1, c, node, result);
        dfs(board, r, c + 1, node, result);
        dfs(board, r, c - 1, node, result);
        board[r][c] = ch;

        if (node.word == null && isEmpty(node)) {
            parent.children[ch - 'a'] = null;
        }
    }

    private boolean isEmpty(TrieNode node) {
        for (TrieNode child : node.children) {
            if (child != null) return false;
        }
        return true;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (curr.children[idx] == null) curr.children[idx] = new TrieNode();
                curr = curr.children[idx];
            }
            curr.word = word;
        }
        return root;
    }
}
