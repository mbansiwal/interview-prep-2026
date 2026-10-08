package com.interview.blind75.trees;

/**
 * ============================================================
 * PROBLEM : Balanced Binary Tree
 * LINK    : https://leetcode.com/problems/balanced-binary-tree/
 * DIFFICULTY: Easy
 * PATTERN : Trees
 * ============================================================
 *
 * DESCRIPTION:
 * Given a binary tree, determine if it is height-balanced.
 * A tree is balanced if every node has left/right subtrees whose
 * heights differ by no more than 1.
 *
 * EXAMPLES:
 *   Input : [3,9,20,null,null,15,7]
 *   Output: true
 *
 *   Input : [1,2,2,3,3,null,null,4,4]
 *   Output: false
 *
 * CONSTRAINTS:
 *   - 0 <= number of nodes <= 5000
 *
 * ============================================================
 * APPROACHES  (n = nodes, h = height)
 * ============================================================
 * APPROACH 1: Bottom-Up DFS Height with -1 Sentinel
 * 1. DFS returns the subtree height, or -1 if any subtree is unbalanced.
 * 2. If |leftHeight - rightHeight| > 1, return -1 and stop exploring.
 * 3. Otherwise return 1 + max(left, right).
 * Intuition: compute heights once, bottom-up, and short-circuit on the first imbalance.
 * TIME  : O(n)
 * SPACE : O(h) — recursion stack
 *
 * ALTERNATIVES: top-down (call height() at every node, then recurse) is
 * O(n log n) for balanced trees and O(n²) for skewed ones because heights are
 * recomputed — no materially better alternative to Approach 1.
 * ============================================================
 */
public class BalancedBinaryTree {

    /** Approach 1 — Bottom-up height with early exit. TIME O(n) · SPACE O(h) recursion stack */
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode node) {
        if (node == null) return 0;
        int leftH = checkHeight(node.left);
        if (leftH == -1) return -1;
        int rightH = checkHeight(node.right);
        if (rightH == -1) return -1;
        if (Math.abs(leftH - rightH) > 1) return -1;
        return 1 + Math.max(leftH, rightH);
    }
}
