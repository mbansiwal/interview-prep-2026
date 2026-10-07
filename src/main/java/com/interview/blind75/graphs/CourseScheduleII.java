package com.interview.blind75.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * PROBLEM : Course Schedule II
 * LINK    : https://leetcode.com/problems/course-schedule-ii/
 * DIFFICULTY: Medium
 * PATTERN : Topological Sort (DFS Post-order)
 * ============================================================
 *
 * DESCRIPTION:
 * Return a valid order to finish all courses, or empty array if impossible (cycle).
 *
 * EXAMPLES:
 *   Input : numCourses = 2, prerequisites = [[1,0]]
 *   Output: [0,1]
 *
 *   Input : numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 *   Output: [0,2,1,3] or [0,1,2,3]
 *
 * CONSTRAINTS:
 *   - 1 <= numCourses <= 2000
 *
 * ============================================================
 * APPROACH: DFS Post-order → Topological Order
 * ============================================================
 * 1. Same 3-color DFS as CourseSchedule.
 * 2. After visiting all neighbors, push node to result list.
 * 3. Return the post-order list as-is (no reverse needed — see below).
 *
 * WHY THIS WORKS:
 * Edges point course → prerequisite. Post-order adds a node only after
 * every prerequisite reachable from it has been added, so prerequisites
 * always appear first. That list is already a valid course order.
 * (If edges pointed prereq → course instead, you would reverse it.)
 *
 * TIME  : O(V + E)
 * SPACE : O(V + E)
 * ============================================================
 */
public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        for (int[] pre : prerequisites) adj.get(pre[0]).add(pre[1]);

        int[] state = new int[numCourses];
        List<Integer> order = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(adj, state, i, order)) return new int[0];
        }

        int[] result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) result[i] = order.get(i);
        return result;
    }

    private boolean dfs(List<List<Integer>> adj, int[] state, int node, List<Integer> order) {
        if (state[node] == 1) return false;
        if (state[node] == 2) return true;
        state[node] = 1;
        for (int neighbor : adj.get(node)) {
            if (!dfs(adj, state, neighbor, order)) return false;
        }
        state[node] = 2;
        order.add(node);
        return true;
    }
}
