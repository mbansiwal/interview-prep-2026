package com.interview.blind75.stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EvaluateRPNApproachesTest {

    static Stream<ToIntFunction<String[]>> approaches() {
        EvaluateRPN s = new EvaluateRPN();
        return Stream.of(s::evalRPN, s::evalRPNArrayStack);
    }

    @ParameterizedTest @MethodSource("approaches")
    void example1(ToIntFunction<String[]> f) { assertEquals(9, f.applyAsInt(new String[]{"2", "1", "+", "3", "*"})); }

    @ParameterizedTest @MethodSource("approaches")
    void divisionTruncates(ToIntFunction<String[]> f) { assertEquals(6, f.applyAsInt(new String[]{"4", "13", "5", "/", "+"})); }

    @ParameterizedTest @MethodSource("approaches")
    void example3(ToIntFunction<String[]> f) {
        assertEquals(22, f.applyAsInt(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }

    @ParameterizedTest @MethodSource("approaches")
    void singleNumber(ToIntFunction<String[]> f) { assertEquals(-7, f.applyAsInt(new String[]{"-7"})); }

    @ParameterizedTest @MethodSource("approaches")
    void operandOrderAndTruncation(ToIntFunction<String[]> f) {
        // (2 - 5) - (-7 / 2) = -3 - (-3) = 0   (division truncates toward zero)
        assertEquals(0, f.applyAsInt(new String[]{"2", "5", "-", "-7", "2", "/", "-"}));
    }
}
