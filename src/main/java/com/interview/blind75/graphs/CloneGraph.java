package com.interview.blind75.graphs;

import java.util.Deque;
import java.util.ArrayDeque;
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
 * APPROACHES  (V nodes, E edges)
 * ============================================================
 * APPROACH 1: DFS + HashMap (original → clone)
 * 1. If a node is already cloned, return its clone (this also breaks cycles).
 * 2. Otherwise create the clone, register it, then clone each neighbour recursively.
 * Intuition: the map is both the "visited" set and the clone registry.
 * TIME  : O(V + E)
 * SPACE : O(V) map + O(V) recursion depth (a long path graph)
 *
 * APPROACH 2: BFS + HashMap
 * 1. Clone the start node and enqueue the original.
 * 2. For each dequeued node, for each neighbour: clone it on first sight and enqueue it,
 *    then wire clone(node) → clone(neighbour).
 * Intuition: same registry, iterative traversal — no recursion depth limit.
 * TIME  : O(V + E)
 * SPACE : O(V) map + O(V) queue
 *
 * WHICH TO USE:
 * Both are optimal. DFS is shortest; BFS is safer for very deep graphs.
 * The must-say point: register a clone BEFORE visiting neighbours, or cycles loop forever.
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

    /** Approach 1 — DFS + HashMap. TIME O(V + E) · SPACE O(V) */
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

    /** Approach 2 — BFS + HashMap. TIME O(V + E) · SPACE O(V) */
    public Node cloneGraphBfs(Node node) {
        if (node == null) return null;
        Map<Node, Node> cloneMap = new HashMap<>();
        cloneMap.put(node, new Node(node.val));
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (Node neighbor : current.neighbors) {
                if (!cloneMap.containsKey(neighbor)) {
                    cloneMap.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);
                }
                cloneMap.get(current).neighbors.add(cloneMap.get(neighbor));
            }
        }
        return cloneMap.get(node);
    }
}
