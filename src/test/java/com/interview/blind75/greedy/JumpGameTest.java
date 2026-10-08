package com.interview.blind75.greedy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class JumpGameTest {

    static Stream<Arguments> approaches() {
        JumpGame s = new JumpGame();
        return Stream.of(
                Arguments.of("forward", (Predicate<int[]>) s::canJump),
                Arguments.of("backward", (Predicate<int[]>) s::canJumpBackward));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void canReach(String name, Predicate<int[]> f) { assertTrue(f.test(new int[]{2, 3, 1, 1, 4})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void cannotReach(String name, Predicate<int[]> f) { assertFalse(f.test(new int[]{3, 2, 1, 0, 4})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void singleElement(String name, Predicate<int[]> f) { assertTrue(f.test(new int[]{0})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void stuckAtStart(String name, Predicate<int[]> f) { assertFalse(f.test(new int[]{0, 1})); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void jumpOverZero(String name, Predicate<int[]> f) { assertTrue(f.test(new int[]{2, 0, 0})); }
}
