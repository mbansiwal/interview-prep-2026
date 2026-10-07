package com.interview.blind75.intervals;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomsIITest {

    private final MeetingRoomsII solution = new MeetingRoomsII();

    @Test
    void twoRooms() { assertEquals(2, solution.minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}})); }

    @Test
    void oneRoom() { assertEquals(1, solution.minMeetingRooms(new int[][]{{7,10},{2,4}})); }

    @Test
    void allOverlap() { assertEquals(3, solution.minMeetingRooms(new int[][]{{1,10},{2,9},{3,8}})); }
}
