package com.interview.blind75.graphs;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GraphValidTreeTest {

    private static final GraphValidTree solution = new GraphValidTree();

    static Stream<Named<BiPredicate<Integer, int[][]>>> approaches() {
        return Stream.of(
                Named.of("union-find", solution::validTree),
                Named.of("bfs", solution::validTreeBfs));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void validTree(BiPredicate<Integer, int[][]> approach) {
        assertTrue(approach.test(5, new int[][]{{0,1},{0,2},{0,3},{1,4}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void cycleInvalid(BiPredicate<Integer, int[][]> approach) {
        assertFalse(approach.test(5, new int[][]{{0,1},{1,2},{2,3},{1,3},{1,4}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleNode(BiPredicate<Integer, int[][]> approach) {
        assertTrue(approach.test(1, new int[][]{}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void rightEdgeCountButDisconnectedWithCycle(BiPredicate<Integer, int[][]> approach) {
        // 4 nodes, 3 edges, but 0-1-2 form a triangle and node 3 is isolated
        assertFalse(approach.test(4, new int[][]{{0,1},{1,2},{2,0}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void tooFewEdges(BiPredicate<Integer, int[][]> approach) {
        assertFalse(approach.test(4, new int[][]{{0,1},{2,3}}));
    }
}
