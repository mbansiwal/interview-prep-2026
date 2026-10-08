package com.interview.blind75.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.IntBinaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UniquePathsTest {

    static Stream<Arguments> approaches() {
        UniquePaths s = new UniquePaths();
        return Stream.of(
                Arguments.of("1D rolling", (IntBinaryOperator) s::uniquePaths),
                Arguments.of("2D table", (IntBinaryOperator) s::uniquePaths2D),
                Arguments.of("combinatorics", (IntBinaryOperator) s::uniquePathsMath));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example1(String name, IntBinaryOperator f) { assertEquals(28, f.applyAsInt(3, 7)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example2(String name, IntBinaryOperator f) { assertEquals(3, f.applyAsInt(3, 2)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void singleRow(String name, IntBinaryOperator f) { assertEquals(1, f.applyAsInt(1, 5)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void singleCell(String name, IntBinaryOperator f) { assertEquals(1, f.applyAsInt(1, 1)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void largeGrid(String name, IntBinaryOperator f) { assertEquals(1916797311, f.applyAsInt(51, 9)); }
}
