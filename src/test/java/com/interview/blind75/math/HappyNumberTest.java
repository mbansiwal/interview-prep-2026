package com.interview.blind75.math;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.IntPredicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HappyNumberTest {

    static Stream<Arguments> approaches() {
        HappyNumber s = new HappyNumber();
        return Stream.of(
                Arguments.of("floyd", (IntPredicate) s::isHappy),
                Arguments.of("hash set", (IntPredicate) s::isHappyHashSet));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void happy19(String name, IntPredicate f) { assertTrue(f.test(19)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void unhappy2(String name, IntPredicate f) { assertFalse(f.test(2)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void happy1(String name, IntPredicate f) { assertTrue(f.test(1)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void happy7(String name, IntPredicate f) { assertTrue(f.test(7)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void largeUnhappy(String name, IntPredicate f) { assertFalse(f.test(Integer.MAX_VALUE)); }
}
