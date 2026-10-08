package com.interview.blind75.trees;

import java.util.ArrayDeque;
import java.util.Deque;

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
 * APPROACHES  (n = nodes, h = height)
 * ============================================================
 * APPROACH 1: DFS with Valid Range Bounds
 * 1. Pass down the open interval (min, max) each node must fall in.
 * 2. Going left, max becomes the parent's value; going right, min does.
 * 3. Any node outside its interval → not a BST. Use long bounds so Integer.MIN/MAX work.
 * Intuition: "left < parent < right" isn't enough — every ancestor constrains a node.
 * TIME  : O(n)
 * SPACE : O(h) — recursion stack
 *
 * APPROACH 2: Iterative Inorder Traversal with a "prev" Value
 * 1. Do an inorder walk (left, node, right) with an explicit stack.
 * 2. Keep the previously visited value; each new value must be strictly greater.
 * 3. Any value <= prev → not a BST.
 * Intuition: inorder traversal of a valid BST is strictly increasing.
 * TIME  : O(n) — and it can stop at the first violation
 * SPACE : O(h) — explicit stack
 *
 * WHICH TO USE:
 * Both are optimal and well known. Range bounds is the most common answer; the
 * inorder version reuses a pattern you'll need anyway (e.g. Kth Smallest in BST).
 * ============================================================
 */
public class ValidateBST {

    /** Approach 1 — DFS with range bounds. TIME O(n) · SPACE O(h) recursion stack */
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    /** Approach 2 — Iterative inorder, strictly increasing check. TIME O(n) · SPACE O(h) stack */
    public boolean isValidBSTInorder(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        Integer prev = null;
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (prev != null && curr.val <= prev) return false;
            prev = curr.val;
            curr = curr.right;
        }
        return true;
    }
}
