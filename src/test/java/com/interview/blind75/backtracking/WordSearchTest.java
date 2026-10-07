package com.interview.blind75.backtracking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WordSearchTest {

    private final WordSearch solution = new WordSearch();

    private char[][] board() {
        return new char[][]{
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };
    }

    @Test
    void wordExists() {
        assertTrue(solution.exist(board(), "ABCCED"));
    }

    @Test
    void wordExistsSee() {
        assertTrue(solution.exist(board(), "SEE"));
    }

    @Test
    void wordNotExists() {
        assertFalse(solution.exist(board(), "ABCB"));
    }
}
