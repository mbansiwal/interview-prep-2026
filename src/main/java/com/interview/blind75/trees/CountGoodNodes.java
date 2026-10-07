package com.interview.blind75.trees;

/**
 * ============================================================
 * PROBLEM : Count Good Nodes in Binary Tree
 * LINK    : https://leetcode.com/problems/count-good-nodes-in-binary-tree/
 * DIFFICULTY: Medium
 * PATTERN : Trees
 * ============================================================
 *
 * DESCRIPTION:
 * Given a binary tree root, a node X in the tree is named "good" if in
 * the path from root to X there are no nodes with a value greater than X.
 * Return the number of good nodes.
 *
 * EXAMPLES:
 *   Input : [3,1,4,3,null,1,5]
 *   Output: 4
 *
 *   Input : [3,3,null,4,2]
 *   Output: 3
 *
 * CONSTRAINTS:
 *   - 1 <= number of nodes <= 10^5
 *
 * ============================================================
 * APPROACH: DFS with Running Maximum
 * ============================================================
 * 1. DFS passing the maximum value seen so far on the path.
 * 2. If current node's value >= maxSoFar, it is "good" — count it.
 * 3. Recurse with updated max into left and right subtrees.
 *
 * WHY THIS WORKS:
 * A node is good iff no ancestor has a higher value, which is captured
 * by comparing with the running max from root to current node.
 *
 * TIME  : O(n)
 * SPACE : O(h)
 * ============================================================
 */
public class CountGoodNodes {

    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    private int dfs(TreeNode node, int maxSoFar) {
        if (node == null) return 0;
        int good = (node.val >= maxSoFar) ? 1 : 0;
        int newMax = Math.max(maxSoFar, node.val);
        return good + dfs(node.left, newMax) + dfs(node.right, newMax);
    }
}
