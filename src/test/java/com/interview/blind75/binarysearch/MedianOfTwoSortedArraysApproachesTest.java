package com.interview.blind75.binarysearch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToDoubleBiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MedianOfTwoSortedArraysApproachesTest {

    static Stream<ToDoubleBiFunction<int[], int[]>> approaches() {
        MedianOfTwoSortedArrays s = new MedianOfTwoSortedArrays();
        return Stream.of(s::findMedianSortedArrays, s::findMedianSortedArraysMergeWalk);
    }

    @ParameterizedTest @MethodSource("approaches")
    void oddTotal(ToDoubleBiFunction<int[], int[]> f) { assertEquals(2.0, f.applyAsDouble(new int[]{1, 3}, new int[]{2}), 1e-9); }

    @ParameterizedTest @MethodSource("approaches")
    void evenTotal(ToDoubleBiFunction<int[], int[]> f) { assertEquals(2.5, f.applyAsDouble(new int[]{1, 2}, new int[]{3, 4}), 1e-9); }

    @ParameterizedTest @MethodSource("approaches")
    void oneEmpty(ToDoubleBiFunction<int[], int[]> f) { assertEquals(3.5, f.applyAsDouble(new int[]{}, new int[]{2, 3, 4, 5}), 1e-9); }

    @ParameterizedTest @MethodSource("approaches")
    void interleavedWithNegatives(ToDoubleBiFunction<int[], int[]> f) {
        assertEquals(0.5, f.applyAsDouble(new int[]{-5, -1, 3}, new int[]{-2, 2, 4}), 1e-9); // merged: -5,-2,-1,2,3,4
    }

    @ParameterizedTest @MethodSource("approaches")
    void singleElementEach(ToDoubleBiFunction<int[], int[]> f) { assertEquals(1.5, f.applyAsDouble(new int[]{1}, new int[]{2}), 1e-9); }
}
