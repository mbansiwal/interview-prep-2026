package com.interview.blind75.trees;

import java.util.ArrayDeque;
import java.util.Deque;

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
 * APPROACHES  (n = nodes in the smaller tree, h = height)
 * ============================================================
 * APPROACH 1: Recursive Structural Comparison
 * 1. Both null → true; exactly one null → false; values differ → false.
 * 2. Otherwise both the left pair and the right pair must be the same tree.
 * Intuition: two trees are identical iff every corresponding node pair matches.
 * TIME  : O(n)
 * SPACE : O(h) — recursion stack
 *
 * APPROACH 2: Iterative, Comparing Node Pairs from a Stack
 * 1. Push the pair (p, q) onto a stack.
 * 2. Pop a pair; apply the same null/value checks.
 * 3. Push (p.left, q.left) and (p.right, q.right); repeat until the stack is empty.
 * Intuition: the recursion's implicit call stack, made explicit.
 * TIME  : O(n)
 * SPACE : O(h) — explicit stack
 *
 * WHICH TO USE:
 * The recursive version is the expected answer. The iterative one shows you
 * can convert recursion to an explicit stack, and avoids deep-recursion limits.
 * ============================================================
 */
public class SameTree {

    /** Approach 1 — Recursive comparison. TIME O(n) · SPACE O(h) recursion stack */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /** Approach 2 — Iterative with an explicit stack of node pairs. TIME O(n) · SPACE O(h) */
    public boolean isSameTreeIterative(TreeNode p, TreeNode q) {
        Deque<TreeNode[]> stack = new ArrayDeque<>();
        stack.push(new TreeNode[]{p, q});
        while (!stack.isEmpty()) {
            TreeNode[] pair = stack.pop();
            TreeNode a = pair[0], b = pair[1];
            if (a == null && b == null) continue;
            if (a == null || b == null || a.val != b.val) return false;
            stack.push(new TreeNode[]{a.left, b.left});
            stack.push(new TreeNode[]{a.right, b.right});
        }
        return true;
    }
}
