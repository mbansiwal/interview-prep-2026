package com.interview.blind75.binarysearch;

/**
 * ============================================================
 * PROBLEM : Search a 2D Matrix
 * LINK    : https://leetcode.com/problems/search-a-2d-matrix/
 * DIFFICULTY: Medium
 * PATTERN : Binary Search
 * ============================================================
 *
 * DESCRIPTION:
 * Search for a value in an m×n matrix where:
 * - Each row is sorted left to right.
 * - The first integer of each row > last integer of previous row.
 *
 * EXAMPLES:
 *   Input : matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 *   Output: true
 *
 *   Input : matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 *   Output: false
 *
 * CONSTRAINTS:
 *   - m == matrix.length, n == matrix[i].length
 *   - 1 <= m, n <= 100
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: One binary search over the flattened matrix  (primary)
 *   1. Treat the m×n matrix as one sorted array of length m·n.
 *   2. Index k maps to matrix[k / n][k % n].
 *   Intuition: rows are sorted and each row starts after the previous ends,
 *   so row-major order is fully sorted.
 *   TIME O(log(m·n)) · SPACE O(1)
 *
 * APPROACH 2: Two binary searches — pick the row, then the column
 *   1. Binary-search the first column for the last row whose first value ≤ target.
 *   2. Binary-search inside that row.
 *   Intuition: the same total work, split into two easier-to-explain searches.
 *   TIME O(log m + log n) = O(log(m·n)) · SPACE O(1)
 *
 * APPROACH 3: Staircase search from the top-right corner
 *   1. Start at (0, n−1). Bigger than target → move left; smaller → move down.
 *   Intuition: each step rules out a whole row or column. Also solves
 *   "Search a 2D Matrix II", where only rows and columns are sorted.
 *   TIME O(m + n) · SPACE O(1)
 *
 * WHICH TO USE:
 *   #1 is the expected optimal answer; #2 avoids the index arithmetic.
 *   Mention #3 as the general technique for the weaker ordering (LC 240).
 * ============================================================
 */
public class SearchA2DMatrix {

    /** Approach 1 — binary search on the flattened index. TIME O(log(m·n)) · SPACE O(1) */
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length, cols = matrix[0].length;
        int left = 0, right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int value = matrix[mid / cols][mid % cols];
            if (value == target) return true;
            else if (value < target) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }

    /** Approach 2 — binary search the row, then the column. TIME O(log m + log n) · SPACE O(1) */
    public boolean searchMatrixTwoPhase(int[][] matrix, int target) {
        int top = 0, bottom = matrix.length - 1, row = -1;
        while (top <= bottom) {
            int mid = top + (bottom - top) / 2;
            if (matrix[mid][0] <= target) {
                row = mid;
                top = mid + 1;
            } else {
                bottom = mid - 1;
            }
        }
        if (row == -1) return false;

        int left = 0, right = matrix[row].length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[row][mid] == target) return true;
            if (matrix[row][mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }

    /** Approach 3 — staircase from the top-right corner. TIME O(m + n) · SPACE O(1) */
    public boolean searchMatrixStaircase(int[][] matrix, int target) {
        int r = 0, c = matrix[0].length - 1;
        while (r < matrix.length && c >= 0) {
            int value = matrix[r][c];
            if (value == target) return true;
            if (value > target) c--;
            else r++;
        }
        return false;
    }
}
