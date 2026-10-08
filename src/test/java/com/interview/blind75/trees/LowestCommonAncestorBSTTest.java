package com.interview.blind75.trees;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LowestCommonAncestorBSTTest {

    interface Lca { TreeNode find(TreeNode root, TreeNode p, TreeNode q); }

    private static final LowestCommonAncestorBST solution = new LowestCommonAncestorBST();

    static Stream<Lca> approaches() {
        return Stream.of(solution::lowestCommonAncestor, solution::lowestCommonAncestorRecursive);
    }

    private TreeNode buildBST() {
        return new TreeNode(6,
                new TreeNode(2, new TreeNode(0), new TreeNode(4, new TreeNode(3), new TreeNode(5))),
                new TreeNode(8, new TreeNode(7), new TreeNode(9)));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void lcaAtRoot(Lca lca) {
        TreeNode root = buildBST();
        assertEquals(6, lca.find(root, root.left, root.right).val);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void nodeIsLCA(Lca lca) {
        TreeNode root = buildBST();
        assertEquals(2, lca.find(root, root.left, root.left.right).val);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void deepNodes(Lca lca) {
        TreeNode root = buildBST();
        assertEquals(4, lca.find(root, root.left.right.left, root.left.right.right).val);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void sameNode(Lca lca) {
        TreeNode root = buildBST();
        assertEquals(7, lca.find(root, root.right.left, root.right.left).val);
    }
}
