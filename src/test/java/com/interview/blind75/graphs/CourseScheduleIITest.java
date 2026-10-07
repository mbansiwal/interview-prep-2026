package com.interview.blind75.graphs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CourseScheduleIITest {

    private final CourseScheduleII solution = new CourseScheduleII();

    private static void assertValidOrder(int numCourses, int[][] prereqs, int[] order) {
        assertEquals(numCourses, order.length);
        int[] position = new int[numCourses];
        java.util.Arrays.fill(position, -1);
        for (int i = 0; i < order.length; i++) position[order[i]] = i;
        for (int p : position) assertNotEquals(-1, p, "every course must appear");
        for (int[] pre : prereqs) {
            assertTrue(position[pre[1]] < position[pre[0]],
                    "prereq " + pre[1] + " must come before " + pre[0]);
        }
    }

    @Test
    void twoCoursesOrder() {
        int[][] prereqs = {{1, 0}};
        assertValidOrder(2, prereqs, solution.findOrder(2, prereqs));
    }

    @Test
    void fourCoursesDiamond() {
        int[][] prereqs = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        assertValidOrder(4, prereqs, solution.findOrder(4, prereqs));
    }

    @Test
    void cycleReturnsEmpty() {
        assertEquals(0, solution.findOrder(2, new int[][]{{1, 0}, {0, 1}}).length);
    }

    @Test
    void noDependencies() {
        assertValidOrder(3, new int[0][], solution.findOrder(3, new int[][]{}));
    }
}
