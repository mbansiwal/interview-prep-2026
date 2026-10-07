package com.interview.blind75.trie;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class WordSearchIITest {

    private final WordSearchII solution = new WordSearchII();

    @Test
    void findWords() {
        char[][] board = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };
        List<String> result = solution.findWords(board, new String[]{"oath","pea","eat","rain"});
        assertEquals(2, result.size());
        assertTrue(result.containsAll(List.of("eat", "oath")));
    }

    @Test
    void noWordsFound() {
        char[][] board = {{'a'}};
        assertTrue(solution.findWords(board, new String[]{"b"}).isEmpty());
    }

    @Test
    void singleWord() {
        char[][] board = {{'a','b'},{'c','d'}};
        List<String> result = solution.findWords(board, new String[]{"ab", "cd", "abdc"});
        assertTrue(result.contains("ab"));
        assertTrue(result.contains("abdc"));
    }
}
