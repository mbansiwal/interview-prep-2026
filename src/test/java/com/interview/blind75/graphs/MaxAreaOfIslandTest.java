package com.interview.blind75.graphs;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MaxAreaOfIslandTest {

    private static final MaxAreaOfIsland solution = new MaxAreaOfIsland();

    static Stream<Named<ToIntFunction<int[][]>>> approaches() {
        return Stream.of(
                Named.of("dfs", solution::maxAreaOfIsland),
                Named.of("bfs", solution::maxAreaOfIslandBfs));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void multipleIslands(ToIntFunction<int[][]> approach) {
        int[][] grid = {
            {0,0,1,0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,1,1,0,1,0,0,0,0,0,0,0,0},
            {0,1,0,0,1,1,0,0,1,0,1,0,0},
            {0,1,0,0,1,1,0,0,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };
        assertEquals(6, approach.applyAsInt(grid));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void noIsland(ToIntFunction<int[][]> approach) {
        assertEquals(0, approach.applyAsInt(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleCell(ToIntFunction<int[][]> approach) {
        assertEquals(1, approach.applyAsInt(new int[][]{{1}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void smallExample(ToIntFunction<int[][]> approach) {
        assertEquals(3, approach.applyAsInt(new int[][]{{1, 1, 0}, {0, 1, 0}, {0, 0, 1}}));
    }
}
