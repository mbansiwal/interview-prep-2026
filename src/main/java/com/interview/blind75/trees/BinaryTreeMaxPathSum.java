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
 * APPROACHES  (n = nodes, h = height)
 * ============================================================
 * APPROACH 1: DFS Returning the Best Downward "Gain"
 * 1. For each node, get the best gain from the left and right subtrees, clamped at 0
 *    (a negative branch is better left out).
 * 2. Best path that peaks at this node = node.val + leftGain + rightGain; update the global max.
 * 3. Return node.val + max(leftGain, rightGain) — a path going up can use only one side.
 * Intuition: every path has one highest node; there it may use both sides, everywhere else only one.
 * TIME  : O(n)
 * SPACE : O(h) — recursion stack
 *
 * ALTERNATIVES: brute force (try every node as the peak and recompute gains) is
 * O(n²) — no materially better alternative to Approach 1.
 * ============================================================
 */
public class BinaryTreeMaxPathSum {

    private int maxSum = Integer.MIN_VALUE;

    /** Approach 1 — DFS with clamped gains and a global max. TIME O(n) · SPACE O(h) recursion stack */
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
