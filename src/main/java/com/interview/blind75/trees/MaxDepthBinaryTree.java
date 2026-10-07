package com.interview.blind75.trees;

/**
 * ============================================================
 * PROBLEM : Maximum Depth of Binary Tree
 * LINK    : https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * DIFFICULTY: Easy
 * PATTERN : Trees
 * ============================================================
 *
 * DESCRIPTION:
 * Given the root of a binary tree, return its maximum depth.
 * The maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 *
 * EXAMPLES:
 *   Input : [3,9,20,null,null,15,7]
 *   Output: 3
 *
 *   Input : [1,null,2]
 *   Output: 2
 *
 * CONSTRAINTS:
 *   - 0 <= number of nodes <= 10^4
 *
 * ============================================================
 * APPROACH: Recursive DFS
 * ============================================================
 * 1. Base case: null → depth 0.
 * 2. Recursively get left depth and right depth.
 * 3. Return 1 + max(leftDepth, rightDepth).
 *
 * WHY THIS WORKS:
 * Depth of a node = 1 (itself) + max depth of its subtrees.
 * The recursion naturally computes this bottom-up.
 *
 * TIME  : O(n)
 * SPACE : O(h)
 * ============================================================
 */
public class MaxDepthBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
