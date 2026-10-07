package com.interview.blind75.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * PROBLEM : Course Schedule
 * LINK    : https://leetcode.com/problems/course-schedule/
 * DIFFICULTY: Medium
 * PATTERN : Topological Sort / Cycle Detection (DFS)
 * ============================================================
 *
 * DESCRIPTION:
 * Given numCourses and prerequisites[i] = [a, b] (must take b before a),
 * return true if you can finish all courses (i.e., no cycle in dependency graph).
 *
 * EXAMPLES:
 *   Input : numCourses = 2, prerequisites = [[1,0]]
 *   Output: true
 *
 *   Input : numCourses = 2, prerequisites = [[1,0],[0,1]]
 *   Output: false  (cycle!)
 *
 * CONSTRAINTS:
 *   - 1 <= numCourses <= 2000
 *   - 0 <= prerequisites.length <= 5000
 *
 * ============================================================
 * APPROACH: DFS Cycle Detection with 3-Color Marking
 * ============================================================
 * state[i] = 0: unvisited, 1: in current DFS path (gray), 2: done (black)
 * 1. For each unvisited node, run DFS.
 * 2. If we reach a gray node → cycle detected → return false.
 * 3. Mark node black after processing all its neighbors.
 *
 * WHY THIS WORKS:
 * A gray node means it's an ancestor in the current DFS path — reaching it again
 * means we have a back edge, which is a cycle. Black nodes are safe (fully explored).
 *
 * TIME  : O(V + E) — V courses, E prerequisites
 * SPACE : O(V + E) — adjacency list + state array
 * ============================================================
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        for (int[] pre : prerequisites) adj.get(pre[0]).add(pre[1]);

        int[] state = new int[numCourses]; // 0=unvisited, 1=visiting, 2=done
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(adj, state, i)) return false;
        }
        return true;
    }

    private boolean dfs(List<List<Integer>> adj, int[] state, int node) {
        if (state[node] == 1) return false; // cycle
        if (state[node] == 2) return true;  // already processed
        state[node] = 1;
        for (int neighbor : adj.get(node)) {
            if (!dfs(adj, state, neighbor)) return false;
        }
        state[node] = 2;
        return true;
    }
}
