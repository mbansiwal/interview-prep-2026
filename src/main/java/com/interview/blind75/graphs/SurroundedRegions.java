package com.interview.blind75.graphs;

/**
 * ============================================================
 * PROBLEM : Surrounded Regions
 * LINK    : https://leetcode.com/problems/surrounded-regions/
 * DIFFICULTY: Medium
 * PATTERN : DFS from Border
 * ============================================================
 *
 * DESCRIPTION:
 * Given an m x n matrix board of 'X' and 'O', capture all regions not
 * connected to the border. Captured means replace 'O' with 'X'.
 *
 * EXAMPLES:
 *   Input : [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 *   Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 *
 * CONSTRAINTS:
 *   - m, n >= 1
 *   - board[i][j] is 'X' or 'O'
 *
 * ============================================================
 * APPROACH: Mark Safe O's from Border, Then Flip
 * ============================================================
 * 1. DFS from all border 'O's and mark them 'S' (safe).
 * 2. Scan entire board: 'O' → 'X', 'S' → 'O'.
 *
 * WHY THIS WORKS:
 * Any 'O' connected to a border cannot be captured (it escapes).
 * We protect those first, then flip everything else.
 *
 * TIME  : O(m * n)
 * SPACE : O(m * n) — recursion stack
 * ============================================================
 */
public class SurroundedRegions {

    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        for (int r = 0; r < m; r++) {
            dfs(board, r, 0);
            dfs(board, r, n - 1);
        }
        for (int c = 0; c < n; c++) {
            dfs(board, 0, c);
            dfs(board, m - 1, c);
        }
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == 'O') board[r][c] = 'X';
                else if (board[r][c] == 'S') board[r][c] = 'O';
            }
        }
    }

    private void dfs(char[][] board, int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != 'O') return;
        board[r][c] = 'S';
        dfs(board, r + 1, c); dfs(board, r - 1, c);
        dfs(board, r, c + 1); dfs(board, r, c - 1);
    }
}
