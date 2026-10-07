package com.interview.blind75.graphs;

/**
 * ============================================================
 * PROBLEM : Max Area of Island
 * LINK    : https://leetcode.com/problems/max-area-of-island/
 * DIFFICULTY: Medium
 * PATTERN : DFS on Grid
 * ============================================================
 *
 * DESCRIPTION:
 * Given an m x n binary matrix grid, return the maximum area of an island.
 * An island is a group of 1's connected 4-directionally. An island's area is
 * the number of cells with value 1.
 *
 * EXAMPLES:
 *   Input : [[1,1,0,0],
 *            [0,1,0,1],
 *            [0,0,0,1]]
 *   Output: 3  (top-left island has 3 cells; right island has 2)
 *
 *   Input : [[0,0,0,0,0,0,0,0]]
 *   Output: 0
 *
 * CONSTRAINTS:
 *   - m, n >= 1
 *   - grid[i][j] is 0 or 1
 *
 * ============================================================
 * APPROACH: DFS Returns Area Size
 * ============================================================
 * 1. For each cell equal to 1, DFS returns the count of connected 1s.
 * 2. Mark visited by setting cell to 0.
 * 3. Track global maximum.
 *
 * WHY THIS WORKS:
 * DFS accumulates 1 + area(all neighbors), naturally giving island area.
 *
 * TIME  : O(m * n)
 * SPACE : O(m * n) — worst case stack depth
 * ============================================================
 */
public class MaxAreaOfIsland {

    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    max = Math.max(max, dfs(grid, r, c));
                }
            }
        }
        return max;
    }

    private int dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) return 0;
        grid[r][c] = 0;
        return 1 + dfs(grid, r + 1, c) + dfs(grid, r - 1, c)
                 + dfs(grid, r, c + 1) + dfs(grid, r, c - 1);
    }
}
