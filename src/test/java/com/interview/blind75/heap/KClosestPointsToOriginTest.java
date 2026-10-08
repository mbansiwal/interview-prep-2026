package com.interview.blind75.heap;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class KClosestPointsToOriginTest {

    private static final KClosestPointsToOrigin solution = new KClosestPointsToOrigin();

    static Stream<Named<BiFunction<int[][], Integer, int[][]>>> approaches() {
        return Stream.of(
                Named.of("heap", solution::kClosest),
                Named.of("quickselect", solution::kClosestQuickselect),
                Named.of("sort", solution::kClosestSort));
    }

    // Answer order is free, so compare as sorted sets of points.
    private static int[][] normalize(int[][] points) {
        int[][] copy = Arrays.stream(points).map(int[]::clone).toArray(int[][]::new);
        Arrays.sort(copy, Comparator.<int[]>comparingInt(p -> p[0]).thenComparingInt(p -> p[1]));
        return copy;
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void k1(BiFunction<int[][], Integer, int[][]> approach) {
        int[][] result = approach.apply(new int[][]{{1, 3}, {-2, 2}}, 1);
        assertArrayEquals(new int[][]{{-2, 2}}, result);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void k2(BiFunction<int[][], Integer, int[][]> approach) {
        int[][] result = approach.apply(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2);
        assertArrayEquals(normalize(new int[][]{{3, 3}, {-2, 4}}), normalize(result));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void allPoints(BiFunction<int[][], Integer, int[][]> approach) {
        int[][] result = approach.apply(new int[][]{{1, 0}, {0, 1}}, 2);
        assertArrayEquals(normalize(new int[][]{{1, 0}, {0, 1}}), normalize(result));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void largerInputWithNegatives(BiFunction<int[][], Integer, int[][]> approach) {
        int[][] points = {{10, 10}, {-1, -1}, {0, 2}, {5, -5}, {-3, 0}, {0, 0}, {7, 1}};
        int[][] result = approach.apply(points, 3);
        assertArrayEquals(normalize(new int[][]{{0, 0}, {-1, -1}, {0, 2}}), normalize(result));
    }
}
