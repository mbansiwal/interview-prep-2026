package com.interview.blind75.backtracking;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SubsetsIITest {

    private final SubsetsII solution = new SubsetsII();

    @Test
    void withDuplicates() {
        List<List<Integer>> result = solution.subsetsWithDup(new int[]{1, 2, 2});
        assertEquals(6, result.size());
    }

    @Test
    void singleElement() {
        List<List<Integer>> result = solution.subsetsWithDup(new int[]{0});
        assertEquals(2, result.size());
    }

    @Test
    void allSame() {
        List<List<Integer>> result = solution.subsetsWithDup(new int[]{1, 1, 1});
        assertEquals(4, result.size()); // [], [1], [1,1], [1,1,1]
    }
}
