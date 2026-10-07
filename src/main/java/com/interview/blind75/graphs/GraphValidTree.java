package com.interview.blind75.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * PROBLEM : Graph Valid Tree
 * LINK    : https://leetcode.com/problems/graph-valid-tree/ (Premium)
 *           https://neetcode.io/problems/valid-tree
 * DIFFICULTY: Medium
 * PATTERN : Union-Find / DFS
 * ============================================================
 *
 * DESCRIPTION:
 * Given n nodes (0..n-1) and a list of undirected edges, return true if
 * these edges make a valid tree (connected + no cycles).
 *
 * EXAMPLES:
 *   Input : n=5, edges=[[0,1],[0,2],[0,3],[1,4]]
 *   Output: true
 *
 *   Input : n=5, edges=[[0,1],[1,2],[2,3],[1,3],[1,4]]
 *   Output: false (cycle between 1-2-3)
 *
 * CONSTRAINTS:
 *   - 1 <= n <= 2000
 *   - 0 <= edges.length <= 5000
 *
 * ============================================================
 * APPROACH: Union-Find — Tree IFF n-1 edges AND no cycle
 * ============================================================
 * A valid tree is connected AND acyclic; it always has exactly n-1 edges.
 * 1. If edges.length != n-1, return false immediately.
 * 2. Process each edge with Union-Find.
 * 3. If find(u) == find(v) before union → cycle → return false.
 *
 * WHY THIS WORKS:
 * An acyclic graph with c connected components has exactly n - c edges.
 * So if it has n-1 edges and no cycle, then n - c = n - 1 → c = 1 (connected).
 *
 * TIME  : O(n * α(n)) ≈ O(n)
 * SPACE : O(n)
 * ============================================================
 */
public class GraphValidTree {

    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) return false;
        int[] parent = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int[] edge : edges) {
            int pu = find(parent, edge[0]);
            int pv = find(parent, edge[1]);
            if (pu == pv) return false;
            union(parent, rank, pu, pv);
        }
        return true;
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
