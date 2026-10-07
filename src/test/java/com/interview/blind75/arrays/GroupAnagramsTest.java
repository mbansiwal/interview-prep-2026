package com.interview.blind75.arrays;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class GroupAnagramsTest {

    private final GroupAnagrams solution = new GroupAnagrams();

    @Test
    void standardExample() {
        List<List<String>> result = solution.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        assertEquals(normalize(List.of(List.of("bat"), List.of("nat", "tan"), List.of("ate", "eat", "tea"))),
                normalize(result));
    }

    private static List<List<String>> normalize(List<List<String>> groups) {
        List<List<String>> out = new ArrayList<>();
        for (List<String> g : groups) {
            List<String> copy = new ArrayList<>(g);
            Collections.sort(copy);
            out.add(copy);
        }
        out.sort(Comparator.comparing((List<String> g) -> g.get(0)));
        return out;
    }

    @Test
    void emptyString() {
        List<List<String>> result = solution.groupAnagrams(new String[]{""});
        assertEquals(1, result.size());
        assertEquals(List.of(""), result.get(0));
    }

    @Test
    void singleChar() {
        List<List<String>> result = solution.groupAnagrams(new String[]{"a"});
        assertEquals(1, result.size());
    }

    @Test
    void allAnagrams() {
        List<List<String>> result = solution.groupAnagrams(new String[]{"abc", "bca", "cab"});
        assertEquals(1, result.size());
        assertEquals(3, result.get(0).size());
    }
}
