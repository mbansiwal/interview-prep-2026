package com.interview.blind75.trees;

/**
 * ============================================================
 * PROBLEM : Binary Tree Maximum Path Sum
 * LINK    : https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * DIFFICULTY: Hard
 * PATTERN : Trees
 * ============================================================
 *
 * DESCRIPTION:
 * A path in a binary tree is a sequence of nodes where each pair of
 * adjacent nodes has an edge between them. Return the maximum path sum.
 * The path does not need to go through the root.
 *
 * EXAMPLES:
 *   Input : [1,2,3]
 *   Output: 6
 *
 *   Input : [-10,9,20,null,null,15,7]
 *   Output: 42
 *
 * CONSTRAINTS:
 *   - 1 <= number of nodes <= 3 * 10^4
 *   - -1000 <= Node.val <= 1000
 *
 * ============================================================
 * APPROACH: DFS with Max Path Through Node
 * ============================================================
 * 1. At each node, compute max contribution from left and right subtrees.
 *    Negative contributions are cut off at 0 (don't extend through them).
 * 2. Update global max: node.val + max(0, leftGain) + max(0, rightGain).
 * 3. Return node.val + max(0, max(leftGain, rightGain)) for parent's use.
 *    (Can only choose one direction to extend a path upward.)
 *
 * WHY THIS WORKS:
 * A path through any node connects its best left chain, itself, and its
 * best right chain. We compute this locally and propagate the best single
 * direction upward.
 *
 * TIME  : O(n)
 * SPACE : O(h)
 * ============================================================
 */
public class BinaryTreeMaxPathSum {

    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        gainFrom(root);
        return maxSum;
    }

    private int gainFrom(TreeNode node) {
        if (node == null) return 0;
        int leftGain  = Math.max(0, gainFrom(node.left));
        int rightGain = Math.max(0, gainFrom(node.right));
        maxSum = Math.max(maxSum, node.val + leftGain + rightGain);
        return node.val + Math.max(leftGain, rightGain);
    }
}
