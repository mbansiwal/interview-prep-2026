package com.interview.blind75.graphs;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AlienDictionaryTest {

    private final AlienDictionary solution = new AlienDictionary();

    private static void assertConsistent(List<String> words, String order) {
        assertFalse(order.isEmpty());
        for (int i = 0; i + 1 < words.size(); i++) {
            String a = words.get(i), b = words.get(i + 1);
            int j = 0;
            while (j < Math.min(a.length(), b.length()) && a.charAt(j) == b.charAt(j)) j++;
            if (j < Math.min(a.length(), b.length())) {
                assertTrue(order.indexOf(a.charAt(j)) < order.indexOf(b.charAt(j)),
                        a.charAt(j) + " must come before " + b.charAt(j) + " in " + order);
            }
        }
    }

    @Test
    void example1() {
        List<String> words = List.of("wrt", "wrf", "er", "ett", "rftt");
        String order = solution.alienOrder(words);
        assertEquals("wertf", order);
        assertConsistent(words, order);
    }

    @Test
    void cycleReturnsEmpty() {
        assertEquals("", solution.alienOrder(List.of("z", "x", "z")));
    }

    @Test
    void prefixAfterLongerWordIsInvalid() {
        assertEquals("", solution.alienOrder(List.of("abc", "ab")));
    }

    @Test
    void singleWordReturnsAllItsLetters() {
        String order = solution.alienOrder(List.of("zx"));
        assertEquals(2, order.length());
        assertTrue(order.contains("z") && order.contains("x"));
    }
}
