package com.interview.blind75.graphs;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WordLadderTest {

    private static final WordLadder solution = new WordLadder();

    interface Ladder { int length(String begin, String end, List<String> words); }

    static Stream<Named<Ladder>> approaches() {
        return Stream.of(
                Named.of("bfs", solution::ladderLength),
                Named.of("bidirectional bfs", solution::ladderLengthBidirectional));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void example1(Ladder approach) {
        assertEquals(5, approach.length("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void endWordNotInList(Ladder approach) {
        assertEquals(0, approach.length("hit", "cog", List.of("hot", "dot", "dog", "lot", "log")));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void endWordInListButUnreachable(Ladder approach) {
        assertEquals(0, approach.length("hot", "dog", List.of("hot", "dog")));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void directTransform(Ladder approach) {
        assertEquals(2, approach.length("a", "c", List.of("a", "b", "c")));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void picksShortestOfSeveralPaths(Ladder approach) {
        // long route: aaa→baa→bba→bbb ; short route: aaa→aab→abb→bbb — both 4 words
        assertEquals(4, approach.length("aaa", "bbb", List.of("baa", "bba", "aab", "abb", "bbb", "bab")));
    }
}
