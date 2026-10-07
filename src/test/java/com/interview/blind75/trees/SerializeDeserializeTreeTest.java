package com.interview.blind75.trees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SerializeDeserializeTreeTest {

    private final SerializeDeserializeTree codec = new SerializeDeserializeTree();

    @Test
    void roundTrip() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3, new TreeNode(4), new TreeNode(5)));
        String data = codec.serialize(root);
        TreeNode result = codec.deserialize(data);
        assertEquals(1, result.val);
        assertEquals(2, result.left.val);
        assertEquals(3, result.right.val);
        assertEquals(4, result.right.left.val);
        assertEquals(5, result.right.right.val);
    }

    @Test
    void nullTree() {
        assertNull(codec.deserialize(codec.serialize(null)));
    }

    @Test
    void singleNode() {
        TreeNode root = new TreeNode(42);
        TreeNode result = codec.deserialize(codec.serialize(root));
        assertEquals(42, result.val);
    }

    @Test
    void serializeIsStableAfterRoundTrip() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(6)),
                new TreeNode(3, new TreeNode(4), new TreeNode(5)));
        String data = codec.serialize(root);
        assertEquals(data, codec.serialize(codec.deserialize(data)));
    }

    @Test
    void negativeAndMultiDigitValues() {
        TreeNode root = new TreeNode(-10,
                new TreeNode(-200),
                new TreeNode(1000, null, new TreeNode(Integer.MIN_VALUE)));
        TreeNode result = codec.deserialize(codec.serialize(root));
        assertEquals(-10, result.val);
        assertEquals(-200, result.left.val);
        assertNull(result.right.left);
        assertEquals(Integer.MIN_VALUE, result.right.right.val);
        assertEquals(codec.serialize(root), codec.serialize(result));
    }
}
