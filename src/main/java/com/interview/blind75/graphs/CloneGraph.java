package com.interview.blind75.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ============================================================
 * PROBLEM : Clone Graph
 * LINK    : https://leetcode.com/problems/clone-graph/
 * DIFFICULTY: Medium
 * PATTERN : DFS / BFS + HashMap
 * ============================================================
 *
 * DESCRIPTION:
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone).
 * Each node has a val (int) and a list of neighbors.
 *
 * EXAMPLES:
 *   Input : adjList = [[2,4],[1,3],[2,4],[1,3]]
 *   Output: [[2,4],[1,3],[2,4],[1,3]]  (deep copy)
 *
 * CONSTRAINTS:
 *   - 1 <= Node.val <= 100
 *   - No repeated edges or self-loops
 *
 * ============================================================
 * APPROACH: DFS + HashMap (old node → new node)
 * ============================================================
 * 1. Use a HashMap to map each original node to its clone.
 * 2. DFS: if node is already cloned, return the clone.
 * 3. Otherwise, create a new node, add it to the map, then DFS all neighbors.
 *
 * WHY THIS WORKS:
 * The HashMap serves as both the visited set and the clone registry,
 * preventing infinite loops in cyclic graphs.
 *
 * TIME  : O(V + E) — visit every node and edge once
 * SPACE : O(V) — HashMap + recursion stack
 * ============================================================
 */
public class CloneGraph {

    static class Node {
        int val;
        List<Node> neighbors;
        Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }

    public Node cloneGraph(Node node) {
        return clone(node, new HashMap<>());
    }

    private Node clone(Node node, Map<Node, Node> cloneMap) {
        if (node == null) return null;
        if (cloneMap.containsKey(node)) return cloneMap.get(node);

        Node copy = new Node(node.val);
        cloneMap.put(node, copy);
        for (Node neighbor : node.neighbors) {
            copy.neighbors.add(clone(neighbor, cloneMap));
        }
        return copy;
    }
}
