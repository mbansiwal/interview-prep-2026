package com.interview.blind75.bits;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SingleNumberTest {

    static Stream<Arguments> approaches() {
        SingleNumber s = new SingleNumber();
        return Stream.of(
                Arguments.of("xor", (ToIntFunction<int[]>) s::singleNumber),
                Arguments.of("hash set", (ToIntFunction<int[]>) s::singleNumberHashSet));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example1(String name, ToIntFunction<int[]> f) { assertEquals(1, f.applyAsInt(new int[]{2, 2, 1})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void example2(String name, ToIntFunction<int[]> f) { assertEquals(4, f.applyAsInt(new int[]{4, 1, 2, 1, 2})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void singleElement(String name, ToIntFunction<int[]> f) { assertEquals(1, f.applyAsInt(new int[]{1})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void negativeNumbers(String name, ToIntFunction<int[]> f) { assertEquals(-7, f.applyAsInt(new int[]{-3, 5, -7, 5, -3})); }
}
