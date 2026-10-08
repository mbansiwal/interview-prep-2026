package com.interview.blind75.bits;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.IntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CountingBitsTest {

    static Stream<Arguments> approaches() {
        CountingBits s = new CountingBits();
        return Stream.of(
                Arguments.of("i >> 1", (IntFunction<int[]>) s::countBits),
                Arguments.of("i & (i-1)", (IntFunction<int[]>) s::countBitsLowestSetBit));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void n2(String name, IntFunction<int[]> f) { assertArrayEquals(new int[]{0, 1, 1}, f.apply(2)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void n5(String name, IntFunction<int[]> f) { assertArrayEquals(new int[]{0, 1, 1, 2, 1, 2}, f.apply(5)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void n0(String name, IntFunction<int[]> f) { assertArrayEquals(new int[]{0}, f.apply(0)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void matchesBitCountUpTo1000(String name, IntFunction<int[]> f) {
        int[] result = f.apply(1000);
        for (int i = 0; i <= 1000; i++) assertEquals(Integer.bitCount(i), result[i], "i=" + i);
    }
}
