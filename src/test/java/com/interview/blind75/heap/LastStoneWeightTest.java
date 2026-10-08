package com.interview.blind75.heap;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LastStoneWeightTest {

    private static final LastStoneWeight solution = new LastStoneWeight();

    static Stream<Named<ToIntFunction<int[]>>> approaches() {
        return Stream.of(
                Named.of("max-heap", solution::lastStoneWeight),
                Named.of("buckets", solution::lastStoneWeightBuckets));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void example(ToIntFunction<int[]> approach) {
        assertEquals(1, approach.applyAsInt(new int[]{2, 7, 4, 1, 8, 1}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleStone(ToIntFunction<int[]> approach) {
        assertEquals(1, approach.applyAsInt(new int[]{1}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void allSame(ToIntFunction<int[]> approach) {
        assertEquals(0, approach.applyAsInt(new int[]{2, 2}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void allDestroyed(ToIntFunction<int[]> approach) {
        assertEquals(0, approach.applyAsInt(new int[]{2, 2, 4, 4}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void oneHugeStone(ToIntFunction<int[]> approach) {
        assertEquals(994, approach.applyAsInt(new int[]{1000, 1, 2, 3}));
    }

    @Test
    void bucketsMatchHeapOnRandomInputs() {
        Random random = new Random(11);
        for (int trial = 0; trial < 300; trial++) {
            int[] stones = new int[1 + random.nextInt(30)];
            for (int i = 0; i < stones.length; i++) stones[i] = 1 + random.nextInt(50);
            assertEquals(solution.lastStoneWeight(stones.clone()), solution.lastStoneWeightBuckets(stones.clone()));
        }
    }
}
