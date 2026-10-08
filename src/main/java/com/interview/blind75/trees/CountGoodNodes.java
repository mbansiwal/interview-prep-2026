package com.interview.blind75.trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ============================================================
 * PROBLEM : Count Good Nodes in Binary Tree
 * LINK    : https://leetcode.com/problems/count-good-nodes-in-binary-tree/
 * DIFFICULTY: Medium
 * PATTERN : Trees
 * ============================================================
 *
 * DESCRIPTION:
 * Given a binary tree root, a node X in the tree is named "good" if in
 * the path from root to X there are no nodes with a value greater than X.
 * Return the number of good nodes.
 *
 * EXAMPLES:
 *   Input : [3,1,4,3,null,1,5]
 *   Output: 4
 *
 *   Input : [3,3,null,4,2]
 *   Output: 3
 *
 * CONSTRAINTS:
 *   - 1 <= number of nodes <= 10^5
 *
 * ============================================================
 * APPROACHES  (n = nodes, h = height, w = max width)
 * ============================================================
 * APPROACH 1: Recursive DFS with Running Maximum
 * 1. DFS, passing the largest value seen on the path from the root.
 * 2. A node is good if node.val >= that max; count it.
 * 3. Recurse into both children with max(maxSoFar, node.val).
 * Intuition: "no ancestor is larger" is exactly "val >= max of the path so far".
 * TIME  : O(n)
 * SPACE : O(h) — recursion stack
 *
 * APPROACH 2: Iterative BFS Carrying the Path Max
 * 1. Queue holds (node, maxOnPathToNode) pairs, starting with (root, root.val).
 * 2. Pop a pair; if node.val >= max, count it.
 * 3. Push each child with max(max, node.val).
 * Intuition: same rule; the path max simply travels with each queued node.
 * TIME  : O(n)
 * SPACE : O(w) — the queue
 *
 * WHICH TO USE:
 * Recursive DFS is the expected answer. The iterative form avoids deep recursion
 * on skewed trees and shows you can carry per-path state without recursion.
 * ============================================================
 */
public class CountGoodNodes {

    /** Approach 1 — Recursive DFS with running max. TIME O(n) · SPACE O(h) recursion stack */
    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    private int dfs(TreeNode node, int maxSoFar) {
        if (node == null) return 0;
        int good = (node.val >= maxSoFar) ? 1 : 0;
        int newMax = Math.max(maxSoFar, node.val);
        return good + dfs(node.left, newMax) + dfs(node.right, newMax);
    }

    /** Approach 2 — Iterative BFS carrying the path max. TIME O(n) · SPACE O(w) queue */
    public int goodNodesBfs(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> nodes = new ArrayDeque<>();
        Deque<Integer> pathMax = new ArrayDeque<>();
        nodes.offer(root);
        pathMax.offer(root.val);
        int good = 0;
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            int max = pathMax.poll();
            if (node.val >= max) good++;
            int childMax = Math.max(max, node.val);
            if (node.left != null) { nodes.offer(node.left); pathMax.offer(childMax); }
            if (node.right != null) { nodes.offer(node.right); pathMax.offer(childMax); }
        }
        return good;
    }
}
