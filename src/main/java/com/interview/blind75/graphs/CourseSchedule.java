package com.interview.blind75.graphs;

import java.util.Deque;
import java.util.ArrayDeque;
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
 * APPROACHES  (V = numCourses, E = prerequisites.length)
 * ============================================================
 * The question is "does the prerequisite graph contain a cycle?"
 *
 * APPROACH 1: DFS cycle detection with 3 colours
 * state[i] = 0 unvisited, 1 on the current DFS path (grey), 2 fully explored (black).
 * 1. DFS from every unvisited course along course → prerequisite edges.
 * 2. Reaching a grey node means a back edge → cycle → impossible.
 * 3. Mark a node black once all its prerequisites are explored.
 * Intuition: grey = "I'm still waiting on this", so meeting it again means a circular wait.
 * TIME  : O(V + E)
 * SPACE : O(V + E) adjacency + O(V) state + O(V) recursion depth
 *
 * APPROACH 2: Kahn's algorithm (BFS topological sort)
 * 1. Edges prerequisite → course; indegree[c] = number of prerequisites of c.
 * 2. Queue every course with indegree 0; repeatedly take one and decrement its dependents,
 *    queuing any that drop to 0.
 * 3. All courses can be finished iff every course was taken out of the queue.
 * Intuition: keep taking courses whose prerequisites are all done; a cycle never reaches 0.
 * TIME  : O(V + E)
 * SPACE : O(V + E) adjacency + O(V) indegree and queue — no recursion
 *
 * WHICH TO USE:
 * Both are optimal. Kahn's is iterative (no stack-overflow risk) and directly gives an order;
 * DFS colouring is the classic cycle-detection template. Know both — interviewers often ask for the other.
 * ============================================================
 */
public class CourseSchedule {

    /** Approach 1 — DFS 3-colour cycle detection. TIME O(V + E) · SPACE O(V + E) */
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

    /** Approach 2 — Kahn's BFS topological sort. TIME O(V + E) · SPACE O(V + E) */
    public boolean canFinishKahn(int numCourses, int[][] prerequisites) {
        List<List<Integer>> dependents = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) dependents.add(new ArrayList<>());
        int[] indegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            dependents.get(pre[1]).add(pre[0]);
            indegree[pre[0]]++;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) if (indegree[i] == 0) queue.offer(i);

        int taken = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            taken++;
            for (int next : dependents.get(course)) {
                if (--indegree[next] == 0) queue.offer(next);
            }
        }
        return taken == numCourses;
    }
}
