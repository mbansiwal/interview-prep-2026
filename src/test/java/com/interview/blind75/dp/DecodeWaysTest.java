package com.interview.blind75.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DecodeWaysTest {

    static Stream<Arguments> approaches() {
        DecodeWays s = new DecodeWays();
        return Stream.of(
                Arguments.of("rolling O(1)", (ToIntFunction<String>) s::numDecodings),
                Arguments.of("memo", (ToIntFunction<String>) s::numDecodingsMemo),
                Arguments.of("tabulation", (ToIntFunction<String>) s::numDecodingsTabulation));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void twoWays(String name, ToIntFunction<String> f) { assertEquals(2, f.applyAsInt("12")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void threeWays(String name, ToIntFunction<String> f) { assertEquals(3, f.applyAsInt("226")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void leadingZero(String name, ToIntFunction<String> f) { assertEquals(0, f.applyAsInt("06")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void tenIsOnlyAPair(String name, ToIntFunction<String> f) { assertEquals(1, f.applyAsInt("10")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void doubleZeroImpossible(String name, ToIntFunction<String> f) { assertEquals(0, f.applyAsInt("100")); }

    // must split as 2|10|1 — "21" would leave "01", which is invalid
    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void zeroInMiddleForcesPairing(String name, ToIntFunction<String> f) { assertEquals(1, f.applyAsInt("2101")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void singleDigit(String name, ToIntFunction<String> f) { assertEquals(1, f.applyAsInt("7")); }
}
