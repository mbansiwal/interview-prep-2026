package com.interview.blind75.trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ============================================================
 * PROBLEM : Invert Binary Tree
 * LINK    : https://leetcode.com/problems/invert-binary-tree/
 * DIFFICULTY: Easy
 * PATTERN : Trees
 * ============================================================
 *
 * DESCRIPTION:
 * Given the root of a binary tree, invert the tree (mirror it),
 * and return its root.
 *
 * EXAMPLES:
 *   Input :     4           Output:     4
 *              / \                     / \
 *             2   7                   7   2
 *            / \ / \                 / \ / \
 *           1  3 6  9               9  6 3  1
 *
 * CONSTRAINTS:
 *   - 0 <= number of nodes <= 100
 *
 * ============================================================
 * APPROACHES  (n = nodes, h = height, w = max width)
 * ============================================================
 * APPROACH 1: Recursive DFS Swap
 * 1. Base case: null returns null.
 * 2. Recursively invert the left and right subtrees.
 * 3. Swap root.left and root.right, return root.
 * Intuition: an inverted tree = swapped children, each of them also inverted.
 * TIME  : O(n)
 * SPACE : O(h) — recursion stack (O(n) for a skewed tree)
 *
 * APPROACH 2: Iterative BFS with a Queue
 * 1. Push the root into a queue.
 * 2. Pop a node, swap its children, push the non-null children.
 * 3. Repeat until the queue is empty.
 * Intuition: every node just needs its two children swapped once — any visit order works.
 * TIME  : O(n)
 * SPACE : O(w) — the queue holds at most one level (up to ~n/2 for a full tree)
 *
 * WHICH TO USE:
 * Recursion is the 3-line expected answer. The iterative version avoids
 * stack overflow on very deep (skewed) trees — a good follow-up to mention.
 * ============================================================
 */
public class InvertBinaryTree {

    /** Approach 1 — Recursive DFS swap. TIME O(n) · SPACE O(h) recursion stack · mutates input */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /** Approach 2 — Iterative BFS. TIME O(n) · SPACE O(w) queue · mutates input */
    public TreeNode invertTreeIterative(TreeNode root) {
        if (root == null) return null;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return root;
    }
}
