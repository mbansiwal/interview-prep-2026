package com.interview.blind75.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntBiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EditDistanceTest {

    static Stream<Arguments> approaches() {
        EditDistance s = new EditDistance();
        return Stream.of(
                Arguments.of("2D table", (ToIntBiFunction<String, String>) s::minDistance),
                Arguments.of("one row", (ToIntBiFunction<String, String>) s::minDistanceOneRow),
                Arguments.of("memo", (ToIntBiFunction<String, String>) s::minDistanceMemo));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example1(String name, ToIntBiFunction<String, String> f) { assertEquals(3, f.applyAsInt("horse", "ros")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example2(String name, ToIntBiFunction<String, String> f) { assertEquals(5, f.applyAsInt("intention", "execution")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void emptyStrings(String name, ToIntBiFunction<String, String> f) { assertEquals(0, f.applyAsInt("", "")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void oneEmpty(String name, ToIntBiFunction<String, String> f) {
        assertEquals(4, f.applyAsInt("", "abcd"));
        assertEquals(4, f.applyAsInt("abcd", ""));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void shorterFirstArgument(String name, ToIntBiFunction<String, String> f) { assertEquals(3, f.applyAsInt("ros", "horse")); }
}
