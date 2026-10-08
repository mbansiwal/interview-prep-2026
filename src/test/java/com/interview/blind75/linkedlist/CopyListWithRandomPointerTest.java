package com.interview.blind75.linkedlist;

import com.interview.blind75.linkedlist.CopyListWithRandomPointer.Node;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CopyListWithRandomPointerTest {

    private static final CopyListWithRandomPointer solution = new CopyListWithRandomPointer();

    static Stream<UnaryOperator<Node>> approaches() {
        return Stream.of(solution::copyRandomList, solution::copyRandomListInterleaved);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void deepCopy(UnaryOperator<Node> copyFn) {
        Node n1 = new Node(7), n2 = new Node(13), n3 = new Node(11);
        n1.next = n2; n2.next = n3;
        n1.random = null; n2.random = n1; n3.random = n3;

        Node copy = copyFn.apply(n1);

        assertNotSame(n1, copy);
        assertEquals(7, copy.val);
        assertEquals(13, copy.next.val);
        assertEquals(11, copy.next.next.val);
        assertNull(copy.next.next.next);
        assertNull(copy.random);
        assertSame(copy, copy.next.random);
        assertSame(copy.next.next, copy.next.next.random);
        assertNotSame(n3, copy.next.next);
        // original list is left intact
        assertSame(n2, n1.next);
        assertSame(n3, n2.next);
        assertNull(n3.next);
        assertSame(n1, n2.random);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void nullInput(UnaryOperator<Node> copyFn) {
        assertNull(copyFn.apply(null));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleNode(UnaryOperator<Node> copyFn) {
        Node n = new Node(1);
        n.random = n;
        Node copy = copyFn.apply(n);
        assertNotSame(n, copy);
        assertSame(copy, copy.random);
        assertNull(copy.next);
        assertNull(n.next);
    }
}
