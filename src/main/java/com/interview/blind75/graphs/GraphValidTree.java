package com.interview.blind75.graphs;

import java.util.Deque;
import java.util.ArrayDeque;
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
 * APPROACHES  (n nodes, E = edges.length)
 * ============================================================
 * Shared first check: a tree on n nodes has exactly n - 1 edges. If not, return false.
 * With exactly n - 1 edges, "no cycle" and "connected" are equivalent (an acyclic graph with
 * c components has n - c edges), so it's enough to check ONE of them.
 *
 * APPROACH 1: Union-Find — detect a cycle
 * 1. Union the endpoints of every edge.
 * 2. If both endpoints already share a root, that edge closes a cycle → false.
 * TIME  : O(n · α(n)) ≈ O(n) (E = n - 1 after the first check)
 * SPACE : O(n)
 *
 * APPROACH 2: DFS/BFS — check connectivity
 * 1. Build an undirected adjacency list.
 * 2. BFS from node 0 and count reachable nodes.
 * 3. Valid tree iff all n nodes were reached.
 * Intuition: with n - 1 edges, reaching every node proves connectivity, hence no cycle.
 * TIME  : O(n + E) = O(n)
 * SPACE : O(n + E) adjacency + O(n) visited/queue
 *
 * WHICH TO USE:
 * Both are optimal. Union-Find needs no adjacency list and suits streamed edges;
 * BFS is the most intuitive. Always lead with the n - 1 edge check.
 * ============================================================
 */
public class GraphValidTree {

    /** Approach 1 — Union-Find cycle check. TIME O(n · α(n)) · SPACE O(n) */
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

    /** Approach 2 — BFS connectivity check. TIME O(n + E) · SPACE O(n + E) */
    public boolean validTreeBfs(int n, int[][] edges) {
        if (edges.length != n - 1) return false;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        visited[0] = true;
        int reached = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            reached++;
            for (int next : adj.get(node)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
        return reached == n;
    }
}
