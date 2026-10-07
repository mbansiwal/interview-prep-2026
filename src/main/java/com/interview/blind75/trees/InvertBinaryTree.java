package com.interview.blind75.trees;

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
 * APPROACH: Recursive Swap
 * ============================================================
 * 1. Base case: null node returns null.
 * 2. Recursively invert left and right subtrees.
 * 3. Swap root.left and root.right.
 * 4. Return root.
 *
 * WHY THIS WORKS:
 * Inversion is defined recursively: an inverted tree has its left/right
 * subtrees swapped, with each subtree also inverted.
 *
 * TIME  : O(n)
 * SPACE : O(h) — recursion stack depth
 * ============================================================
 */
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
