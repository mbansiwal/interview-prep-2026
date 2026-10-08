package com.interview.blind75.graphs;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PacificAtlanticWaterFlowTest {

    private static final PacificAtlanticWaterFlow solution = new PacificAtlanticWaterFlow();

    static Stream<Named<Function<int[][], List<List<Integer>>>>> approaches() {
        return Stream.of(
                Named.of("bfs", solution::pacificAtlantic),
                Named.of("dfs", solution::pacificAtlanticDfs));
    }

    private static List<List<Integer>> sorted(List<List<Integer>> cells) {
        List<List<Integer>> out = new ArrayList<>(cells);
        out.sort(Comparator.<List<Integer>>comparingInt(c -> c.get(0)).thenComparingInt(c -> c.get(1)));
        return out;
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void example(Function<int[][], List<List<Integer>>> approach) {
        int[][] heights = {
            {1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}
        };
        assertEquals(sorted(List.of(List.of(0, 4), List.of(1, 3), List.of(1, 4), List.of(2, 2),
                        List.of(3, 0), List.of(3, 1), List.of(4, 0))),
                sorted(approach.apply(heights)));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleCell(Function<int[][], List<List<Integer>>> approach) {
        assertEquals(List.of(List.of(0, 0)), approach.apply(new int[][]{{1}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void uniform(Function<int[][], List<List<Integer>>> approach) {
        assertEquals(4, approach.apply(new int[][]{{1,1},{1,1}}).size());
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void valleyInTheMiddleOnlyEdgesFlow(Function<int[][], List<List<Integer>>> approach) {
        int[][] heights = {{3, 3, 3}, {3, 1, 3}, {3, 3, 3}};
        List<List<Integer>> result = approach.apply(heights);
        assertFalse(result.contains(List.of(1, 1)));
        assertEquals(8, result.size());
    }
}
