package com.interview.blind75.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.IntUnaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ClimbingStairsTest {

    static Stream<Arguments> approaches() {
        ClimbingStairs s = new ClimbingStairs();
        return Stream.of(
                Arguments.of("rolling O(1)", (IntUnaryOperator) s::climbStairs),
                Arguments.of("memo", (IntUnaryOperator) s::climbStairsMemo),
                Arguments.of("tabulation", (IntUnaryOperator) s::climbStairsTabulation));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void oneStep(String name, IntUnaryOperator f) { assertEquals(1, f.applyAsInt(1)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void twoSteps(String name, IntUnaryOperator f) { assertEquals(2, f.applyAsInt(2)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void threeSteps(String name, IntUnaryOperator f) { assertEquals(3, f.applyAsInt(3)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void tenSteps(String name, IntUnaryOperator f) { assertEquals(89, f.applyAsInt(10)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void maxConstraint(String name, IntUnaryOperator f) { assertEquals(1836311903, f.applyAsInt(45)); }
}
