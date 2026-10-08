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
 * APPROACHES  (n = nodes, h = height)
 * ============================================================
 * APPROACH 1: Iterative Inorder Traversal with a Stack
 * 1. Push nodes while walking left — the leftmost node is the smallest.
 * 2. Pop a node: it's the next smallest value; decrement k.
 * 3. If k hits 0, return it immediately; otherwise move to its right child.
 * Intuition: BST inorder visits values in ascending order, so the k-th pop is the answer.
 * TIME  : O(h + k) — stops as soon as the k-th value is found
 * SPACE : O(h) — the stack
 *
 * APPROACH 2: Morris Inorder Traversal (no stack, no recursion)
 * 1. If the current node has no left child, visit it and go right.
 * 2. Otherwise find its inorder predecessor (rightmost node of the left subtree).
 *    - No thread yet: point predecessor.right at current (a temporary "thread"), go left.
 *    - Thread exists: remove it, visit current, go right.
 * 3. Record the k-th visited value, but finish the walk so every thread is removed.
 * Intuition: threads replace the stack — each one remembers where to return to.
 * TIME  : O(n) — every edge is walked a constant number of times; full walk to restore the tree
 * SPACE : O(1) — temporarily modifies right pointers, fully restored before returning
 *
 * WHICH TO USE:
 * Approach 1 is the standard answer (and best when k is small). Morris is the
 * answer to "can you do it in O(1) extra space?". Follow-up (frequent inserts/
 * deletes): store subtree sizes in each node to answer in O(h) per query.
 * ============================================================
 */
public class KthSmallestInBST {

    /** Approach 1 — Iterative inorder with a stack. TIME O(h+k) · SPACE O(h) */
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

    /** Approach 2 — Morris inorder traversal. TIME O(n) · SPACE O(1) · tree temporarily threaded, then restored */
    public int kthSmallestMorris(TreeNode root, int k) {
        int count = 0;
        Integer answer = null;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                if (++count == k) answer = curr.val;
                curr = curr.right;
            } else {
                TreeNode pred = curr.left;
                while (pred.right != null && pred.right != curr) pred = pred.right;
                if (pred.right == null) {
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    pred.right = null;
                    if (++count == k) answer = curr.val;
                    curr = curr.right;
                }
            }
        }
        if (answer == null) throw new IllegalArgumentException("k is larger than the number of nodes");
        return answer;
    }
}
