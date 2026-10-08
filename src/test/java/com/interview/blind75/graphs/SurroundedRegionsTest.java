package com.interview.blind75.graphs;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SurroundedRegionsTest {

    private static final SurroundedRegions solution = new SurroundedRegions();

    static Stream<Named<Consumer<char[][]>>> approaches() {
        return Stream.of(
                Named.of("dfs", solution::solve),
                Named.of("bfs", solution::solveBfs));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void capturesInnerO(Consumer<char[][]> approach) {
        char[][] board = {
            {'X','X','X','X'},
            {'X','O','O','X'},
            {'X','X','O','X'},
            {'X','O','X','X'}
        };
        approach.accept(board);
        assertArrayEquals(new char[][]{
            {'X','X','X','X'},
            {'X','X','X','X'},
            {'X','X','X','X'},
            {'X','O','X','X'}
        }, board);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void allX(Consumer<char[][]> approach) {
        char[][] board = {{'X','X'},{'X','X'}};
        approach.accept(board);
        assertArrayEquals(new char[][]{{'X','X'},{'X','X'}}, board);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void borderO(Consumer<char[][]> approach) {
        char[][] board = {{'O'}};
        approach.accept(board);
        assertArrayEquals(new char[][]{{'O'}}, board);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void innerRegionConnectedToBorderSurvives(Consumer<char[][]> approach) {
        char[][] board = {
            {'X','O','X','X'},
            {'X','O','O','X'},
            {'X','X','X','X'},
            {'X','X','O','X'}
        };
        approach.accept(board);
        assertArrayEquals(new char[][]{
            {'X','O','X','X'},
            {'X','O','O','X'},
            {'X','X','X','X'},
            {'X','X','O','X'}
        }, board);
    }
}
