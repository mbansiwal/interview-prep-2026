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
 * APPROACH: Reverse BFS from Ocean Edges
 * ============================================================
 * 1. BFS from all Pacific-edge cells: mark cells reachable going UPHILL.
 * 2. BFS from all Atlantic-edge cells: same.
 * 3. Cells reachable from both are the answer.
 * 4. "Uphill" = neighbor.height >= current.height (reverse flow direction).
 *
 * WHY THIS WORKS:
 * Instead of simulating flow from every cell (expensive), we reverse:
 * "can water reach Pacific?" = "can we reach this cell starting from Pacific edges going uphill?"
 *
 * TIME  : O(m * n)
 * SPACE : O(m * n)
 * ============================================================
 */
public class PacificAtlanticWaterFlow {

    private static final int[][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}};

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

        List<List<Integer>> result = new ArrayList<>();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (pacific[r][c] && atlantic[r][c]) result.add(List.of(r, c));
            }
        }
        return result;
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
}
