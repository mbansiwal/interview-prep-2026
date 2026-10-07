package com.interview.blind75.intervals;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomsTest {

    private final MeetingRooms solution = new MeetingRooms();

    @Test
    void cannotAttend() { assertFalse(solution.canAttendMeetings(new int[][]{{0,30},{5,10},{15,20}})); }

    @Test
    void canAttend() { assertTrue(solution.canAttendMeetings(new int[][]{{7,10},{2,4}})); }

    @Test
    void empty() { assertTrue(solution.canAttendMeetings(new int[][]{})); }
}
