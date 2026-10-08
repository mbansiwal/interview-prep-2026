package com.interview.blind75.arrays;

/**
 * ============================================================
 * PROBLEM : Valid Sudoku
 * LINK    : https://leetcode.com/problems/valid-sudoku/
 * DIFFICULTY: Medium
 * PATTERN : Arrays & Hashing
 * ============================================================
 *
 * DESCRIPTION:
 * Determine if a 9×9 Sudoku board is valid. Only the filled cells
 * need to be validated according to these rules:
 * Each row/column/3×3 box must contain digits 1-9 without repetition.
 *
 * EXAMPLES:
 *   Input : valid 9×9 board
 *   Output: true
 *
 *   Input : board with duplicate in a row
 *   Output: false
 *
 * CONSTRAINTS:
 *   - board[i][j] is a digit '1'-'9' or '.'
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Three boolean "seen" tables  (primary)
 *   1. rowSeen[r][d], colSeen[c][d], boxSeen[b][d] say whether digit d+1 was seen.
 *   2. For each filled cell: d = digit − '1', b = (r/3)*3 + c/3.
 *   3. If any of the three flags is already set → false; otherwise set all three.
 *   Intuition: 27 units (9 rows, 9 cols, 9 boxes), each with 9 flags, checked in one pass.
 *   TIME O(81) = O(1) · SPACE O(243) = O(1)
 *
 * APPROACH 2: Bitmasks — one int per unit
 *   1. rows[r], cols[c], boxes[b] are 9-bit masks; bit d means "digit d+1 seen".
 *   2. For each filled cell, bit = 1 << d. If (rows[r] | cols[c] | boxes[b]) & bit ≠ 0 → false.
 *   3. Otherwise OR the bit into all three masks.
 *   Intuition: the same flags packed into 27 ints — fewer allocations, one AND per check.
 *   TIME O(81) = O(1) · SPACE O(27) = O(1)
 *
 * WHICH TO USE:
 *   Both are O(1) for a fixed 9×9 board. #1 is clearest; #2 is a nice touch if
 *   asked to optimise memory. HashSet-of-strings versions work but are slower.
 * ============================================================
 */
public class ValidSudoku {

    /** Approach 1 — three boolean[9][9] tables. TIME O(1) (81 cells) · SPACE O(1) */
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rowSeen = new boolean[9][9];
        boolean[][] colSeen = new boolean[9][9];
        boolean[][] boxSeen = new boolean[9][9];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char cell = board[r][c];
                if (cell == '.') continue;

                int d = cell - '1';
                int box = (r / 3) * 3 + (c / 3);

                if (rowSeen[r][d] || colSeen[c][d] || boxSeen[box][d]) return false;
                rowSeen[r][d] = colSeen[c][d] = boxSeen[box][d] = true;
            }
        }
        return true;
    }

    /** Approach 2 — one 9-bit mask per row/col/box. TIME O(1) (81 cells) · SPACE O(1) */
    public boolean isValidSudokuBitmask(char[][] board) {
        int[] rows = new int[9], cols = new int[9], boxes = new int[9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char cell = board[r][c];
                if (cell == '.') continue;
                int bit = 1 << (cell - '1');
                int box = (r / 3) * 3 + (c / 3);
                if (((rows[r] | cols[c] | boxes[box]) & bit) != 0) return false;
                rows[r] |= bit;
                cols[c] |= bit;
                boxes[box] |= bit;
            }
        }
        return true;
    }
}
