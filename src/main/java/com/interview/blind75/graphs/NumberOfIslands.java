package com.interview.blind75.graphs;

import java.util.Deque;
import java.util.ArrayDeque;
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
 * APPROACHES  (m × n grid)
 * ============================================================
 * APPROACH 1: DFS with in-place marking
 * 1. Scan every cell; on a '1', count a new island and DFS from it.
 * 2. DFS "sinks" every connected '1' to '0' so it's never counted again.
 * Intuition: one flood fill per island.
 * TIME  : O(m * n)
 * SPACE : O(m * n) recursion in the worst case (all land) — mutates the grid
 *
 * APPROACH 2: BFS with in-place marking
 * 1. Same scan, but flood each island with a queue instead of recursion.
 * 2. Mark a cell '0' when it is enqueued, so nothing is queued twice.
 * Intuition: identical flood fill, but no risk of a stack overflow on huge grids.
 * TIME  : O(m * n)
 * SPACE : O(min(m, n)) queue — the BFS frontier is bounded by the grid's shorter side; mutates the grid
 *
 * APPROACH 3: Union-Find (disjoint set union)
 * 1. Start with count = number of '1' cells, each its own set.
 * 2. For every land cell, union it with its right and down land neighbours; each
 *    successful union merges two islands, so count--.
 * Intuition: islands are connected components; DSU counts components directly.
 * TIME  : O(m * n * α(m * n)) ≈ O(m * n) with path compression + union by rank
 * SPACE : O(m * n) parent/rank arrays — does NOT mutate the grid
 *
 * WHICH TO USE:
 * DFS is the expected answer; switch to BFS if recursion depth is a concern.
 * Union-Find shines when the grid can't be modified or land is added over time
 * (Number of Islands II).
 * ============================================================
 */
public class NumberOfIslands {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /** Approach 1 — DFS flood fill. TIME O(m * n) · SPACE O(m * n) recursion (mutates grid) */
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

    /** Approach 2 — BFS flood fill. TIME O(m * n) · SPACE O(min(m, n)) queue (mutates grid) */
    public int numIslandsBfs(char[][] grid) {
        int m = grid.length, n = grid[0].length, count = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] != '1') continue;
                count++;
                grid[r][c] = '0';
                queue.offer(new int[]{r, c});
                while (!queue.isEmpty()) {
                    int[] cell = queue.poll();
                    for (int[] d : DIRS) {
                        int nr = cell[0] + d[0], nc = cell[1] + d[1];
                        if (nr < 0 || nr >= m || nc < 0 || nc >= n || grid[nr][nc] != '1') continue;
                        grid[nr][nc] = '0';
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        return count;
    }

    /** Approach 3 — Union-Find. TIME O(m * n * α) · SPACE O(m * n) (grid untouched) */
    public int numIslandsUnionFind(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] parent = new int[m * n];
        int[] rank = new int[m * n];
        int count = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == '1') {
                    parent[r * n + c] = r * n + c;
                    count++;
                }
            }
        }
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] != '1') continue;
                if (r + 1 < m && grid[r + 1][c] == '1' && union(parent, rank, r * n + c, (r + 1) * n + c)) count--;
                if (c + 1 < n && grid[r][c + 1] == '1' && union(parent, rank, r * n + c, r * n + c + 1)) count--;
            }
        }
        return count;
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];   // path halving
            x = parent[x];
        }
        return x;
    }

    private boolean union(int[] parent, int[] rank, int a, int b) {
        int ra = find(parent, a), rb = find(parent, b);
        if (ra == rb) return false;
        if (rank[ra] < rank[rb]) { int t = ra; ra = rb; rb = t; }
        parent[rb] = ra;
        if (rank[ra] == rank[rb]) rank[ra]++;
        return true;
    }
}
