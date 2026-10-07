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
 * APPROACH: Treat as Flat Sorted Array
 * ============================================================
 * 1. Binary search with virtual indices 0..m*n-1.
 * 2. Convert mid index to (row, col): row=mid/cols, col=mid%cols.
 * 3. Compare matrix[row][col] with target and adjust bounds.
 *
 * WHY THIS WORKS:
 * The row-ordering guarantee means the matrix IS a sorted array if
 * flattened. We can binary search without actually flattening it.
 *
 * TIME  : O(log(m*n))
 * SPACE : O(1)
 * ============================================================
 */
public class SearchA2DMatrix {

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
}
