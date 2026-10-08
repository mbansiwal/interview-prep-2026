package com.interview.blind75.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CoinChangeIITest {

    interface Solver { int solve(int amount, int[] coins); }

    static Stream<Arguments> approaches() {
        CoinChangeII s = new CoinChangeII();
        return Stream.of(
                Arguments.of("1D", (Solver) s::change),
                Arguments.of("2D", (Solver) s::change2D));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example1(String name, Solver f) { assertEquals(4, f.solve(5, new int[]{1, 2, 5})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void impossible(String name, Solver f) { assertEquals(0, f.solve(3, new int[]{2})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void zeroAmount(String name, Solver f) { assertEquals(1, f.solve(0, new int[]{1, 2, 3})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void singleCoin(String name, Solver f) { assertEquals(1, f.solve(10, new int[]{10})); }
}
