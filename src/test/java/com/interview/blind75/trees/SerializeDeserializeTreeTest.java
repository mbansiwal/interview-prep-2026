package com.interview.blind75.trees;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SerializeDeserializeTreeTest {

    record Codec(String name, Function<TreeNode, String> serialize, Function<String, TreeNode> deserialize) {
        @Override public String toString() { return name; }
    }

    private static final SerializeDeserializeTree solution = new SerializeDeserializeTree();

    static Stream<Codec> approaches() {
        return Stream.of(
                new Codec("preorder DFS", solution::serialize, solution::deserialize),
                new Codec("BFS level order", solution::serializeBfs, solution::deserializeBfs));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void roundTrip(Codec codec) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3, new TreeNode(4), new TreeNode(5)));
        TreeNode result = codec.deserialize().apply(codec.serialize().apply(root));
        assertEquals(1, result.val);
        assertEquals(2, result.left.val);
        assertNull(result.left.left);
        assertEquals(3, result.right.val);
        assertEquals(4, result.right.left.val);
        assertEquals(5, result.right.right.val);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void nullTree(Codec codec) {
        assertNull(codec.deserialize().apply(codec.serialize().apply(null)));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleNode(Codec codec) {
        assertEquals(42, codec.deserialize().apply(codec.serialize().apply(new TreeNode(42))).val);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void serializeIsStableAfterRoundTrip(Codec codec) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(6)),
                new TreeNode(3, new TreeNode(4), new TreeNode(5)));
        String data = codec.serialize().apply(root);
        assertEquals(data, codec.serialize().apply(codec.deserialize().apply(data)));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void negativeAndMultiDigitValues(Codec codec) {
        TreeNode root = new TreeNode(-10,
                new TreeNode(-200),
                new TreeNode(1000, null, new TreeNode(Integer.MIN_VALUE)));
        TreeNode result = codec.deserialize().apply(codec.serialize().apply(root));
        assertEquals(-10, result.val);
        assertEquals(-200, result.left.val);
        assertNull(result.right.left);
        assertEquals(Integer.MIN_VALUE, result.right.right.val);
        assertEquals(codec.serialize().apply(root), codec.serialize().apply(result));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void deepSkewedTree(Codec codec) {
        TreeNode root = new TreeNode(0), curr = root;
        for (int i = 1; i < 50; i++) curr = curr.right = new TreeNode(i);
        String data = codec.serialize().apply(root);
        assertEquals(data, codec.serialize().apply(codec.deserialize().apply(data)));
    }
}
