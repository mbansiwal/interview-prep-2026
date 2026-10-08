package com.interview.blind75.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * ============================================================
 * PROBLEM : Pacific Atlantic Water Flow
 * LINK    : https://leetcode.com/problems/pacific-atlantic-water-flow/
 * DIFFICULTY: Medium
 * PATTERN : Multi-source BFS / DFS
 * ============================================================
 *
 * DESCRIPTION:
 * Given an m x n island, rain water flows to Pacific Ocean (top/left edges)
 * and Atlantic Ocean (bottom/right edges). Water flows to adjacent cells with
 * equal or lesser height. Return all cells from which water can flow to both oceans.
 *
 * EXAMPLES:
 *   Input : heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 *   Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 *
 * CONSTRAINTS:
 *   - m, n >= 1
 *   - 0 <= heights[r][c] <= 10^5
 *
 * ============================================================
 * APPROACHES  (m × n grid)
 * ============================================================
 * Key idea for both: reverse the flow. Instead of asking "where can water from this cell go?"
 * (one search per cell = O((m * n)²)), start from each ocean's edge and walk UPHILL
 * (neighbour height >= current). Cells reached from both oceans are the answer.
 *
 * APPROACH 1: Multi-source BFS from each ocean's edge
 * 1. Queue every Pacific-edge cell (top row, left column); BFS uphill, marking pacific[][].
 * 2. Do the same from the Atlantic edge (bottom row, right column) into atlantic[][].
 * 3. Collect cells marked in both.
 * TIME  : O(m * n) — each cell enters each queue at most once
 * SPACE : O(m * n) for the two visited grids (+ queue)
 *
 * APPROACH 2: DFS from each ocean's edge
 * 1. Run a recursive uphill DFS from every edge cell instead of a queue.
 * 2. Same intersection step.
 * TIME  : O(m * n)
 * SPACE : O(m * n) visited grids + O(m * n) recursion depth in the worst case
 *
 * WHICH TO USE:
 * Either is fine and equally fast; BFS avoids deep recursion on large grids,
 * DFS is a little shorter to write. The reverse-flow idea is what's being tested.
 * ============================================================
 */
public class PacificAtlanticWaterFlow {

    private static final int[][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}};

    /** Approach 1 — Multi-source reverse BFS. TIME O(m * n) · SPACE O(m * n) */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        Queue<int[]> pq = new LinkedList<>();
        Queue<int[]> aq = new LinkedList<>();

        for (int r = 0; r < m; r++) {
            pq.offer(new int[]{r, 0});   pacific[r][0] = true;
            aq.offer(new int[]{r, n-1}); atlantic[r][n-1] = true;
        }
        for (int c = 0; c < n; c++) {
            pq.offer(new int[]{0, c});   pacific[0][c] = true;
            aq.offer(new int[]{m-1, c}); atlantic[m-1][c] = true;
        }

        bfs(heights, pq, pacific);
        bfs(heights, aq, atlantic);
        return bothOceans(pacific, atlantic);
    }

    private void bfs(int[][] heights, Queue<int[]> queue, boolean[][] visited) {
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : DIRS) {
                int nr = cell[0] + d[0], nc = cell[1] + d[1];
                if (nr < 0 || nr >= heights.length || nc < 0 || nc >= heights[0].length) continue;
                if (visited[nr][nc]) continue;
                if (heights[nr][nc] < heights[cell[0]][cell[1]]) continue;
                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }
    }

    /** Approach 2 — Reverse DFS from the edges. TIME O(m * n) · SPACE O(m * n) */
    public List<List<Integer>> pacificAtlanticDfs(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        for (int r = 0; r < m; r++) {
            dfs(heights, r, 0, pacific);
            dfs(heights, r, n - 1, atlantic);
        }
        for (int c = 0; c < n; c++) {
            dfs(heights, 0, c, pacific);
            dfs(heights, m - 1, c, atlantic);
        }
        return bothOceans(pacific, atlantic);
    }

    private void dfs(int[][] heights, int r, int c, boolean[][] visited) {
        if (visited[r][c]) return;
        visited[r][c] = true;
        for (int[] d : DIRS) {
            int nr = r + d[0], nc = c + d[1];
            if (nr < 0 || nr >= heights.length || nc < 0 || nc >= heights[0].length) continue;
            if (heights[nr][nc] >= heights[r][c]) dfs(heights, nr, nc, visited);
        }
    }

    private List<List<Integer>> bothOceans(boolean[][] pacific, boolean[][] atlantic) {
        List<List<Integer>> result = new ArrayList<>();
        for (int r = 0; r < pacific.length; r++) {
            for (int c = 0; c < pacific[0].length; c++) {
                if (pacific[r][c] && atlantic[r][c]) result.add(List.of(r, c));
            }
        }
        return result;
    }
}
