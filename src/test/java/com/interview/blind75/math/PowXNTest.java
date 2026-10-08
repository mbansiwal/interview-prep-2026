package com.interview.blind75.math;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PowXNTest {

    interface Pow { double apply(double x, int n); }

    static Stream<Arguments> approaches() {
        PowXN s = new PowXN();
        return Stream.of(
                Arguments.of("recursive", (Pow) s::myPow),
                Arguments.of("iterative", (Pow) s::myPowIterative));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void positivePower(String name, Pow f) { assertEquals(1024.0, f.apply(2.0, 10), 1e-5); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void fractionalBase(String name, Pow f) { assertEquals(9.261, f.apply(2.1, 3), 1e-5); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void negativePower(String name, Pow f) { assertEquals(0.25, f.apply(2.0, -2), 1e-5); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void powerOfZero(String name, Pow f) { assertEquals(1.0, f.apply(5.0, 0), 1e-5); }

    // -Integer.MIN_VALUE overflows int; the solution must widen to long first
    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void minIntExponentDoesNotOverflow(String name, Pow f) {
        assertEquals(1.0, f.apply(1.0, Integer.MIN_VALUE), 1e-9);
        assertEquals(0.0, f.apply(2.0, Integer.MIN_VALUE), 1e-9);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void negativeBaseOddPower(String name, Pow f) { assertEquals(-8.0, f.apply(-2.0, 3), 1e-9); }
}
