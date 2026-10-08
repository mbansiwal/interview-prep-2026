package com.interview.blind75.stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CarFleetApproachesTest {

    interface Fleet { int count(int target, int[] position, int[] speed); }

    static Stream<Fleet> approaches() {
        CarFleet s = new CarFleet();
        return Stream.of(s::carFleet, s::carFleetStack, s::carFleetCountingSort);
    }

    @ParameterizedTest @MethodSource("approaches")
    void example1(Fleet f) { assertEquals(3, f.count(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3})); }

    @ParameterizedTest @MethodSource("approaches")
    void singleCar(Fleet f) { assertEquals(1, f.count(10, new int[]{3}, new int[]{3})); }

    @ParameterizedTest @MethodSource("approaches")
    void allMerge(Fleet f) { assertEquals(1, f.count(100, new int[]{0, 2, 4}, new int[]{4, 2, 1})); }

    @ParameterizedTest @MethodSource("approaches")
    void noneMerge(Fleet f) { assertEquals(3, f.count(10, new int[]{6, 8, 0}, new int[]{3, 2, 1})); }
}
