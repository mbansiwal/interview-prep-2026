package com.interview.blind75.graphs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CourseScheduleTest {

    private final CourseSchedule solution = new CourseSchedule();

    @Test
    void canFinish() {
        assertTrue(solution.canFinish(2, new int[][]{{1, 0}}));
    }

    @Test
    void cyclePrevents() {
        assertFalse(solution.canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }

    @Test
    void noPrerequisites() {
        assertTrue(solution.canFinish(5, new int[][]{}));
    }
}
