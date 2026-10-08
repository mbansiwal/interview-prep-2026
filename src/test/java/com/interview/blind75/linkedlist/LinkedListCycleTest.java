package com.interview.blind75.linkedlist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListCycleTest {

    private static final LinkedListCycle solution = new LinkedListCycle();

    static Stream<Predicate<ListNode>> approaches() {
        return Stream.of(solution::hasCycle, solution::hasCycleWithSet);
    }

    private static ListNode withCycle(int pos, int... vals) {
        ListNode head = ListNode.of(vals);
        ListNode entry = head, tail = head;
        for (int i = 0; i < pos; i++) entry = entry.next;
        while (tail.next != null) tail = tail.next;
        tail.next = entry;
        return head;
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void hasCycle(Predicate<ListNode> detect) {
        assertTrue(detect.test(withCycle(1, 3, 2, 0, -4)));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void cycleToHead(Predicate<ListNode> detect) {
        assertTrue(detect.test(withCycle(0, 1, 2)));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void selfLoop(Predicate<ListNode> detect) {
        assertTrue(detect.test(withCycle(0, 1)));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void noCycle(Predicate<ListNode> detect) {
        assertFalse(detect.test(ListNode.of(1, 2, 3)));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void nullHead(Predicate<ListNode> detect) {
        assertFalse(detect.test(null));
    }
}
