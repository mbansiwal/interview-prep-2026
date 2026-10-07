package com.interview.blind75.graphs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SurroundedRegionsTest {

    private final SurroundedRegions solution = new SurroundedRegions();

    @Test
    void capturesInnerO() {
        char[][] board = {
            {'X','X','X','X'},
            {'X','O','O','X'},
            {'X','X','O','X'},
            {'X','O','X','X'}
        };
        solution.solve(board);
        assertEquals('X', board[1][1]);
        assertEquals('O', board[3][1]); // border-connected
    }

    @Test
    void allX() {
        char[][] board = {{'X','X'},{'X','X'}};
        solution.solve(board);
        assertEquals('X', board[0][0]);
    }

    @Test
    void borderO() {
        char[][] board = {{'O'}};
        solution.solve(board);
        assertEquals('O', board[0][0]);
    }
}
