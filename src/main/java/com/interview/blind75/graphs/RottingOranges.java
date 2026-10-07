package com.interview.blind75.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ============================================================
 * PROBLEM : Rotting Oranges
 * LINK    : https://leetcode.com/problems/rotting-oranges/
 * DIFFICULTY: Medium
 * PATTERN : Multi-source BFS
 * ============================================================
 *
 * DESCRIPTION:
 * In a grid: 0 = empty, 1 = fresh orange, 2 = rotten orange.
 * Every minute, each rotten orange rots adjacent fresh ones.
 * Return minimum minutes until no fresh oranges remain, or -1 if impossible.
 *
 * EXAMPLES:
 *   Input : [[2,1,1],[1,1,0],[0,1,1]]
 *   Output: 4
 *
 *   Input : [[2,1,1],[0,1,1],[1,0,1]]
 *   Output: -1
 *
 *   Input : [[0,2]]
 *   Output: 0
 *
 * CONSTRAINTS:
 *   - m, n >= 1, grid[i][j] in {0, 1, 2}
 *
 * ============================================================
 * APPROACH: Multi-Source BFS from All Rotten Oranges
 * ============================================================
 * 1. Enqueue all initially rotten oranges (layer 0).
 * 2. Count fresh oranges.
 * 3. BFS: each level = one minute. Rot adjacent fresh, decrement fresh count.
 * 4. Return minutes if fresh==0, else -1.
 *
 * WHY THIS WORKS:
 * Multi-source BFS simultaneously spreads rot from all starting points,
 * computing the minimum time naturally via BFS levels.
 *
 * TIME  : O(m * n)
 * SPACE : O(m * n) — queue size
 * ============================================================
 */
public class RottingOranges {

    private static final int[][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}};

    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 2) queue.offer(new int[]{r, c});
                else if (grid[r][c] == 1) fresh++;
            }
        }

        int minutes = 0;
        while (!queue.isEmpty() && fresh > 0) {
            minutes++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                for (int[] d : DIRS) {
                    int nr = cell[0] + d[0], nc = cell[1] + d[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || grid[nr][nc] != 1) continue;
                    grid[nr][nc] = 2;
                    fresh--;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return fresh == 0 ? minutes : -1;
    }
}
