package com.interview.blind75.slidingwindow;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BestTimeToBuyAndSellStockApproachesTest {

    static Stream<ToIntFunction<int[]>> approaches() {
        BestTimeToBuyAndSellStock s = new BestTimeToBuyAndSellStock();
        return Stream.of(s::maxProfit, s::maxProfitKadane);
    }

    @ParameterizedTest @MethodSource("approaches")
    void example1(ToIntFunction<int[]> f) { assertEquals(5, f.applyAsInt(new int[]{7, 1, 5, 3, 6, 4})); }

    @ParameterizedTest @MethodSource("approaches")
    void decreasing(ToIntFunction<int[]> f) { assertEquals(0, f.applyAsInt(new int[]{7, 6, 4, 3, 1})); }

    @ParameterizedTest @MethodSource("approaches")
    void singleDay(ToIntFunction<int[]> f) { assertEquals(0, f.applyAsInt(new int[]{5})); }

    @ParameterizedTest @MethodSource("approaches")
    void dipAfterPeak(ToIntFunction<int[]> f) { assertEquals(8, f.applyAsInt(new int[]{3, 9, 1, 2, 4, 7, 9})); }
}
