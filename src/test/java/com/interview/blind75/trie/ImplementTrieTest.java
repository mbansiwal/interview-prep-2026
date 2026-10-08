package com.interview.blind75.trie;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ImplementTrieTest {

    interface Trie {
        void insert(String word);
        boolean search(String word);
        boolean startsWith(String prefix);
    }

    static Stream<Supplier<Trie>> approaches() {
        Supplier<Trie> arrayTrie = () -> {
            ImplementTrie t = new ImplementTrie();
            return new Trie() {
                public void insert(String w) { t.insert(w); }
                public boolean search(String w) { return t.search(w); }
                public boolean startsWith(String p) { return t.startsWith(p); }
            };
        };
        Supplier<Trie> hashMapTrie = () -> {
            ImplementTrie.HashMapTrie t = new ImplementTrie.HashMapTrie();
            return new Trie() {
                public void insert(String w) { t.insert(w); }
                public boolean search(String w) { return t.search(w); }
                public boolean startsWith(String p) { return t.startsWith(p); }
            };
        };
        return Stream.of(arrayTrie, hashMapTrie);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void basicOperations(Supplier<Trie> factory) {
        Trie trie = factory.get();
        trie.insert("apple");
        assertTrue(trie.search("apple"));
        assertFalse(trie.search("app"));
        assertTrue(trie.startsWith("app"));
        trie.insert("app");
        assertTrue(trie.search("app"));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void prefixNotWord(Supplier<Trie> factory) {
        Trie trie = factory.get();
        trie.insert("hello");
        assertFalse(trie.search("hell"));
        assertTrue(trie.startsWith("hell"));
        assertFalse(trie.startsWith("help"));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void notExists(Supplier<Trie> factory) {
        Trie trie = factory.get();
        assertFalse(trie.search("any"));
        assertFalse(trie.startsWith("any"));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void longerThanStoredWord(Supplier<Trie> factory) {
        Trie trie = factory.get();
        trie.insert("ab");
        assertFalse(trie.search("abc"));
        assertFalse(trie.startsWith("abc"));
        assertTrue(trie.startsWith(""));
    }
}
