package com.interview.blind75.heap;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TaskSchedulerTest {

    private static final TaskScheduler solution = new TaskScheduler();

    static Stream<Named<BiFunction<char[], Integer, Integer>>> approaches() {
        return Stream.of(
                Named.of("formula", solution::leastInterval),
                Named.of("heap simulation", solution::leastIntervalHeap));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void withCooldown(BiFunction<char[], Integer, Integer> approach) {
        assertEquals(8, approach.apply(new char[]{'A','A','A','B','B','B'}, 2));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void noCooldown(BiFunction<char[], Integer, Integer> approach) {
        assertEquals(6, approach.apply(new char[]{'A','A','A','B','B','B'}, 0));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void manyUnique(BiFunction<char[], Integer, Integer> approach) {
        assertEquals(9, approach.apply(new char[]{'A','A','A','B','C','D','E','F','G'}, 2));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void singleTaskLongCooldown(BiFunction<char[], Integer, Integer> approach) {
        assertEquals(1, approach.apply(new char[]{'A'}, 100));
        assertEquals(7, approach.apply(new char[]{'A','A','A'}, 2));
    }

    @Test
    void approachesAgreeOnRandomInputs() {
        Random random = new Random(3);
        for (int trial = 0; trial < 300; trial++) {
            char[] tasks = new char[1 + random.nextInt(40)];
            for (int i = 0; i < tasks.length; i++) tasks[i] = (char) ('A' + random.nextInt(5));
            int n = random.nextInt(5);
            assertEquals(solution.leastInterval(tasks, n), solution.leastIntervalHeap(tasks, n));
        }
    }
}
