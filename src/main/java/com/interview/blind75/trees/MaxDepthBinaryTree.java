package com.interview.blind75.trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ============================================================
 * PROBLEM : Maximum Depth of Binary Tree
 * LINK    : https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * DIFFICULTY: Easy
 * PATTERN : Trees
 * ============================================================
 *
 * DESCRIPTION:
 * Given the root of a binary tree, return its maximum depth.
 * The maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 *
 * EXAMPLES:
 *   Input : [3,9,20,null,null,15,7]
 *   Output: 3
 *
 *   Input : [1,null,2]
 *   Output: 2
 *
 * CONSTRAINTS:
 *   - 0 <= number of nodes <= 10^4
 *
 * ============================================================
 * APPROACHES  (n = nodes, h = height, w = max width)
 * ============================================================
 * APPROACH 1: Recursive DFS
 * 1. Base case: null → depth 0.
 * 2. Return 1 + max(depth(left), depth(right)).
 * Intuition: a node's depth is itself plus its deeper subtree.
 * TIME  : O(n)
 * SPACE : O(h) — recursion stack
 *
 * APPROACH 2: BFS Level Counting
 * 1. Push the root into a queue; depth = 0.
 * 2. For each level, pop exactly queue.size() nodes and push their children; depth++.
 * 3. When the queue empties, depth is the number of levels.
 * Intuition: max depth = number of levels, and BFS processes one level at a time.
 * TIME  : O(n)
 * SPACE : O(w) — the queue holds one level
 *
 * WHICH TO USE:
 * Recursive DFS is the expected one-liner. BFS is equally optimal, has no
 * recursion-depth risk, and is better for wide-but-shallow trees.
 * ============================================================
 */
public class MaxDepthBinaryTree {

    /** Approach 1 — Recursive DFS. TIME O(n) · SPACE O(h) recursion stack */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /** Approach 2 — BFS level counting. TIME O(n) · SPACE O(w) queue */
    public int maxDepthBfs(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            depth++;
        }
        return depth;
    }
}
