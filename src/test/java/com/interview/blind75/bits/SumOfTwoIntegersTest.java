package com.interview.blind75.bits;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.IntBinaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SumOfTwoIntegersTest {

    static Stream<Arguments> approaches() {
        SumOfTwoIntegers s = new SumOfTwoIntegers();
        return Stream.of(
                Arguments.of("iterative", (IntBinaryOperator) s::getSum),
                Arguments.of("recursive", (IntBinaryOperator) s::getSumRecursive));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void simple(String name, IntBinaryOperator f) { assertEquals(3, f.applyAsInt(1, 2)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void negativeNumbers(String name, IntBinaryOperator f) { assertEquals(-1, f.applyAsInt(-2, 1)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void withZero(String name, IntBinaryOperator f) { assertEquals(5, f.applyAsInt(5, 0)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void bothNegative(String name, IntBinaryOperator f) { assertEquals(-2000, f.applyAsInt(-1000, -1000)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void cancelsToZero(String name, IntBinaryOperator f) { assertEquals(0, f.applyAsInt(-1000, 1000)); }
}
