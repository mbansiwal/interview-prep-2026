package com.interview.blind75.bits;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.IntUnaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ReverseBitsTest {

    static Stream<Arguments> approaches() {
        ReverseBits s = new ReverseBits();
        return Stream.of(
                Arguments.of("bit by bit", (IntUnaryOperator) s::reverseBits),
                Arguments.of("mask swaps", (IntUnaryOperator) s::reverseBitsMasks));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example1(String name, IntUnaryOperator f) { assertEquals(964176192, f.applyAsInt(43261596)); }

    // 11111111111111111111111111111101 → 10111111111111111111111111111111 = 3221225471 unsigned
    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example2Unsigned(String name, IntUnaryOperator f) {
        assertEquals("3221225471", Integer.toUnsignedString(f.applyAsInt(-3)));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void allOnesExceptTop(String name, IntUnaryOperator f) { assertEquals(-2, f.applyAsInt(Integer.MAX_VALUE)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void zero(String name, IntUnaryOperator f) { assertEquals(0, f.applyAsInt(0)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void matchesIntegerReverse(String name, IntUnaryOperator f) {
        for (int x : new int[]{1, 2, 0x12345678, Integer.MIN_VALUE, -1, 0x0F0F0F0F}) {
            assertEquals(Integer.reverse(x), f.applyAsInt(x), "x=" + x);
        }
    }
}
