package com.interview.blind75.graphs;

import java.util.Deque;
import java.util.ArrayDeque;
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
 * APPROACHES  (m × n grid)
 * ============================================================
 * APPROACH 1: DFS that returns the area
 * 1. For each land cell, DFS returns 1 + the area of its unvisited neighbours.
 * 2. Mark cells 0 as they're visited; keep the maximum.
 * Intuition: the recursion naturally sums the island's cells.
 * TIME  : O(m * n)
 * SPACE : O(m * n) recursion in the worst case — mutates the grid
 *
 * APPROACH 2: BFS counting cells
 * 1. For each land cell, BFS the island, counting cells as they are dequeued.
 * 2. Mark cells 0 when enqueued; keep the maximum.
 * Intuition: same flood fill without recursion depth limits.
 * TIME  : O(m * n)
 * SPACE : O(min(m, n)) queue — mutates the grid
 *
 * WHICH TO USE:
 * DFS is shortest to write; BFS avoids stack overflow on very large islands.
 * To keep the input intact, use a visited[][] array (O(m * n) extra) or Union-Find with sizes.
 * ============================================================
 */
public class MaxAreaOfIsland {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /** Approach 1 — DFS returning area. TIME O(m * n) · SPACE O(m * n) recursion (mutates grid) */
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

    /** Approach 2 — BFS counting cells. TIME O(m * n) · SPACE O(min(m, n)) queue (mutates grid) */
    public int maxAreaOfIslandBfs(int[][] grid) {
        int m = grid.length, n = grid[0].length, max = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] != 1) continue;
                int area = 0;
                grid[r][c] = 0;
                queue.offer(new int[]{r, c});
                while (!queue.isEmpty()) {
                    int[] cell = queue.poll();
                    area++;
                    for (int[] d : DIRS) {
                        int nr = cell[0] + d[0], nc = cell[1] + d[1];
                        if (nr < 0 || nr >= m || nc < 0 || nc >= n || grid[nr][nc] != 1) continue;
                        grid[nr][nc] = 0;
                        queue.offer(new int[]{nr, nc});
                    }
                }
                max = Math.max(max, area);
            }
        }
        return max;
    }
}
