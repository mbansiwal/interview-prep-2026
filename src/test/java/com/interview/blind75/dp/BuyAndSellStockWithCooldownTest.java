package com.interview.blind75.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BuyAndSellStockWithCooldownTest {

    static Stream<Arguments> approaches() {
        BuyAndSellStockWithCooldown s = new BuyAndSellStockWithCooldown();
        return Stream.of(
                Arguments.of("rolling O(1)", (ToIntFunction<int[]>) s::maxProfit),
                Arguments.of("state arrays", (ToIntFunction<int[]>) s::maxProfitArrays));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example1(String name, ToIntFunction<int[]> f) { assertEquals(3, f.applyAsInt(new int[]{1, 2, 3, 0, 2})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void singlePrice(String name, ToIntFunction<int[]> f) { assertEquals(0, f.applyAsInt(new int[]{1})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void decreasing(String name, ToIntFunction<int[]> f) { assertEquals(0, f.applyAsInt(new int[]{5, 4, 3, 2, 1})); }

    // without cooldown you'd do 1→2 and 2→3 for 2; with cooldown best single trade 1→3 = 2
    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void cooldownBlocksBackToBack(String name, ToIntFunction<int[]> f) { assertEquals(2, f.applyAsInt(new int[]{1, 2, 3})); }
}
