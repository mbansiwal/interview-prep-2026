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
 * APPROACH: Three boolean "seen" tables
 * ============================================================
 * 1. Create boolean[9][9] tables: rowSeen, colSeen, boxSeen.
 *    rowSeen[r][d] == true means digit d+1 already appears in row r.
 * 2. For each cell (r, c) holding a digit:
 *    a. d = digit - '1'  (maps '1'..'9' to 0..8).
 *    b. box = (r/3)*3 + (c/3)  (numbers the 3×3 boxes 0..8, row by row).
 *    c. If rowSeen[r][d], colSeen[c][d] or boxSeen[box][d] is already
 *       true → duplicate → return false.
 *    d. Otherwise mark all three true.
 * 3. No duplicates found → return true.
 *
 * WHY THIS WORKS:
 * Each of the 27 units (9 rows, 9 cols, 9 boxes) gets its own "seen"
 * row of 9 flags, so a single pass checks all three rules at once.
 * Plain arrays are clearer and faster than HashSets for a fixed 1-9 range.
 *
 * TIME  : O(81) = O(1)
 * SPACE : O(243) = O(1) — three 9×9 tables
 * ============================================================
 */
public class ValidSudoku {

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
}
