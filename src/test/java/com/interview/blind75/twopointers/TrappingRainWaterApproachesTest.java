package com.interview.blind75.twopointers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TrappingRainWaterApproachesTest {

    static Stream<ToIntFunction<int[]>> approaches() {
        TrappingRainWater s = new TrappingRainWater();
        return Stream.of(s::trap, s::trapPrefixArrays, s::trapMonotonicStack);
    }

    @ParameterizedTest @MethodSource("approaches")
    void example1(ToIntFunction<int[]> f) { assertEquals(6, f.applyAsInt(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1})); }

    @ParameterizedTest @MethodSource("approaches")
    void example2(ToIntFunction<int[]> f) { assertEquals(9, f.applyAsInt(new int[]{4, 2, 0, 3, 2, 5})); }

    @ParameterizedTest @MethodSource("approaches")
    void singleBar(ToIntFunction<int[]> f) { assertEquals(0, f.applyAsInt(new int[]{5})); }

    @ParameterizedTest @MethodSource("approaches")
    void strictlyIncreasing(ToIntFunction<int[]> f) { assertEquals(0, f.applyAsInt(new int[]{1, 2, 3, 4})); }

    @ParameterizedTest @MethodSource("approaches")
    void valley(ToIntFunction<int[]> f) { assertEquals(7, f.applyAsInt(new int[]{3, 0, 2, 0, 4})); }
}
