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
 * APPROACH: BST Property Navigation
 * ============================================================
 * 1. If both p and q < current node, LCA is in left subtree.
 * 2. If both p and q > current node, LCA is in right subtree.
 * 3. Otherwise, current node is the LCA (p and q split here, or one is current).
 *
 * WHY THIS WORKS:
 * BST ordering means if both nodes are on the same side, we go that way.
 * When they split (or one equals current), the split point is the LCA.
 *
 * TIME  : O(h)
 * SPACE : O(1)
 * ============================================================
 */
public class LowestCommonAncestorBST {

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
}
