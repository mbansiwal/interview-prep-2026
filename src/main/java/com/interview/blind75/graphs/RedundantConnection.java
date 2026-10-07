package com.interview.blind75.graphs;

/**
 * ============================================================
 * PROBLEM : Redundant Connection
 * LINK    : https://leetcode.com/problems/redundant-connection/
 * DIFFICULTY: Medium
 * PATTERN : Union-Find (Disjoint Set Union)
 * ============================================================
 *
 * DESCRIPTION:
 * Given a graph that started as a tree and had one extra edge added,
 * return the extra edge. If multiple answers exist, return the last one.
 *
 * EXAMPLES:
 *   Input : [[1,2],[1,3],[2,3]]
 *   Output: [2,3]
 *
 *   Input : [[1,2],[2,3],[3,4],[1,4],[1,5]]
 *   Output: [1,4]
 *
 * CONSTRAINTS:
 *   - n nodes labeled 1..n
 *   - n edges (one redundant)
 *
 * ============================================================
 * APPROACH: Union-Find with Path Compression + Union by Rank
 * ============================================================
 * 1. Process edges one by one.
 * 2. For each edge (u, v): find roots of both.
 * 3. If same root → this edge creates a cycle → return it.
 * 4. Otherwise union the two sets.
 *
 * WHY THIS WORKS:
 * Union-Find efficiently tracks connected components.
 * The first edge connecting two already-connected nodes is the redundant one.
 *
 * TIME  : O(n * α(n)) ≈ O(n) — α is inverse Ackermann, nearly constant
 * SPACE : O(n)
 * ============================================================
 */
public class RedundantConnection {

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        int[] rank = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;

        for (int[] edge : edges) {
            int pu = find(parent, edge[0]);
            int pv = find(parent, edge[1]);
            if (pu == pv) return edge;
            union(parent, rank, pu, pv);
        }
        return new int[]{};
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    private void union(int[] parent, int[] rank, int x, int y) {
        if (rank[x] < rank[y]) { int t = x; x = y; y = t; }
        parent[y] = x;
        if (rank[x] == rank[y]) rank[x]++;
    }
}
