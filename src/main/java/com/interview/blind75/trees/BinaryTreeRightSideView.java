package com.interview.blind75.trees;

import java.util.*;

/**
 * ============================================================
 * PROBLEM : Binary Tree Right Side View
 * LINK    : https://leetcode.com/problems/binary-tree-right-side-view/
 * DIFFICULTY: Medium
 * PATTERN : Trees (BFS)
 * ============================================================
 *
 * DESCRIPTION:
 * Given the root of a binary tree, imagine yourself standing on the
 * right side of it, return the values of the nodes you can see ordered
 * from top to bottom.
 *
 * EXAMPLES:
 *   Input : [1,2,3,null,5,null,4]
 *   Output: [1,3,4]
 *
 *   Input : [1,null,3]
 *   Output: [1,3]
 *
 * CONSTRAINTS:
 *   - 0 <= number of nodes <= 100
 *
 * ============================================================
 * APPROACHES  (n = nodes, h = height, w = max width)
 * ============================================================
 * APPROACH 1: BFS, Last Node per Level
 * 1. Level-order traversal with a queue.
 * 2. Within each level, the last node polled is the rightmost one.
 * 3. Add its value to the result.
 * Intuition: BFS goes left-to-right per level, so the last node is what you see from the right.
 * TIME  : O(n)
 * SPACE : O(w) — the queue
 *
 * APPROACH 2: DFS, Right Child First
 * 1. DFS visiting node, then right, then left, passing the depth.
 * 2. The first node reached at each depth is the rightmost → add it when depth == result.size().
 * Intuition: going right first guarantees the first visit to a level is its rightmost node.
 * TIME  : O(n)
 * SPACE : O(h) — recursion stack
 *
 * WHICH TO USE:
 * Both are optimal. BFS is the most common answer; DFS is shorter and uses less
 * memory on wide trees (O(h) vs O(w)).
 * ============================================================
 */
public class BinaryTreeRightSideView {

    /** Approach 1 — BFS, last node per level. TIME O(n) · SPACE O(w) queue */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) result.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return result;
    }

    /** Approach 2 — DFS, right child first. TIME O(n) · SPACE O(h) recursion stack */
    public List<Integer> rightSideViewDfs(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }

    private void dfs(TreeNode node, int depth, List<Integer> result) {
        if (node == null) return;
        if (depth == result.size()) result.add(node.val);
        dfs(node.right, depth + 1, result);
        dfs(node.left, depth + 1, result);
    }
}
