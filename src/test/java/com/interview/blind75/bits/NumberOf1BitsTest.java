package com.interview.blind75.bits;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.IntUnaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NumberOf1BitsTest {

    static Stream<Arguments> approaches() {
        NumberOf1Bits s = new NumberOf1Bits();
        return Stream.of(
                Arguments.of("n & (n-1)", (IntUnaryOperator) s::hammingWeight),
                Arguments.of("shift each bit", (IntUnaryOperator) s::hammingWeightShift),
                Arguments.of("parallel SWAR", (IntUnaryOperator) s::hammingWeightParallel));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void threeBits(String name, IntUnaryOperator f) { assertEquals(3, f.applyAsInt(0b00000000000000000000000000001011)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void oneBit(String name, IntUnaryOperator f) { assertEquals(1, f.applyAsInt(0b10000000)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void zero(String name, IntUnaryOperator f) { assertEquals(0, f.applyAsInt(0)); }

    // 0xFFFFFFFD = 11111111111111111111111111111101 (negative as a Java int)
    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void highBitSet(String name, IntUnaryOperator f) {
        assertEquals(31, f.applyAsInt(-3));
        assertEquals(32, f.applyAsInt(-1));
        assertEquals(1, f.applyAsInt(Integer.MIN_VALUE));
    }
}
