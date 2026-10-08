package com.interview.blind75.graphs;

import java.util.Deque;
import java.util.ArrayDeque;
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
 * APPROACHES  (m × n board)
 * ============================================================
 * Key idea for all: an 'O' survives only if it connects to the border. So mark the
 * border-connected 'O's as safe first, then flip every other 'O' to 'X'.
 *
 * APPROACH 1: DFS from border 'O's
 * 1. DFS from each border 'O', marking connected 'O's as 'S' (safe).
 * 2. Scan the board: 'O' → 'X' (captured), 'S' → 'O' (restore).
 * TIME  : O(m * n)
 * SPACE : O(m * n) recursion in the worst case (board is modified in place, as required)
 *
 * APPROACH 2: Multi-source BFS from border 'O's
 * 1. Enqueue every border 'O' (marking it 'S'), then BFS to mark connected 'O's.
 * 2. Same final scan.
 * TIME  : O(m * n)
 * SPACE : O(m * n) queue in the worst case — but no recursion-depth risk
 *
 * ALTERNATIVE: Union-Find with a virtual "border" node — union border 'O's with it and
 * adjacent 'O's with each other; flip 'O's not connected to it. Same O(m * n · α) time,
 * O(m * n) space, more code — worth naming, rarely worth writing.
 *
 * WHICH TO USE:
 * DFS or BFS from the border — the "work from the boundary inward" insight is what matters.
 * Prefer BFS on large boards to avoid stack overflow.
 * ============================================================
 */
public class SurroundedRegions {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /** Approach 1 — DFS from border. TIME O(m * n) · SPACE O(m * n) recursion (mutates board, as required) */
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
        flip(board);
    }

    private void dfs(char[][] board, int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != 'O') return;
        board[r][c] = 'S';
        dfs(board, r + 1, c); dfs(board, r - 1, c);
        dfs(board, r, c + 1); dfs(board, r, c - 1);
    }

    /** Approach 2 — Multi-source BFS from border. TIME O(m * n) · SPACE O(m * n) queue (mutates board, as required) */
    public void solveBfs(char[][] board) {
        int m = board.length, n = board[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < m; r++) {
            markSafe(board, r, 0, queue);
            markSafe(board, r, n - 1, queue);
        }
        for (int c = 0; c < n; c++) {
            markSafe(board, 0, c, queue);
            markSafe(board, m - 1, c, queue);
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : DIRS) {
                int nr = cell[0] + d[0], nc = cell[1] + d[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) markSafe(board, nr, nc, queue);
            }
        }
        flip(board);
    }

    private void markSafe(char[][] board, int r, int c, Deque<int[]> queue) {
        if (board[r][c] != 'O') return;
        board[r][c] = 'S';
        queue.offer(new int[]{r, c});
    }

    private void flip(char[][] board) {
        for (char[] row : board) {
            for (int c = 0; c < row.length; c++) {
                if (row[c] == 'O') row[c] = 'X';
                else if (row[c] == 'S') row[c] = 'O';
            }
        }
    }
}
