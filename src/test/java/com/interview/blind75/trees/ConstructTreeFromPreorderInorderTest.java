package com.interview.blind75.trees;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ConstructTreeFromPreorderInorderTest {

    interface Builder { TreeNode build(int[] preorder, int[] inorder); }

    static Stream<Builder> approaches() {
        return Stream.of(
                (pre, in) -> new ConstructTreeFromPreorderInorder().buildTree(pre, in),
                (pre, in) -> new ConstructTreeFromPreorderInorder().buildTreeNoMap(pre, in));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void buildTree(Builder builder) {
        TreeNode root = builder.build(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        assertEquals(3, root.val);
        assertEquals(9, root.left.val);
        assertNull(root.left.left);
        assertEquals(20, root.right.val);
        assertEquals(15, root.right.left.val);
        assertEquals(7, root.right.right.val);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleNode(Builder builder) {
        TreeNode root = builder.build(new int[]{1}, new int[]{1});
        assertEquals(1, root.val);
        assertNull(root.left);
        assertNull(root.right);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void leftSkewed(Builder builder) {
        TreeNode root = builder.build(new int[]{3, 2, 1}, new int[]{1, 2, 3});
        assertEquals(3, root.val);
        assertEquals(2, root.left.val);
        assertEquals(1, root.left.left.val);
        assertNull(root.right);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void rightSkewedWithNegatives(Builder builder) {
        TreeNode root = builder.build(new int[]{-1, 0, 5}, new int[]{-1, 0, 5});
        assertEquals(-1, root.val);
        assertNull(root.left);
        assertEquals(0, root.right.val);
        assertEquals(5, root.right.right.val);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void reusableInstance(Builder builder) {
        builder.build(new int[]{2, 1}, new int[]{1, 2});
        TreeNode root = builder.build(new int[]{1, 2}, new int[]{1, 2});
        assertEquals(1, root.val);
        assertEquals(2, root.right.val);
    }
}
