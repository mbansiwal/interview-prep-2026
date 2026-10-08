package com.interview.blind75.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CoinChangeTest {

    interface Solver { int solve(int[] coins, int amount); }

    static Stream<Arguments> approaches() {
        CoinChange s = new CoinChange();
        return Stream.of(
                Arguments.of("bottom-up", (Solver) s::coinChange),
                Arguments.of("memo", (Solver) s::coinChangeMemo),
                Arguments.of("bfs", (Solver) s::coinChangeBfs));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example1(String name, Solver f) { assertEquals(3, f.solve(new int[]{1, 2, 5}, 11)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void impossible(String name, Solver f) { assertEquals(-1, f.solve(new int[]{2}, 3)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void zeroAmount(String name, Solver f) { assertEquals(0, f.solve(new int[]{1}, 0)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void greedyFails(String name, Solver f) { assertEquals(2, f.solve(new int[]{1, 3, 4}, 6)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void largerAmount(String name, Solver f) { assertEquals(20, f.solve(new int[]{186, 419, 83, 408}, 6249)); }
}
