package com.interview.blind75.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HouseRobberIITest {

    static Stream<Arguments> approaches() {
        HouseRobberII s = new HouseRobberII();
        return Stream.of(
                Arguments.of("rolling O(1)", (ToIntFunction<int[]>) s::rob),
                Arguments.of("tabulation", (ToIntFunction<int[]>) s::robTabulation));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void circularThree(String name, ToIntFunction<int[]> f) { assertEquals(3, f.applyAsInt(new int[]{2, 3, 2})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void circularFour(String name, ToIntFunction<int[]> f) { assertEquals(4, f.applyAsInt(new int[]{1, 2, 3, 1})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void singleHouse(String name, ToIntFunction<int[]> f) { assertEquals(1, f.applyAsInt(new int[]{1})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void twoHouses(String name, ToIntFunction<int[]> f) { assertEquals(7, f.applyAsInt(new int[]{7, 3})); }
}
