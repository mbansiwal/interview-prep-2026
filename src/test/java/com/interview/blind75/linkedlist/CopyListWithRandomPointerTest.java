package com.interview.blind75.linkedlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CopyListWithRandomPointerTest {

    private final CopyListWithRandomPointer solution = new CopyListWithRandomPointer();

    @Test
    void deepCopy() {
        CopyListWithRandomPointer.Node n1 = new CopyListWithRandomPointer.Node(7);
        CopyListWithRandomPointer.Node n2 = new CopyListWithRandomPointer.Node(13);
        CopyListWithRandomPointer.Node n3 = new CopyListWithRandomPointer.Node(11);
        n1.next = n2; n2.next = n3;
        n1.random = null; n2.random = n1; n3.random = n3;

        CopyListWithRandomPointer.Node copy = solution.copyRandomList(n1);

        assertNotSame(n1, copy);
        assertEquals(7, copy.val);
        assertEquals(13, copy.next.val);
        assertNull(copy.random);
        assertSame(copy, copy.next.random);
    }

    @Test
    void nullInput() {
        assertNull(solution.copyRandomList(null));
    }

    @Test
    void singleNode() {
        CopyListWithRandomPointer.Node n = new CopyListWithRandomPointer.Node(1);
        n.random = n;
        CopyListWithRandomPointer.Node copy = solution.copyRandomList(n);
        assertNotSame(n, copy);
        assertSame(copy, copy.random);
    }
}
