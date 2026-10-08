package com.interview.blind75.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CombinationSumIVTest {

    interface Solver { int solve(int[] nums, int target); }

    static Stream<Arguments> approaches() {
        CombinationSumIV s = new CombinationSumIV();
        return Stream.of(
                Arguments.of("bottom-up", (Solver) s::combinationSum4),
                Arguments.of("memo", (Solver) s::combinationSum4Memo));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void orderedSequencesCounted(String name, Solver f) { assertEquals(7, f.solve(new int[]{1, 2, 3}, 4)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void unreachableTarget(String name, Solver f) { assertEquals(0, f.solve(new int[]{9}, 3)); }

    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void singleCoinExactlyDivides(String name, Solver f) { assertEquals(1, f.solve(new int[]{2}, 6)); }

    // CoinChangeII counts {1,2},{1,1,1} = 2 for amount 3; ordered count is 3
    @ParameterizedTest(name = "{0}")
    @MethodSource("approaches")
    void differsFromCoinChangeII(String name, Solver f) { assertEquals(3, f.solve(new int[]{1, 2}, 3)); }
}
