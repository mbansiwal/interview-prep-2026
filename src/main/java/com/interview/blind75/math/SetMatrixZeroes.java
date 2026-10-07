package com.interview.blind75.math;

/**
 * ============================================================
 * PROBLEM : Set Matrix Zeroes
 * LINK    : https://leetcode.com/problems/set-matrix-zeroes/
 * DIFFICULTY: Medium
 * PATTERN : Math / Matrix
 * ============================================================
 *
 * DESCRIPTION:
 * Given an m x n matrix, if an element is 0, set its entire row and column to 0.
 * Must be done in-place.
 *
 * EXAMPLES:
 *   Input : [[1,1,1],[1,0,1],[1,1,1]]  →  [[1,0,1],[0,0,0],[1,0,1]]
 *   Input : [[0,1,2,0],[3,4,5,2],[1,3,1,5]]  →  [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *
 * CONSTRAINTS:
 *   - m, n >= 1
 *   - Must be in-place with O(1) extra space
 *
 * ============================================================
 * APPROACH: Use First Row and First Column as Markers
 * ============================================================
 * 1. Check if first row / first column has any zero (separate flags).
 * 2. Use matrix[0][j] and matrix[i][0] to mark which rows/columns need zeroing.
 * 3. Zero cells based on markers (inner matrix only).
 * 4. Zero first row/column based on flags.
 *
 * WHY THIS WORKS:
 * The first row and column become our O(1) space "visited" markers,
 * avoiding a separate boolean array.
 *
 * TIME  : O(m * n)
 * SPACE : O(1)
 * ============================================================
 */
public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean firstRowZero = false, firstColZero = false;

        for (int j = 0; j < n; j++) if (matrix[0][j] == 0) firstRowZero = true;
        for (int i = 0; i < m; i++) if (matrix[i][0] == 0) firstColZero = true;

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (matrix[i][j] == 0) { matrix[i][0] = 0; matrix[0][j] = 0; }

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;

        if (firstRowZero) for (int j = 0; j < n; j++) matrix[0][j] = 0;
        if (firstColZero) for (int i = 0; i < m; i++) matrix[i][0] = 0;
    }
}
