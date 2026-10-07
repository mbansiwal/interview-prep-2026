package com.interview.blind75.graphs;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class WordLadderTest {

    private final WordLadder solution = new WordLadder();

    @Test
    void example1() {
        assertEquals(5, solution.ladderLength("hit", "cog",
            List.of("hot", "dot", "dog", "lot", "log", "cog")));
    }

    @Test
    void endWordNotInList() {
        assertEquals(0, solution.ladderLength("hit", "cog",
            List.of("hot", "dot", "dog", "lot", "log")));
    }

    @Test
    void endWordInListButUnreachable() {
        assertEquals(0, solution.ladderLength("hot", "dog", List.of("hot", "dog")));
    }

    @Test
    void directTransform() {
        assertEquals(2, solution.ladderLength("a", "c", List.of("a", "b", "c")));
    }
}
