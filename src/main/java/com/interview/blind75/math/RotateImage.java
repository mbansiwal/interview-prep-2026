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
 * APPROACHES
 * ============================================================
 * Copying into a new matrix (result[j][n-1-i] = matrix[i][j]) is O(n²) space and
 * breaks the in-place rule.
 *
 * APPROACH 1: Transpose, then reverse each row             → rotate
 *   1. Transpose: swap matrix[i][j] with matrix[j][i].
 *   2. Reverse every row.
 *   Intuition: transpose is a flip across the diagonal; the row reversal is a flip
 *   across the vertical axis — two reflections make a 90° clockwise rotation.
 *   TIME  : O(n²)    SPACE : O(1)
 *
 * APPROACH 2: Rotate four cells at a time, layer by layer  → rotateLayers
 *   1. For each layer (ring) from the outside in, and each offset along its top edge:
 *   2. Cycle the four matching cells: left → top, bottom → left,
 *      right → bottom, top → right, using one temp variable.
 *   Intuition: every cell moves exactly once, straight to its final position.
 *   TIME  : O(n²)    SPACE : O(1)
 *
 * WHICH TO USE:
 * Approach 1 is easiest to get right under pressure; Approach 2 makes half as many
 * writes and shows you can reason about the index mapping directly.
 * ============================================================
 */
public class RotateImage {

    /** Approach 1 — Transpose + reverse rows. TIME O(n²) · SPACE O(1) */
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

    /** Approach 2 — Four-way swap per layer. TIME O(n²) · SPACE O(1) */
    public void rotateLayers(int[][] matrix) {
        int n = matrix.length;
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer, last = n - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                int top = matrix[first][i];
                matrix[first][i] = matrix[last - offset][first];          // left   → top
                matrix[last - offset][first] = matrix[last][last - offset]; // bottom → left
                matrix[last][last - offset] = matrix[i][last];            // right  → bottom
                matrix[i][last] = top;                                     // top    → right
            }
        }
    }
}
