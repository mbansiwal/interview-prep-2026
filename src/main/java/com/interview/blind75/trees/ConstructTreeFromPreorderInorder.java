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
 * APPROACH: Recursive with Inorder Index Map
 * ============================================================
 * 1. preorder[0] is always the root.
 * 2. Find root in inorder — elements left are left subtree, right are right subtree.
 * 3. Recursively build left subtree (inorder left portion) and right (inorder right).
 * 4. Use a HashMap for O(1) inorder index lookup.
 *
 * WHY THIS WORKS:
 * Preorder gives us the root at each recursive level. Inorder tells us
 * the exact split between left and right subtrees.
 *
 * TIME  : O(n)
 * SPACE : O(n)
 * ============================================================
 */
public class ConstructTreeFromPreorderInorder {

    private int preIndex = 0;
    private Map<Integer, Integer> inorderIndex;

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
}
