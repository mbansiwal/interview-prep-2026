package com.interview.blind75.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ValidSudokuApproachesTest {

    static Stream<Predicate<char[][]>> approaches() {
        ValidSudoku s = new ValidSudoku();
        return Stream.of(s::isValidSudoku, s::isValidSudokuBitmask);
    }

    private static char[][] board(String... rows) {
        char[][] b = new char[9][];
        for (int i = 0; i < 9; i++) b[i] = rows[i].toCharArray();
        return b;
    }

    private static char[][] validBoard() {
        return board("53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1",
                     "7...2...6", ".6....28.", "...419..5", "....8..79");
    }

    @ParameterizedTest @MethodSource("approaches")
    void leetCodeValidBoard(Predicate<char[][]> f) { assertTrue(f.test(validBoard())); }

    @ParameterizedTest @MethodSource("approaches")
    void duplicateInBox(Predicate<char[][]> f) {
        char[][] b = validBoard();
        b[0][0] = '8'; // 8 is already at [2][2], in the same top-left box
        assertFalse(f.test(b));
    }

    @ParameterizedTest @MethodSource("approaches")
    void duplicateInColumn(Predicate<char[][]> f) {
        char[][] b = validBoard();
        b[8][0] = '5'; // column 0 already has 5 at row 0
        assertFalse(f.test(b));
    }

    @ParameterizedTest @MethodSource("approaches")
    void emptyBoard(Predicate<char[][]> f) {
        String e = ".........";
        assertTrue(f.test(board(e, e, e, e, e, e, e, e, e)));
    }
}
