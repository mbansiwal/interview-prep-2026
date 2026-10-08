package com.interview.blind75.stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DailyTemperaturesApproachesTest {

    static Stream<UnaryOperator<int[]>> approaches() {
        DailyTemperatures s = new DailyTemperatures();
        return Stream.of(s::dailyTemperatures, s::dailyTemperaturesBackwardJumps);
    }

    @ParameterizedTest @MethodSource("approaches")
    void example1(UnaryOperator<int[]> f) {
        assertArrayEquals(new int[]{1, 1, 4, 2, 1, 1, 0, 0}, f.apply(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
    }

    @ParameterizedTest @MethodSource("approaches")
    void increasing(UnaryOperator<int[]> f) { assertArrayEquals(new int[]{1, 1, 1, 0}, f.apply(new int[]{30, 40, 50, 60})); }

    @ParameterizedTest @MethodSource("approaches")
    void decreasing(UnaryOperator<int[]> f) { assertArrayEquals(new int[]{0, 0, 0}, f.apply(new int[]{90, 60, 30})); }

    @ParameterizedTest @MethodSource("approaches")
    void equalTemperaturesAreNotWarmer(UnaryOperator<int[]> f) {
        assertArrayEquals(new int[]{3, 2, 1, 0}, f.apply(new int[]{50, 50, 50, 51}));
    }
}
