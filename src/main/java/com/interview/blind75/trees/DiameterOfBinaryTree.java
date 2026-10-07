package com.interview.blind75.trees;

/**
 * ============================================================
 * PROBLEM : Diameter of Binary Tree
 * LINK    : https://leetcode.com/problems/diameter-of-binary-tree/
 * DIFFICULTY: Easy
 * PATTERN : Trees
 * ============================================================
 *
 * DESCRIPTION:
 * Given the root of a binary tree, return the length of the diameter.
 * The diameter is the length of the longest path between any two nodes.
 * The path may or may not pass through the root.
 *
 * EXAMPLES:
 *   Input : [1,2,3,4,5]
 *   Output: 3 (path: 4→2→1→3 or 5→2→1→3)
 *
 *   Input : [1,2]
 *   Output: 1
 *
 * CONSTRAINTS:
 *   - 1 <= number of nodes <= 10^4
 *
 * ============================================================
 * APPROACH: DFS Height with Global Max
 * ============================================================
 * 1. Define a DFS function that returns the height of a subtree.
 * 2. At each node: diameter candidate = leftHeight + rightHeight.
 * 3. Update the global max diameter.
 * 4. Return height = 1 + max(leftHeight, rightHeight).
 *
 * WHY THIS WORKS:
 * The diameter through any node equals the sum of heights of its
 * two subtrees. We compute this at every node and take the maximum.
 *
 * TIME  : O(n)
 * SPACE : O(h)
 * ============================================================
 */
public class DiameterOfBinaryTree {

    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        height(root);
        return maxDiameter;
    }

    private int height(TreeNode node) {
        if (node == null) return 0;
        int leftH = height(node.left);
        int rightH = height(node.right);
        maxDiameter = Math.max(maxDiameter, leftH + rightH);
        return 1 + Math.max(leftH, rightH);
    }
}
