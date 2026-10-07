package com.interview.blind75.graphs;

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
 * APPROACH: Union-Find with path compression + union by rank
 * ============================================================
 * 1. Start with n components — every node is its own parent.
 * 2. For each edge (u, v), find the root of each.
 * 3. If the roots differ, union them and decrement the component count.
 *    If they are the same, the edge is inside an existing component — skip.
 * 4. Return the remaining count.
 *
 * WHY THIS WORKS:
 * Each successful union merges exactly two components into one, so the
 * count drops by one per merge. Path compression and union by rank keep
 * the trees almost flat, so each find is effectively constant time.
 *
 * TIME  : O(n + E · α(n)) ≈ O(n + E) — α is the inverse Ackermann function
 * SPACE : O(n)
 * ============================================================
 */
public class NumberOfConnectedComponents {

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
}
