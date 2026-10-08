package com.interview.blind75.trees;

import java.util.*;

/**
 * ============================================================
 * PROBLEM : Binary Tree Level Order Traversal
 * LINK    : https://leetcode.com/problems/binary-tree-level-order-traversal/
 * DIFFICULTY: Medium
 * PATTERN : Trees (BFS)
 * ============================================================
 *
 * DESCRIPTION:
 * Given the root of a binary tree, return the level order traversal
 * of its nodes' values (i.e., from left to right, level by level).
 *
 * EXAMPLES:
 *   Input : [3,9,20,null,null,15,7]
 *   Output: [[3],[9,20],[15,7]]
 *
 *   Input : [1]
 *   Output: [[1]]
 *
 * CONSTRAINTS:
 *   - 0 <= number of nodes <= 2000
 *
 * ============================================================
 * APPROACHES  (n = nodes, h = height, w = max width)
 * ============================================================
 * APPROACH 1: BFS with Level-Size Control
 * 1. Start a queue with the root.
 * 2. Each round, record size = queue.size() — exactly the nodes on this level.
 * 3. Poll that many nodes, collect their values, enqueue their children.
 * Intuition: the queue size at the start of a round isolates one level.
 * TIME  : O(n)
 * SPACE : O(w) — the queue (plus O(n) for the output)
 *
 * APPROACH 2: DFS Carrying the Depth
 * 1. Preorder DFS (node, left, right), passing the current depth.
 * 2. If depth == result.size(), this is the first node on a new level → add a new list.
 * 3. Append node.val to result.get(depth).
 * Intuition: preorder visits each level's nodes left-to-right, so appending keeps order.
 * TIME  : O(n)
 * SPACE : O(h) — recursion stack (plus O(n) for the output)
 *
 * WHICH TO USE:
 * BFS is the natural, expected answer for "level order". DFS is equally optimal
 * and uses less memory on wide, shallow trees — a good alternative to mention.
 * ============================================================
 */
public class BinaryTreeLevelOrderTraversal {

    /** Approach 1 — BFS with level-size control. TIME O(n) · SPACE O(w) queue */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(level);
        }
        return result;
    }

    /** Approach 2 — DFS with depth. TIME O(n) · SPACE O(h) recursion stack */
    public List<List<Integer>> levelOrderDfs(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }

    private void dfs(TreeNode node, int depth, List<List<Integer>> result) {
        if (node == null) return;
        if (depth == result.size()) result.add(new ArrayList<>());
        result.get(depth).add(node.val);
        dfs(node.left, depth + 1, result);
        dfs(node.right, depth + 1, result);
    }
}
