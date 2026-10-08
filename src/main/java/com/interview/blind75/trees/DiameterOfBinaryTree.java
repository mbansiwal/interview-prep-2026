package com.interview.blind75.trees;

/**
 * ============================================================
 * PROBLEM : Diameter of Binary Tree
 * LINK    : https://leetcode.com/problems/diameter-of-binary-tree/
 * DIFFICULTY: Easy
 * PATTERN : Trees
 * ============================================================
 *
 * DESCRIPTION:
 * Given the root of a binary tree, return the length of the diameter.
 * The diameter is the length of the longest path between any two nodes.
 * The path may or may not pass through the root.
 *
 * EXAMPLES:
 *   Input : [1,2,3,4,5]
 *   Output: 3 (path: 4→2→1→3 or 5→2→1→3)
 *
 *   Input : [1,2]
 *   Output: 1
 *
 * CONSTRAINTS:
 *   - 1 <= number of nodes <= 10^4
 *
 * ============================================================
 * APPROACHES  (n = nodes, h = height)
 * ============================================================
 * APPROACH 1: DFS Height with a Running Max
 * 1. DFS returns the height of each subtree.
 * 2. At each node, the longest path through it = leftHeight + rightHeight edges.
 * 3. Track the maximum of that over all nodes; return 1 + max(left, right) upward.
 * Intuition: every longest path has a highest node; there it uses both subtree heights.
 * TIME  : O(n)
 * SPACE : O(h) — recursion stack
 *
 * ALTERNATIVES: brute force (compute heights separately at every node) is O(n²)
 * on skewed trees — no materially better alternative to Approach 1.
 * ============================================================
 */
public class DiameterOfBinaryTree {

    private int maxDiameter = 0;

    /** Approach 1 — DFS height with running max. TIME O(n) · SPACE O(h) recursion stack */
    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        height(root);
        return maxDiameter;
    }

    private int height(TreeNode node) {
        if (node == null) return 0;
        int leftH = height(node.left);
        int rightH = height(node.right);
        maxDiameter = Math.max(maxDiameter, leftH + rightH);
        return 1 + Math.max(leftH, rightH);
    }
}
