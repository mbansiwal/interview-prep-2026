package com.interview.blind75.graphs;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CloneGraphTest {

    private static final CloneGraph solution = new CloneGraph();

    static Stream<Named<UnaryOperator<CloneGraph.Node>>> approaches() {
        return Stream.of(
                Named.of("dfs", solution::cloneGraph),
                Named.of("bfs", solution::cloneGraphBfs));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void clonesSingleNode(UnaryOperator<CloneGraph.Node> approach) {
        CloneGraph.Node node = new CloneGraph.Node(1);
        CloneGraph.Node clone = approach.apply(node);
        assertNotSame(node, clone);
        assertEquals(1, clone.val);
        assertTrue(clone.neighbors.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void preservesCycleWithNewNodes(UnaryOperator<CloneGraph.Node> approach) {
        CloneGraph.Node n1 = new CloneGraph.Node(1);
        CloneGraph.Node n2 = new CloneGraph.Node(2);
        n1.neighbors.add(n2);
        n2.neighbors.add(n1);

        CloneGraph.Node clone = approach.apply(n1);
        CloneGraph.Node cloneOf2 = clone.neighbors.get(0);

        assertNotSame(n1, clone);
        assertNotSame(n2, cloneOf2);
        assertEquals(2, cloneOf2.val);
        assertSame(clone, cloneOf2.neighbors.get(0));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void squareGraphKeepsNeighbourOrderAndSharing(UnaryOperator<CloneGraph.Node> approach) {
        // LeetCode example: 1-2, 2-3, 3-4, 4-1
        CloneGraph.Node[] n = new CloneGraph.Node[5];
        for (int i = 1; i <= 4; i++) n[i] = new CloneGraph.Node(i);
        n[1].neighbors.add(n[2]); n[1].neighbors.add(n[4]);
        n[2].neighbors.add(n[1]); n[2].neighbors.add(n[3]);
        n[3].neighbors.add(n[2]); n[3].neighbors.add(n[4]);
        n[4].neighbors.add(n[1]); n[4].neighbors.add(n[3]);

        CloneGraph.Node c1 = approach.apply(n[1]);
        CloneGraph.Node c2 = c1.neighbors.get(0), c4 = c1.neighbors.get(1);
        assertEquals(2, c2.val);
        assertEquals(4, c4.val);
        CloneGraph.Node c3 = c2.neighbors.get(1);
        assertEquals(3, c3.val);
        assertSame(c3, c4.neighbors.get(1), "node 3 must be cloned exactly once");
        for (int i = 1; i <= 4; i++) {
            assertNotSame(n[i], i == 1 ? c1 : i == 2 ? c2 : i == 3 ? c3 : c4);
        }
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void nullInput(UnaryOperator<CloneGraph.Node> approach) {
        assertNull(approach.apply(null));
    }

    @Test
    void reusingInstanceDoesNotLeakClones() {
        CloneGraph.Node a = new CloneGraph.Node(1);
        assertNotSame(solution.cloneGraph(a), solution.cloneGraph(a));
        assertNotSame(solution.cloneGraphBfs(a), solution.cloneGraphBfs(a));
    }
}
