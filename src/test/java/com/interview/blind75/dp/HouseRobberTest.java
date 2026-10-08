package com.interview.blind75.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HouseRobberTest {

    static Stream<Arguments> approaches() {
        HouseRobber s = new HouseRobber();
        return Stream.of(
                Arguments.of("rolling O(1)", (ToIntFunction<int[]>) s::rob),
                Arguments.of("memo", (ToIntFunction<int[]>) s::robMemo),
                Arguments.of("tabulation", (ToIntFunction<int[]>) s::robTabulation));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example1(String name, ToIntFunction<int[]> f) { assertEquals(4, f.applyAsInt(new int[]{1, 2, 3, 1})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example2(String name, ToIntFunction<int[]> f) { assertEquals(12, f.applyAsInt(new int[]{2, 7, 9, 3, 1})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void singleHouse(String name, ToIntFunction<int[]> f) { assertEquals(5, f.applyAsInt(new int[]{5})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void skipTwoInARow(String name, ToIntFunction<int[]> f) { assertEquals(4, f.applyAsInt(new int[]{2, 1, 1, 2})); }
}
