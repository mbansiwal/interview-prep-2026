package com.interview.blind75.graphs;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NumberOfIslandsTest {

    private static final NumberOfIslands solution = new NumberOfIslands();

    static Stream<Named<ToIntFunction<char[][]>>> approaches() {
        return Stream.of(
                Named.of("dfs", solution::numIslands),
                Named.of("bfs", solution::numIslandsBfs),
                Named.of("union-find", solution::numIslandsUnionFind));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void oneIsland(ToIntFunction<char[][]> approach) {
        char[][] grid = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        assertEquals(1, approach.applyAsInt(grid));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void threeIslands(ToIntFunction<char[][]> approach) {
        char[][] grid = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        assertEquals(3, approach.applyAsInt(grid));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void allWater(ToIntFunction<char[][]> approach) {
        assertEquals(0, approach.applyAsInt(new char[][]{{'0','0'},{'0','0'}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void diagonalCellsAreSeparateIslands(ToIntFunction<char[][]> approach) {
        char[][] grid = {
            {'1','0','1'},
            {'0','1','0'},
            {'1','0','1'}
        };
        assertEquals(5, approach.applyAsInt(grid));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void ringAroundWater(ToIntFunction<char[][]> approach) {
        char[][] grid = {
            {'1','1','1'},
            {'1','0','1'},
            {'1','1','1'}
        };
        assertEquals(1, approach.applyAsInt(grid));
    }
}
