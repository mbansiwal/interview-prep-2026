package com.interview.blind75.trees;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.LinkedList;

/**
 * ============================================================
 * PROBLEM : Serialize and Deserialize Binary Tree
 * LINK    : https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * DIFFICULTY: Hard
 * PATTERN : Trees
 * ============================================================
 *
 * DESCRIPTION:
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how serialization/deserialization format.
 *
 * EXAMPLES:
 *   Input : root = [1,2,3,null,null,4,5]
 *   Output: serialize then deserialize gives the same tree
 *
 * CONSTRAINTS:
 *   - 0 <= number of nodes <= 10^4
 *
 * ============================================================
 * APPROACHES  (n = nodes, h = height, w = max width)
 * ============================================================
 * APPROACH 1: Preorder DFS with "N" for Null
 * Serialize: preorder DFS, writing each value or "N" for null, comma-separated.
 * Deserialize: split into a token queue; consume recursively — "N" → null, else
 * create the node, then build its left and right from the following tokens.
 * Intuition: preorder + explicit nulls encodes the shape exactly; tokens are read
 * back in the same order they were written.
 * TIME  : O(n) both
 * SPACE : O(n) for the string + O(h) recursion stack
 *
 * APPROACH 2: BFS Level Order (LeetCode's own format)
 * Serialize: BFS from the root; write each node's value or "N", enqueueing children of non-null nodes.
 * Deserialize: the first token is the root; then, for each node taken from a queue,
 * the next two tokens are its left and right children.
 * Intuition: level order lists every node's children right after the earlier nodes,
 * so a queue rebuilds parent → child links in the same order.
 * TIME  : O(n) both
 * SPACE : O(n) for the string + O(w) queue — no recursion, safe for very deep trees
 *
 * WHICH TO USE:
 * Preorder DFS is the shortest to write and explain. BFS matches LeetCode's
 * "[1,2,3,null,null,4,5]" format and avoids recursion depth limits on skewed trees.
 * ============================================================
 */
public class SerializeDeserializeTree {

    /** Approach 1 — Preorder DFS serialize. TIME O(n) · SPACE O(n) string + O(h) recursion */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeDFS(root, sb);
        return sb.toString();
    }

    private void serializeDFS(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("N,");
            return;
        }
        sb.append(node.val).append(",");
        serializeDFS(node.left, sb);
        serializeDFS(node.right, sb);
    }

    /** Approach 1 — Preorder DFS deserialize. TIME O(n) · SPACE O(n) tokens + O(h) recursion */
    public TreeNode deserialize(String data) {
        Queue<String> tokens = new ArrayDeque<>();
        for (String token : data.split(",")) tokens.offer(token);
        return deserializeDFS(tokens);
    }

    private TreeNode deserializeDFS(Queue<String> tokens) {
        String token = tokens.poll();
        if ("N".equals(token)) return null;
        TreeNode node = new TreeNode(Integer.parseInt(token));
        node.left = deserializeDFS(tokens);
        node.right = deserializeDFS(tokens);
        return node;
    }

    /** Approach 2 — BFS level-order serialize. TIME O(n) · SPACE O(n) string + O(w) queue */
    public String serializeBfs(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("N,");
                continue;
            }
            sb.append(node.val).append(",");
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return sb.toString();
    }

    /** Approach 2 — BFS level-order deserialize. TIME O(n) · SPACE O(n) tokens + O(w) queue */
    public TreeNode deserializeBfs(String data) {
        String[] tokens = data.split(",");
        if (tokens[0].equals("N")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));
        Queue<TreeNode> parents = new ArrayDeque<>();
        parents.offer(root);
        int i = 1;
        while (!parents.isEmpty()) {
            TreeNode parent = parents.poll();
            if (!tokens[i].equals("N")) {
                parent.left = new TreeNode(Integer.parseInt(tokens[i]));
                parents.offer(parent.left);
            }
            i++;
            if (!tokens[i].equals("N")) {
                parent.right = new TreeNode(Integer.parseInt(tokens[i]));
                parents.offer(parent.right);
            }
            i++;
        }
        return root;
    }
}
