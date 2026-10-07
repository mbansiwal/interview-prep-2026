package com.interview.blind75.trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ============================================================
 * PROBLEM : Kth Smallest Element in a BST
 * LINK    : https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * DIFFICULTY: Medium
 * PATTERN : Trees
 * ============================================================
 *
 * DESCRIPTION:
 * Given the root of a binary search tree and an integer k, return the
 * kth smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 * EXAMPLES:
 *   Input : root = [3,1,4,null,2], k = 1
 *   Output: 1
 *
 *   Input : root = [5,3,6,2,4,null,null,1], k = 3
 *   Output: 3
 *
 * CONSTRAINTS:
 *   - 1 <= k <= number of nodes <= 10^4
 *
 * ============================================================
 * APPROACH: Iterative Inorder Traversal (Sorted Order)
 * ============================================================
 * 1. Push nodes while walking left — the leftmost node is the smallest.
 * 2. Pop a node: that is the next smallest value. Decrement k.
 * 3. If k hits 0, return it immediately. Otherwise move to its right child.
 *
 * WHY THIS WORKS:
 * BST inorder (left → node → right) visits values in ascending order, so the
 * k-th popped node is the k-th smallest. The iterative stack lets us return the
 * moment we find it — a recursive version keeps unwinding through the right
 * subtrees unless every level checks for "already found".
 *
 * TIME  : O(h + k)
 * SPACE : O(h)
 * ============================================================
 */
public class KthSmallestInBST {

    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (--k == 0) return curr.val;
            curr = curr.right;
        }
        throw new IllegalArgumentException("k is larger than the number of nodes");
    }
}
