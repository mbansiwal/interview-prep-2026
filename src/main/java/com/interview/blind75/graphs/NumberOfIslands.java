package com.interview.blind75.graphs;

/**
 * ============================================================
 * PROBLEM : Number of Islands
 * LINK    : https://leetcode.com/problems/number-of-islands/
 * DIFFICULTY: Medium
 * PATTERN : DFS / BFS on Grid
 * ============================================================
 *
 * DESCRIPTION:
 * Given an m x n 2D binary grid '1' (land) / '0' (water), return the number of islands.
 * An island is surrounded by water and formed by connecting adjacent lands horizontally/vertically.
 *
 * EXAMPLES:
 *   Input : [["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]
 *   Output: 1
 *
 *   Input : [["1","1","0","0","0"],["1","1","0","0","0"],["0","0","1","0","0"],["0","0","0","1","1"]]
 *   Output: 3
 *
 * CONSTRAINTS:
 *   - m, n >= 1
 *   - grid[i][j] is '0' or '1'
 *
 * ============================================================
 * APPROACH: DFS with In-Place Marking
 * ============================================================
 * 1. Iterate every cell. When we find a '1', increment count and DFS.
 * 2. DFS marks every connected '1' as '0' (visited), then recurses in 4 directions.
 * 3. After DFS, the entire island has been "sunk".
 *
 * WHY THIS WORKS:
 * Each DFS call floods an entire island, preventing double-counting.
 * In-place modification avoids extra visited array.
 *
 * TIME  : O(m * n)
 * SPACE : O(m * n) — worst case recursion on full-land grid
 * ============================================================
 */
public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    dfs(grid, r, c);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != '1') return;
        grid[r][c] = '0';
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}
