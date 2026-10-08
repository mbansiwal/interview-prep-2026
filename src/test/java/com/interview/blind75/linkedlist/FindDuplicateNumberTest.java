package com.interview.blind75.linkedlist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FindDuplicateNumberTest {

    private static final FindDuplicateNumber solution = new FindDuplicateNumber();

    static Stream<ToIntFunction<int[]>> approaches() {
        return Stream.of(solution::findDuplicate, solution::findDuplicateBinarySearch,
                solution::findDuplicateSignMarking);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void example1(ToIntFunction<int[]> find) {
        assertEquals(2, find.applyAsInt(new int[]{1, 3, 4, 2, 2}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void example2(ToIntFunction<int[]> find) {
        assertEquals(3, find.applyAsInt(new int[]{3, 1, 3, 4, 2}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void duplicateAtStart(ToIntFunction<int[]> find) {
        assertEquals(1, find.applyAsInt(new int[]{1, 1}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void duplicateRepeatedManyTimes(ToIntFunction<int[]> find) {
        assertEquals(2, find.applyAsInt(new int[]{2, 2, 2, 2, 2}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void inputLeftUnchanged(ToIntFunction<int[]> find) {
        int[] nums = {3, 1, 3, 4, 2};
        find.applyAsInt(nums);
        assertArrayEquals(new int[]{3, 1, 3, 4, 2}, nums);
    }
}
