package com.interview.blind75.intervals;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomsIITest {

    private static final MeetingRoomsII solution = new MeetingRoomsII();

    static Stream<Named<ToIntFunction<int[][]>>> approaches() {
        return Stream.of(
                Named.of("min-heap", solution::minMeetingRooms),
                Named.of("chronological ordering", solution::minMeetingRoomsChronological));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void twoRooms(ToIntFunction<int[][]> approach) {
        assertEquals(2, approach.applyAsInt(new int[][]{{0,30},{5,10},{15,20}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void oneRoom(ToIntFunction<int[][]> approach) {
        assertEquals(1, approach.applyAsInt(new int[][]{{7,10},{2,4}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void allOverlap(ToIntFunction<int[][]> approach) {
        assertEquals(3, approach.applyAsInt(new int[][]{{1,10},{2,9},{3,8}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void backToBackMeetingsShareARoom(ToIntFunction<int[][]> approach) {
        assertEquals(1, approach.applyAsInt(new int[][]{{1,5},{5,10},{10,15}}));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void emptyAndSingle(ToIntFunction<int[][]> approach) {
        assertEquals(0, approach.applyAsInt(new int[][]{}));
        assertEquals(1, approach.applyAsInt(new int[][]{{3, 4}}));
    }
}
