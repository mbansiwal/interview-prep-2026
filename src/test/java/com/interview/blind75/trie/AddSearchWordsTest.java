package com.interview.blind75.trie;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddSearchWordsTest {

    @Test
    void wildcardSearch() {
        AddSearchWords ws = new AddSearchWords();
        ws.addWord("bad");
        ws.addWord("dad");
        ws.addWord("mad");
        assertFalse(ws.search("pad"));
        assertTrue(ws.search(".ad"));
        assertTrue(ws.search("b.."));
    }

    @Test
    void exactSearch() {
        AddSearchWords ws = new AddSearchWords();
        ws.addWord("word");
        assertTrue(ws.search("word"));
        assertFalse(ws.search("wore"));
    }

    @Test
    void allWildcard() {
        AddSearchWords ws = new AddSearchWords();
        ws.addWord("abc");
        assertTrue(ws.search("..."));
        assertFalse(ws.search("...."));
    }
}
