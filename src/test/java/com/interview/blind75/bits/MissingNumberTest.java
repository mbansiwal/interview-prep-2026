package com.interview.blind75.bits;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MissingNumberTest {

    static Stream<Arguments> approaches() {
        MissingNumber s = new MissingNumber();
        return Stream.of(
                Arguments.of("xor", (ToIntFunction<int[]>) s::missingNumber),
                Arguments.of("gauss", (ToIntFunction<int[]>) s::missingNumberGauss));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void missing2(String name, ToIntFunction<int[]> f) { assertEquals(2, f.applyAsInt(new int[]{3, 0, 1})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void missing2Again(String name, ToIntFunction<int[]> f) { assertEquals(2, f.applyAsInt(new int[]{0, 1})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void missingLast(String name, ToIntFunction<int[]> f) { assertEquals(8, f.applyAsInt(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void missingZero(String name, ToIntFunction<int[]> f) { assertEquals(0, f.applyAsInt(new int[]{1})); }
}
