package com.interview.blind75.trees;

/**
 * ============================================================
 * PROBLEM : Same Tree
 * LINK    : https://leetcode.com/problems/same-tree/
 * DIFFICULTY: Easy
 * PATTERN : Trees
 * ============================================================
 *
 * DESCRIPTION:
 * Given the roots of two binary trees p and q, write a function to
 * check if they are the same or not. Two trees are the same if they
 * are structurally identical and nodes have the same value.
 *
 * EXAMPLES:
 *   Input : p = [1,2,3], q = [1,2,3]
 *   Output: true
 *
 *   Input : p = [1,2], q = [1,null,2]
 *   Output: false
 *
 * CONSTRAINTS:
 *   - 0 <= number of nodes <= 100
 *
 * ============================================================
 * APPROACH: Recursive Structural Comparison
 * ============================================================
 * 1. If both null, return true.
 * 2. If exactly one is null, return false.
 * 3. If values differ, return false.
 * 4. Recursively check left subtrees and right subtrees.
 *
 * WHY THIS WORKS:
 * Trees are identical iff every corresponding node pair matches.
 * Recursion checks all pairs simultaneously by structural descent.
 *
 * TIME  : O(n)
 * SPACE : O(h)
 * ============================================================
 */
public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
