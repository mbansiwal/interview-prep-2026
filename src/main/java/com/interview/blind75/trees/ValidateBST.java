package com.interview.blind75.trees;

/**
 * ============================================================
 * PROBLEM : Validate Binary Search Tree
 * LINK    : https://leetcode.com/problems/validate-binary-search-tree/
 * DIFFICULTY: Medium
 * PATTERN : Trees
 * ============================================================
 *
 * DESCRIPTION:
 * Given the root of a binary tree, determine if it is a valid
 * binary search tree (BST). A valid BST has all left subtree values
 * strictly less than the node and all right subtree values strictly greater.
 *
 * EXAMPLES:
 *   Input : [2,1,3]
 *   Output: true
 *
 *   Input : [5,1,4,null,null,3,6]
 *   Output: false (4 < 5 but right child)
 *
 * CONSTRAINTS:
 *   - 1 <= number of nodes <= 10^4
 *
 * ============================================================
 * APPROACH: DFS with Valid Range Bounds
 * ============================================================
 * 1. Pass min and max valid bounds down the tree.
 * 2. Left subtree: max bound = current node's value.
 * 3. Right subtree: min bound = current node's value.
 * 4. If node value violates bounds, return false.
 *
 * WHY THIS WORKS:
 * Just checking left < parent < right is insufficient for nested cases.
 * Tracking inherited bounds from all ancestors catches those violations.
 *
 * TIME  : O(n)
 * SPACE : O(h)
 * ============================================================
 */
public class ValidateBST {

    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }
}
