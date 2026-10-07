package com.interview.blind75.trees;

import java.util.*;

/**
 * ============================================================
 * PROBLEM : Binary Tree Right Side View
 * LINK    : https://leetcode.com/problems/binary-tree-right-side-view/
 * DIFFICULTY: Medium
 * PATTERN : Trees (BFS)
 * ============================================================
 *
 * DESCRIPTION:
 * Given the root of a binary tree, imagine yourself standing on the
 * right side of it, return the values of the nodes you can see ordered
 * from top to bottom.
 *
 * EXAMPLES:
 *   Input : [1,2,3,null,5,null,4]
 *   Output: [1,3,4]
 *
 *   Input : [1,null,3]
 *   Output: [1,3]
 *
 * CONSTRAINTS:
 *   - 0 <= number of nodes <= 100
 *
 * ============================================================
 * APPROACH: BFS, Last Node Per Level
 * ============================================================
 * 1. Level-order traversal using a queue.
 * 2. For each level, the last node polled is the rightmost visible node.
 * 3. Add that node's value to result.
 *
 * WHY THIS WORKS:
 * At each level, only the rightmost node is visible from the right side.
 * BFS naturally processes nodes left-to-right within each level.
 *
 * TIME  : O(n)
 * SPACE : O(n)
 * ============================================================
 */
public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) result.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return result;
    }
}
