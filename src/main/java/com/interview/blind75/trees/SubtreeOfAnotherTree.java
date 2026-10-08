package com.interview.blind75.trees;

/**
 * ============================================================
 * PROBLEM : Subtree of Another Tree
 * LINK    : https://leetcode.com/problems/subtree-of-another-tree/
 * DIFFICULTY: Easy
 * PATTERN : Trees
 * ============================================================
 *
 * DESCRIPTION:
 * Given the roots of two binary trees root and subRoot, return true
 * if subRoot is a subtree of root. A subtree is a node in root along
 * with all of its descendants.
 *
 * EXAMPLES:
 *   Input : root=[3,4,5,1,2], subRoot=[4,1,2]
 *   Output: true
 *
 *   Input : root=[3,4,5,1,2,null,null,null,null,0], subRoot=[4,1,2]
 *   Output: false
 *
 * CONSTRAINTS:
 *   - 1 <= number of nodes <= 2000
 *
 * ============================================================
 * APPROACHES  (m = nodes in root, n = nodes in subRoot, h = height of root)
 * ============================================================
 * APPROACH 1: isSameTree at Every Node
 * 1. At each node of root, check isSameTree(node, subRoot).
 * 2. If it matches, return true; otherwise try the left and right children.
 * Intuition: a matching subtree must start at some node of root — try each one.
 * TIME  : O(m * n) — worst case compares subRoot at every node
 * SPACE : O(h) — recursion stack
 *
 * APPROACH 2: Serialize Both Trees + KMP Substring Search
 * 1. Serialize each tree in preorder with null markers, e.g. ",3,4,#,#,5,#,#".
 * 2. subRoot is a subtree of root iff its string occurs inside root's string.
 * 3. Find it with KMP (linear time). The leading comma stops "2" matching inside "12".
 * Intuition: with null markers, preorder uniquely encodes a tree's shape and values,
 * and a subtree is a contiguous block of its parent's preorder string.
 * TIME  : O(m + n)
 * SPACE : O(m + n) — the two strings and the KMP table
 *
 * WHICH TO USE:
 * Approach 1 is the expected, easy-to-explain answer and fine for LeetCode limits.
 * Senior interviewers may push for linear time → Approach 2 (or Merkle-style hashing).
 * ============================================================
 */
public class SubtreeOfAnotherTree {

    /** Approach 1 — isSameTree at every node. TIME O(m·n) · SPACE O(h) recursion stack */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        if (isSameTree(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /** Approach 2 — Preorder serialization + KMP. TIME O(m+n) · SPACE O(m+n) */
    public boolean isSubtreeKmp(TreeNode root, TreeNode subRoot) {
        StringBuilder text = new StringBuilder(), pattern = new StringBuilder();
        serialize(root, text);
        serialize(subRoot, pattern);
        return kmpContains(text.toString(), pattern.toString());
    }

    private void serialize(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(",#");
            return;
        }
        sb.append(',').append(node.val);
        serialize(node.left, sb);
        serialize(node.right, sb);
    }

    private boolean kmpContains(String text, String pattern) {
        int[] lps = new int[pattern.length()];
        for (int i = 1, len = 0; i < pattern.length(); ) {
            if (pattern.charAt(i) == pattern.charAt(len)) lps[i++] = ++len;
            else if (len > 0) len = lps[len - 1];
            else lps[i++] = 0;
        }
        for (int i = 0, j = 0; i < text.length(); ) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == pattern.length()) return true;
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
        return false;
    }
}
