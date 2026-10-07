package com.interview.blind75.trees;

/**
 * ============================================================
 * PROBLEM : Subtree of Another Tree
 * LINK    : https://leetcode.com/problems/subtree-of-another-tree/
 * DIFFICULTY: Easy
 * PATTERN : Trees
 * ============================================================
 *
 * DESCRIPTION:
 * Given the roots of two binary trees root and subRoot, return true
 * if subRoot is a subtree of root. A subtree is a node in root along
 * with all of its descendants.
 *
 * EXAMPLES:
 *   Input : root=[3,4,5,1,2], subRoot=[4,1,2]
 *   Output: true
 *
 *   Input : root=[3,4,5,1,2,null,null,null,null,0], subRoot=[4,1,2]
 *   Output: false
 *
 * CONSTRAINTS:
 *   - 1 <= number of nodes <= 2000
 *
 * ============================================================
 * APPROACH: Recursive isSameTree at Every Node
 * ============================================================
 * 1. At each node of root, check if isSameTree(node, subRoot).
 * 2. If yes, return true. Otherwise recurse on left and right.
 *
 * WHY THIS WORKS:
 * A subtree rooted at any node must be structurally identical to subRoot.
 * We check this condition at every possible starting position.
 *
 * FOLLOW-UP (O(m+n)): serialize both trees in preorder with null markers
 * (e.g. ",2,4,#,#,5,#,#") and check if subRoot's string is a substring of
 * root's using KMP. The leading comma stops "2" from matching inside "12".
 *
 * TIME  : O(m*n) where m = |root|, n = |subRoot|
 * SPACE : O(m+n)
 * ============================================================
 */
public class SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        if (isSameTree(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
