package com.interview.blind75.graphs;

import java.util.Deque;
import java.util.ArrayDeque;
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
 * APPROACHES  (V = numCourses, E = prerequisites.length)
 * ============================================================
 * APPROACH 1: DFS post-order (3-colour) → topological order
 * 1. Same 3-colour DFS as Course Schedule, along course → prerequisite edges.
 * 2. Append a course after all its prerequisites have been appended (post-order).
 * 3. Return that list as-is; a grey-node hit means a cycle → return [].
 * Intuition: because edges point to prerequisites, post-order already lists them first
 * (with prerequisite → course edges you'd reverse the list instead).
 * TIME  : O(V + E)
 * SPACE : O(V + E) adjacency + O(V) state/order + O(V) recursion depth
 *
 * APPROACH 2: Kahn's algorithm (BFS)
 * 1. Edges prerequisite → course; compute indegrees.
 * 2. Start with all indegree-0 courses; each time one is taken, append it to the order and
 *    decrement its dependents, queuing those that reach 0.
 * 3. If fewer than V courses were taken there's a cycle → return [].
 * Intuition: the order in which courses become "free" is a valid schedule.
 * TIME  : O(V + E)
 * SPACE : O(V + E) — no recursion
 *
 * WHICH TO USE:
 * Kahn's is the most common interview answer (iterative, the order falls out directly,
 * and it's easy to get the lexicographically smallest order by swapping in a min-heap).
 * DFS post-order is equally optimal — explain the edge-direction subtlety.
 * ============================================================
 */
public class CourseScheduleII {

    /** Approach 1 — DFS post-order. TIME O(V + E) · SPACE O(V + E) */
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

    /** Approach 2 — Kahn's BFS topological sort. TIME O(V + E) · SPACE O(V + E) */
    public int[] findOrderKahn(int numCourses, int[][] prerequisites) {
        List<List<Integer>> dependents = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) dependents.add(new ArrayList<>());
        int[] indegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            dependents.get(pre[1]).add(pre[0]);
            indegree[pre[0]]++;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) if (indegree[i] == 0) queue.offer(i);

        int[] order = new int[numCourses];
        int taken = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            order[taken++] = course;
            for (int next : dependents.get(course)) {
                if (--indegree[next] == 0) queue.offer(next);
            }
        }
        return taken == numCourses ? order : new int[0];
    }
}
