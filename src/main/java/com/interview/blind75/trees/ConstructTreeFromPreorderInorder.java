package com.interview.blind75.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * PROBLEM : Construct Binary Tree from Preorder and Inorder Traversal
 * LINK    : https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * DIFFICULTY: Medium
 * PATTERN : Trees
 * ============================================================
 *
 * DESCRIPTION:
 * Given two integer arrays preorder and inorder where preorder is the
 * preorder traversal and inorder is the inorder traversal of the same tree,
 * construct and return the binary tree.
 *
 * EXAMPLES:
 *   Input : preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 *   Output: [3,9,20,null,null,15,7]
 *
 * CONSTRAINTS:
 *   - 1 <= n <= 3000
 *   - All values are unique
 *
 * ============================================================
 * APPROACHES  (n = nodes, h = height of the built tree)
 * ============================================================
 * APPROACH 1: Recursion with an Inorder Index HashMap
 * 1. preorder[preIndex] is always the root of the current subtree.
 * 2. Look up the root's position in inorder (HashMap, O(1)): left part = left subtree, right part = right subtree.
 * 3. Recursively build left, then right (preorder visits left before right).
 * Intuition: preorder says WHO the root is; inorder says WHERE the split is.
 * TIME  : O(n)
 * SPACE : O(n) — the HashMap (plus O(h) recursion stack)
 *
 * APPROACH 2: Recursion with a "Stop" Value (no HashMap)
 * 1. Keep two pointers: preIndex into preorder, inIndex into inorder.
 * 2. build(stop): if inorder[inIndex] == stop, this subtree is empty → return null.
 * 3. Otherwise create root = preorder[preIndex++]; root.left = build(root.val);
 *    then skip root in inorder (inIndex++); root.right = build(stop).
 * Intuition: a left subtree ends exactly when inorder reaches its parent's value,
 * so the parent's value acts as the boundary — no index lookups needed.
 * TIME  : O(n)
 * SPACE : O(h) — recursion stack only
 *
 * WHICH TO USE:
 * The HashMap version is the standard, easiest-to-explain answer. The stop-value
 * version is a neat follow-up that drops the O(n) map. Both need unique values.
 * ============================================================
 */
public class ConstructTreeFromPreorderInorder {

    private int preIndex = 0;
    private Map<Integer, Integer> inorderIndex;

    /** Approach 1 — Recursion + inorder index map. TIME O(n) · SPACE O(n) */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preIndex = 0;
        inorderIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inorderIndex.put(inorder[i], i);
        return build(preorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int inLeft, int inRight) {
        if (inLeft > inRight) return null;
        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);
        int mid = inorderIndex.get(rootVal);
        root.left = build(preorder, inLeft, mid - 1);
        root.right = build(preorder, mid + 1, inRight);
        return root;
    }

    private int pre, in;

    /** Approach 2 — Recursion with a stop value, no map. TIME O(n) · SPACE O(h) recursion stack */
    public TreeNode buildTreeNoMap(int[] preorder, int[] inorder) {
        pre = 0;
        in = 0;
        return buildUntil(preorder, inorder, null);
    }

    private TreeNode buildUntil(int[] preorder, int[] inorder, Integer stop) {
        if (pre == preorder.length || (stop != null && inorder[in] == stop)) return null;
        TreeNode root = new TreeNode(preorder[pre++]);
        root.left = buildUntil(preorder, inorder, root.val);
        in++;
        root.right = buildUntil(preorder, inorder, stop);
        return root;
    }
}
