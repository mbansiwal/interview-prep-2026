package com.interview.blind75.trie;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ImplementTrieTest {

    @Test
    void basicOperations() {
        ImplementTrie trie = new ImplementTrie();
        trie.insert("apple");
        assertTrue(trie.search("apple"));
        assertFalse(trie.search("app"));
        assertTrue(trie.startsWith("app"));
        trie.insert("app");
        assertTrue(trie.search("app"));
    }

    @Test
    void prefixNotWord() {
        ImplementTrie trie = new ImplementTrie();
        trie.insert("hello");
        assertFalse(trie.search("hell"));
        assertTrue(trie.startsWith("hell"));
    }

    @Test
    void notExists() {
        ImplementTrie trie = new ImplementTrie();
        assertFalse(trie.search("any"));
        assertFalse(trie.startsWith("any"));
    }
}
