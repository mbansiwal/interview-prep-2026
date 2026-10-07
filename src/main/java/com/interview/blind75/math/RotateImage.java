package com.interview.blind75.math;

/**
 * ============================================================
 * PROBLEM : Rotate Image
 * LINK    : https://leetcode.com/problems/rotate-image/
 * DIFFICULTY: Medium
 * PATTERN : Math / Matrix
 * ============================================================
 *
 * DESCRIPTION:
 * Given an n x n 2D matrix representing an image, rotate it 90 degrees clockwise in-place.
 *
 * EXAMPLES:
 *   Input : [[1,2,3],[4,5,6],[7,8,9]]
 *   Output: [[7,4,1],[8,5,2],[9,6,3]]
 *
 * CONSTRAINTS:
 *   - n x n matrix
 *   - Must be done in-place
 *
 * ============================================================
 * APPROACH: Transpose + Reverse Each Row
 * ============================================================
 * Step 1 — Transpose: swap matrix[i][j] with matrix[j][i].
 * Step 2 — Reverse each row.
 *
 * WHY THIS WORKS:
 * Transposing swaps rows and columns. Then reversing each row completes
 * the 90-degree clockwise rotation. The two steps together are mathematically
 * equivalent to a 90° CW rotation.
 *
 * TIME  : O(n^2)
 * SPACE : O(1)
 * ============================================================
 */
public class RotateImage {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // Reverse each row
        for (int[] row : matrix) {
            int l = 0, r = n - 1;
            while (l < r) {
                int temp = row[l]; row[l] = row[r]; row[r] = temp;
                l++; r--;
            }
        }
    }
}
