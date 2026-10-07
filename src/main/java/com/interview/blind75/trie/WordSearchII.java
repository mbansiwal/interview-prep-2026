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
 * APPROACH: Trie Build + Board DFS
 * ============================================================
 * 1. Build a Trie from all words.
 * 2. DFS from each cell on the board, traversing the Trie simultaneously.
 * 3. Mark cells visited during DFS, restore on backtrack.
 * 4. When a Trie node holds a word, add it to results and clear it.
 * 5. On the way back, if a child node has no word and no children left,
 *    delete it from its parent (leaf pruning).
 *
 * WHY THIS WORKS:
 * Building a Trie upfront lets one DFS search for every word at once and
 * stop as soon as no word starts with the current path. Clearing a found word
 * prevents duplicates. Pruning emptied branches means later DFS calls never
 * re-walk paths whose words are all found — without it, large inputs time out.
 *
 * TIME  : O(m*n*4^L) where L = max word length
 * SPACE : O(W*L) for the trie where W = number of words
 * ============================================================
 */
public class WordSearchII {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
    }

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
