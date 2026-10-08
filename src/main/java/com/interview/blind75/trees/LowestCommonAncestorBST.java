package com.interview.blind75.trees;

/**
 * ============================================================
 * PROBLEM : Lowest Common Ancestor of a Binary Search Tree
 * LINK    : https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * DIFFICULTY: Medium
 * PATTERN : Trees
 * ============================================================
 *
 * DESCRIPTION:
 * Given a BST and two nodes p and q, find their lowest common ancestor (LCA).
 * The LCA is the lowest node that has both p and q as descendants
 * (where a node can be a descendant of itself).
 *
 * EXAMPLES:
 *   Input : root=[6,2,8,0,4,7,9], p=2, q=8
 *   Output: 6
 *
 *   Input : root=[6,2,8,0,4,7,9], p=2, q=4
 *   Output: 2
 *
 * CONSTRAINTS:
 *   - 2 <= number of nodes <= 10^5
 *   - All values are unique
 *
 * ============================================================
 * APPROACHES  (h = height of the BST)
 * ============================================================
 * APPROACH 1: Iterative BST Navigation
 * 1. If both p and q are smaller than the current node, go left.
 * 2. If both are larger, go right.
 * 3. Otherwise they split here (or one equals the current node) → current is the LCA.
 * Intuition: BST order tells you which side both nodes are on; the split point is the LCA.
 * TIME  : O(h)
 * SPACE : O(1)
 *
 * APPROACH 2: Recursive BST Navigation
 * 1. Same three cases, but recurse into the left or right subtree instead of looping.
 * Intuition: identical logic; the recursion is tail-style but Java doesn't optimize it.
 * TIME  : O(h)
 * SPACE : O(h) — recursion stack
 *
 * WHICH TO USE:
 * Iterative is strictly better (O(1) space) and just as short. Note that the
 * general binary-tree LCA (LC 236) needs a full DFS, O(n) — don't use it here.
 * ============================================================
 */
public class LowestCommonAncestorBST {

    /** Approach 1 — Iterative BST navigation. TIME O(h) · SPACE O(1) */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;
        while (curr != null) {
            if (p.val < curr.val && q.val < curr.val) {
                curr = curr.left;
            } else if (p.val > curr.val && q.val > curr.val) {
                curr = curr.right;
            } else {
                return curr;
            }
        }
        return null;
    }

    /** Approach 2 — Recursive BST navigation. TIME O(h) · SPACE O(h) recursion stack */
    public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (p.val < root.val && q.val < root.val) return lowestCommonAncestorRecursive(root.left, p, q);
        if (p.val > root.val && q.val > root.val) return lowestCommonAncestorRecursive(root.right, p, q);
        return root;
    }
}
