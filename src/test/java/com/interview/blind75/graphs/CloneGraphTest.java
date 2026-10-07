package com.interview.blind75.graphs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CloneGraphTest {

    private final CloneGraph solution = new CloneGraph();

    @Test
    void clonesSingleNode() {
        CloneGraph.Node node = new CloneGraph.Node(1);
        CloneGraph.Node clone = solution.cloneGraph(node);
        assertNotSame(node, clone);
        assertEquals(1, clone.val);
    }

    @Test
    void preservesCycleWithNewNodes() {
        CloneGraph.Node n1 = new CloneGraph.Node(1);
        CloneGraph.Node n2 = new CloneGraph.Node(2);
        n1.neighbors.add(n2);
        n2.neighbors.add(n1);

        CloneGraph.Node clone = solution.cloneGraph(n1);
        CloneGraph.Node cloneOf2 = clone.neighbors.get(0);

        assertNotSame(n1, clone);
        assertNotSame(n2, cloneOf2);
        assertEquals(2, cloneOf2.val);
        assertSame(clone, cloneOf2.neighbors.get(0));
    }

    @Test
    void reusingInstanceDoesNotLeakClones() {
        CloneGraph.Node a = new CloneGraph.Node(1);
        CloneGraph.Node first = solution.cloneGraph(a);
        CloneGraph.Node second = solution.cloneGraph(a);
        assertNotSame(first, second);
    }

    @Test
    void nullInput() {
        assertNull(solution.cloneGraph(null));
    }
}
