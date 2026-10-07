package com.interview.blind75.trees;

import java.util.ArrayDeque;
import java.util.Queue;

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
 * APPROACH: Preorder DFS with "N" for Null
 * ============================================================
 * Serialize:
 * 1. DFS preorder, write each node's value. Write "N" for null nodes.
 * 2. Separate tokens with commas.
 *
 * Deserialize:
 * 1. Split string into token queue.
 * 2. Recursively consume tokens: "N" → null, else create node and recurse.
 *
 * WHY THIS WORKS:
 * Preorder + explicit nulls fully encodes the tree structure. The queue
 * ensures we consume tokens in the same order they were produced.
 *
 * TIME  : O(n) both
 * SPACE : O(n) both
 * ============================================================
 */
public class SerializeDeserializeTree {

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
}
