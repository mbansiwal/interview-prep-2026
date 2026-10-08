package com.interview.blind75.math;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * PROBLEM : Spiral Matrix
 * LINK    : https://leetcode.com/problems/spiral-matrix/
 * DIFFICULTY: Medium
 * PATTERN : Math / Matrix
 * ============================================================
 *
 * DESCRIPTION:
 * Given an m x n matrix, return all elements in spiral order.
 *
 * EXAMPLES:
 *   Input : [[1,2,3],[4,5,6],[7,8,9]]
 *   Output: [1,2,3,6,9,8,7,4,5]
 *
 * CONSTRAINTS:
 *   - m, n >= 1
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * Both visit every cell exactly once; the output list (m · n) is not counted as space.
 *
 * APPROACH 1: Shrinking boundaries (top/bottom/left/right) → spiralOrder
 *   1. Walk the top row left→right, then top++; the right column top→bottom, then right--.
 *   2. If rows remain, walk the bottom row right→left, then bottom--.
 *   3. If columns remain, walk the left column bottom→top, then left++.
 *   Intuition: each lap peels off the outer ring; the two checks stop a single
 *   leftover row or column from being read twice.
 *   TIME  : O(m · n)    SPACE : O(1)
 *
 * APPROACH 2: Direction vectors + visited grid             → spiralOrderSimulation
 *   1. Move in the current direction (right, down, left, up).
 *   2. If the next cell is outside the grid or already visited, turn clockwise.
 *   Intuition: simulate exactly what you'd do with a pencil; no boundary bookkeeping.
 *   TIME  : O(m · n)    SPACE : O(m · n) for the visited grid
 *
 * WHICH TO USE:
 * Approach 1 — O(1) extra space. Approach 2 is a good fallback if the boundary
 * edge cases (single row/column) trip you up.
 * ============================================================
 */
public class SpiralMatrix {

    /** Approach 1 — Shrinking boundaries. TIME O(m·n) · SPACE O(1) extra */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            for (int c = left; c <= right; c++) result.add(matrix[top][c]);
            top++;
            for (int r = top; r <= bottom; r++) result.add(matrix[r][right]);
            right--;
            if (top <= bottom) {
                for (int c = right; c >= left; c--) result.add(matrix[bottom][c]);
                bottom--;
            }
            if (left <= right) {
                for (int r = bottom; r >= top; r--) result.add(matrix[r][left]);
                left++;
            }
        }
        return result;
    }

    /** Approach 2 — Direction vectors + visited grid. TIME O(m·n) · SPACE O(m·n) */
    public List<Integer> spiralOrderSimulation(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] dr = {0, 1, 0, -1};   // right, down, left, up
        int[] dc = {1, 0, -1, 0};
        boolean[][] visited = new boolean[m][n];
        List<Integer> result = new ArrayList<>(m * n);
        int r = 0, c = 0, dir = 0;
        for (int step = 0; step < m * n; step++) {
            result.add(matrix[r][c]);
            visited[r][c] = true;
            int nr = r + dr[dir], nc = c + dc[dir];
            if (nr < 0 || nr >= m || nc < 0 || nc >= n || visited[nr][nc]) {
                dir = (dir + 1) % 4;
                nr = r + dr[dir];
                nc = c + dc[dir];
            }
            r = nr;
            c = nc;
        }
        return result;
    }
}
