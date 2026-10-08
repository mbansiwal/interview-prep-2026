package com.interview.blind75.backtracking;

/**
 * ============================================================
 * PROBLEM : Word Search
 * LINK    : https://leetcode.com/problems/word-search/
 * DIFFICULTY: Medium
 * PATTERN : Backtracking / DFS
 * ============================================================
 *
 * DESCRIPTION:
 * Given an m x n grid of characters board and a string word, return true if
 * the word exists in the grid. The word can be constructed from letters of
 * sequentially adjacent cells (horizontally or vertically). The same cell
 * may not be used more than once.
 *
 * EXAMPLES:
 *   Input : board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 *   Output: true
 *
 *   Input : board = same board, word = "SEE"
 *   Output: true
 *
 *   Input : board = same board, word = "ABCB"
 *   Output: false
 *
 * CONSTRAINTS:
 *   - m == board.length, n == board[i].length
 *   - 1 <= m, n <= 6
 *   - 1 <= word.length <= 15
 *
 * ============================================================
 * APPROACH 1: DFS Backtracking with In-Place Visited Marking
 * ============================================================
 * 1. For each cell matching word[0], start a DFS.
 * 2. In DFS: mark cell visited by replacing with '#', recurse on 4 neighbors.
 * 3. Restore cell after recursion (backtrack).
 * 4. Base case: index == word.length() → found!
 * 5. Prune: out of bounds, cell != word[index], or cell == '#'.
 *
 * WHY THIS WORKS:
 * In-place '#' marking avoids a separate visited[][] array.
 * Restoring the cell on backtrack allows other paths to use it.
 *
 * TIME  : O(m * n * 3^L) — L = word length; after the first step only 3 directions are new
 *         (often quoted as the looser O(m * n * 4^L))
 * SPACE : O(L) — recursion depth (temporarily mutates the board, restored before returning)
 *
 * ALTERNATIVES: a separate visited[][] array instead of '#' marking — same time,
 * O(m * n) extra space, but leaves the input untouched. Useful pruning: return false early
 * if the board lacks enough of some letter, and search the reversed word when its last letter
 * is rarer than its first. No materially better asymptotic alternative.
 *
 * WHICH TO USE: DFS + in-place marking; mention the pruning tricks as optimisations.
 * ============================================================
 */
public class WordSearch {

    /** Approach 1 — DFS backtracking, in-place marking. TIME O(m * n * 3^L) · SPACE O(L) (mutates board temporarily) */
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (dfs(board, word, r, c, 0)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int r, int c, int idx) {
        if (idx == word.length()) return true;
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) return false;
        if (board[r][c] != word.charAt(idx)) return false;

        char temp = board[r][c];
        board[r][c] = '#';

        boolean found = dfs(board, word, r + 1, c, idx + 1)
                     || dfs(board, word, r - 1, c, idx + 1)
                     || dfs(board, word, r, c + 1, idx + 1)
                     || dfs(board, word, r, c - 1, idx + 1);

        board[r][c] = temp;
        return found;
    }
}
