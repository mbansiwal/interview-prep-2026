package com.interview.blind75.stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ValidParenthesesApproachesTest {

    static Stream<Predicate<String>> approaches() {
        ValidParentheses s = new ValidParentheses();
        return Stream.of(s::isValid, s::isValidExpectedCloser);
    }

    @ParameterizedTest @MethodSource("approaches")
    void mixedValid(Predicate<String> f) { assertTrue(f.test("()[]{}")); }

    @ParameterizedTest @MethodSource("approaches")
    void nestedValid(Predicate<String> f) { assertTrue(f.test("{[()()]}")); }

    @ParameterizedTest @MethodSource("approaches")
    void wrongOrder(Predicate<String> f) { assertFalse(f.test("(]")); }

    @ParameterizedTest @MethodSource("approaches")
    void unclosed(Predicate<String> f) { assertFalse(f.test("((")); }

    @ParameterizedTest @MethodSource("approaches")
    void closerFirst(Predicate<String> f) { assertFalse(f.test("]")); }
}
