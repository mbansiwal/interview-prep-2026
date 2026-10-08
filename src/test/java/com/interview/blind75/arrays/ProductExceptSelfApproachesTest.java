package com.interview.blind75.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProductExceptSelfApproachesTest {

    static Stream<UnaryOperator<int[]>> approaches() {
        ProductExceptSelf s = new ProductExceptSelf();
        return Stream.of(s::productExceptSelf, s::productExceptSelfPrefixSuffixArrays);
    }

    @ParameterizedTest @MethodSource("approaches")
    void example1(UnaryOperator<int[]> f) { assertArrayEquals(new int[]{24, 12, 8, 6}, f.apply(new int[]{1, 2, 3, 4})); }

    @ParameterizedTest @MethodSource("approaches")
    void withZero(UnaryOperator<int[]> f) { assertArrayEquals(new int[]{0, 0, 9, 0, 0}, f.apply(new int[]{-1, 1, 0, -3, 3})); }

    @ParameterizedTest @MethodSource("approaches")
    void twoElements(UnaryOperator<int[]> f) { assertArrayEquals(new int[]{3, 2}, f.apply(new int[]{2, 3})); }

    @ParameterizedTest @MethodSource("approaches")
    void twoZeros(UnaryOperator<int[]> f) { assertArrayEquals(new int[]{0, 0, 0}, f.apply(new int[]{0, 4, 0})); }
}
