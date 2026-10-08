package com.interview.blind75.greedy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ValidParenthesisStringTest {

    static Stream<Arguments> approaches() {
        ValidParenthesisString s = new ValidParenthesisString();
        return Stream.of(
                Arguments.of("greedy range", (Predicate<String>) s::checkValidString),
                Arguments.of("two pass", (Predicate<String>) s::checkValidStringTwoPass),
                Arguments.of("two stacks", (Predicate<String>) s::checkValidStringStacks));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void simple(String name, Predicate<String> f) { assertTrue(f.test("()")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void withStar(String name, Predicate<String> f) { assertTrue(f.test("(*)")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void starAsClose(String name, Predicate<String> f) { assertTrue(f.test("(*))")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void invalid(String name, Predicate<String> f) { assertFalse(f.test("(((")); }

    // the star comes before the '(' so it can't close it
    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void starOnWrongSide(String name, Predicate<String> f) { assertFalse(f.test("*(")); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void onlyStars(String name, Predicate<String> f) { assertTrue(f.test("***")); }
}
