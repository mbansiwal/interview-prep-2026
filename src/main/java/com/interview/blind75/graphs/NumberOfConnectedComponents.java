package com.interview.blind75.graphs;

import java.util.List;
import java.util.ArrayList;
/**
 * ============================================================
 * PROBLEM : Number of Connected Components in an Undirected Graph
 * LINK    : https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/ (Premium)
 *           https://neetcode.io/problems/count-connected-components
 * DIFFICULTY: Medium
 * PATTERN : Union-Find (Disjoint Set Union)
 * ============================================================
 *
 * DESCRIPTION:
 * You have n nodes labelled 0..n-1 and a list of undirected edges.
 * Return the number of connected components in the graph.
 *
 * EXAMPLES:
 *   Input : n = 5, edges = [[0,1],[1,2],[3,4]]
 *   Output: 2   ({0,1,2} and {3,4})
 *
 *   Input : n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
 *   Output: 1
 *
 * CONSTRAINTS:
 *   - 1 <= n <= 2000
 *   - 0 <= edges.length <= 5000
 *   - No repeated edges
 *
 * ============================================================
 * APPROACHES  (n nodes, E = edges.length)
 * ============================================================
 * APPROACH 1: Union-Find with path compression + union by rank
 * 1. Start with n components — every node is its own parent.
 * 2. For each edge, find both roots; if they differ, union them and decrement the count.
 * 3. Return the remaining count.
 * Intuition: every successful union merges exactly two components.
 * TIME  : O(n + E · α(n)) ≈ O(n + E)
 * SPACE : O(n) — no adjacency list needed
 *
 * APPROACH 2: DFS over an adjacency list
 * 1. Build an undirected adjacency list.
 * 2. For each unvisited node, count a component and DFS to mark everything reachable.
 * Intuition: one traversal per component.
 * TIME  : O(n + E)
 * SPACE : O(n + E) adjacency + O(n) visited + O(n) recursion depth
 *
 * WHICH TO USE:
 * Both are optimal. Union-Find uses less memory and handles edges arriving one at a time
 * (dynamic connectivity); DFS/BFS is the simplest to explain. BFS works the same as DFS
 * if you want to avoid recursion.
 * ============================================================
 */
public class NumberOfConnectedComponents {

    /** Approach 1 — Union-Find. TIME O(n + E · α(n)) · SPACE O(n) */
    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        int components = n;
        for (int[] edge : edges) {
            int rootU = find(parent, edge[0]);
            int rootV = find(parent, edge[1]);
            if (rootU == rootV) continue;

            if (rank[rootU] < rank[rootV]) { int t = rootU; rootU = rootV; rootV = t; }
            parent[rootV] = rootU;
            if (rank[rootU] == rank[rootV]) rank[rootU]++;
            components--;
        }
        return components;
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    /** Approach 2 — DFS over adjacency list. TIME O(n + E) · SPACE O(n + E) */
    public int countComponentsDfs(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        int components = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                components++;
                dfs(adj, visited, i);
            }
        }
        return components;
    }

    private void dfs(List<List<Integer>> adj, boolean[] visited, int node) {
        visited[node] = true;
        for (int next : adj.get(node)) {
            if (!visited[next]) dfs(adj, visited, next);
        }
    }
}
