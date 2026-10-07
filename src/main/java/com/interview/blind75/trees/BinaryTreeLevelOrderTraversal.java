package com.interview.blind75.trees;

import java.util.*;

/**
 * ============================================================
 * PROBLEM : Binary Tree Level Order Traversal
 * LINK    : https://leetcode.com/problems/binary-tree-level-order-traversal/
 * DIFFICULTY: Medium
 * PATTERN : Trees (BFS)
 * ============================================================
 *
 * DESCRIPTION:
 * Given the root of a binary tree, return the level order traversal
 * of its nodes' values (i.e., from left to right, level by level).
 *
 * EXAMPLES:
 *   Input : [3,9,20,null,null,15,7]
 *   Output: [[3],[9,20],[15,7]]
 *
 *   Input : [1]
 *   Output: [[1]]
 *
 * CONSTRAINTS:
 *   - 0 <= number of nodes <= 2000
 *
 * ============================================================
 * APPROACH: BFS with Level-Size Control
 * ============================================================
 * 1. Use a queue, initially containing root.
 * 2. Each iteration processes one complete level:
 *    - Record current queue size (= number of nodes at this level).
 *    - Poll exactly that many nodes, collect values, enqueue children.
 *
 * WHY THIS WORKS:
 * Queue size at the start of each iteration equals the number of nodes
 * in that level — processing exactly that many isolates each level.
 *
 * TIME  : O(n)
 * SPACE : O(n)
 * ============================================================
 */
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(level);
        }
        return result;
    }
}
