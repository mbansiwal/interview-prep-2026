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
 *   - Follow-up: in-place with O(1) extra space
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * Zeroing as you scan is wrong — new zeros would wipe extra rows/columns — so
 * every approach records first and writes second. Copying the whole matrix is
 * O(m · n) extra space.
 *
 * APPROACH 1: First row and column as markers (O(1) space) → setZeroes
 *   1. Remember separately whether row 0 / column 0 contain a zero.
 *   2. For every inner zero at (i, j), mark matrix[i][0] = 0 and matrix[0][j] = 0.
 *   3. Zero inner cells whose row or column is marked.
 *   4. Finally zero row 0 / column 0 if their flags are set.
 *   Intuition: the first row/column already exist, so reuse them as the marker arrays.
 *   TIME  : O(m · n)    SPACE : O(1)
 *
 * APPROACH 2: Separate row and column flag arrays          → setZeroesWithFlags
 *   1. One pass: rowZero[i] = true and colZero[j] = true for each zero.
 *   2. Second pass: zero any cell whose row or column is flagged.
 *   Intuition: the simplest correct version; Approach 1 just folds these arrays into the matrix.
 *   TIME  : O(m · n)    SPACE : O(m + n)
 *
 * WHICH TO USE:
 * Explain Approach 2 first, then answer the O(1)-space follow-up with Approach 1.
 * ============================================================
 */
public class SetMatrixZeroes {

    /** Approach 1 — First row/column as in-place markers. TIME O(m·n) · SPACE O(1) */
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

    /** Approach 2 — Row and column flag arrays. TIME O(m·n) · SPACE O(m + n) */
    public void setZeroesWithFlags(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] rowZero = new boolean[m], colZero = new boolean[n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (matrix[i][j] == 0) { rowZero[i] = true; colZero[j] = true; }

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (rowZero[i] || colZero[j]) matrix[i][j] = 0;
    }
}
