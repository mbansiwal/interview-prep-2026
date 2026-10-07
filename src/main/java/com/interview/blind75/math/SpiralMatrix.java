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
 * APPROACH: Shrinking Boundary (top/bottom/left/right)
 * ============================================================
 * Maintain four boundary pointers. Traverse:
 * 1. Left to right along top row, then top++.
 * 2. Top to bottom along right column, then right--.
 * 3. Right to left along bottom row (if top <= bottom), then bottom--.
 * 4. Bottom to top along left column (if left <= right), then left++.
 * Repeat while top <= bottom AND left <= right.
 *
 * WHY THIS WORKS:
 * Each spiral layer shrinks the boundaries inward. Boundary checks within
 * steps 3 and 4 prevent double-counting single rows/columns.
 *
 * TIME  : O(m * n)
 * SPACE : O(1) — output list not counted
 * ============================================================
 */
public class SpiralMatrix {

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
}
