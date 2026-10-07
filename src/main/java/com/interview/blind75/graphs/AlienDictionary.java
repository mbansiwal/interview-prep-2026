package com.interview.blind75.graphs;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * ============================================================
 * PROBLEM : Alien Dictionary
 * LINK    : https://leetcode.com/problems/alien-dictionary/ (Premium)
 *           https://neetcode.io/problems/foreign-dictionary
 * DIFFICULTY: Hard
 * PATTERN : Topological Sort (BFS / Kahn's Algorithm)
 * ============================================================
 *
 * DESCRIPTION:
 * An alien language uses lowercase English letters in an unknown order.
 * Given a list of words sorted lexicographically by that alien order,
 * return a string of its letters in a valid order. Return "" if no valid
 * order exists. Any valid order is accepted.
 *
 * EXAMPLES:
 *   Input : words = ["wrt","wrf","er","ett","rftt"]
 *   Output: "wertf"
 *
 *   Input : words = ["z","x","z"]
 *   Output: ""   (z<x and x<z is a cycle)
 *
 *   Input : words = ["abc","ab"]
 *   Output: ""   (a longer word cannot come before its own prefix)
 *
 * CONSTRAINTS:
 *   - 1 <= words.length <= 100
 *   - 1 <= words[i].length <= 100
 *
 * ============================================================
 * APPROACH: Build letter graph from adjacent words, then Kahn's BFS
 * ============================================================
 * 1. Every letter that appears is a node with in-degree 0.
 * 2. Compare each pair of ADJACENT words. The first position where they
 *    differ gives one rule: first[j] comes before second[j] (an edge).
 *    Only the first difference matters — later letters tell us nothing.
 * 3. If no difference is found and the first word is longer
 *    ("abc" before "ab"), the input is invalid → return "".
 * 4. Kahn's BFS: start with all in-degree-0 letters, pop one, append it,
 *    decrement its neighbours' in-degree, push any that drop to 0.
 * 5. If we output fewer letters than exist, there was a cycle → return "".
 *
 * WHY THIS WORKS:
 * The sorted list gives "a before b" rules; a valid alphabet is exactly a
 * topological order of those rules. Kahn's algorithm emits a letter only
 * once every letter that must precede it has been emitted.
 *
 * TIME  : O(C) — C = total characters across all words
 * SPACE : O(U + min(U², N)) — U unique letters (≤ 26), N words; i.e. O(1) for a fixed alphabet
 * ============================================================
 */
public class AlienDictionary {

    public String alienOrder(List<String> words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                inDegree.putIfAbsent(c, 0);
            }
        }

        for (int i = 0; i + 1 < words.size(); i++) {
            String first = words.get(i);
            String second = words.get(i + 1);
            int len = Math.min(first.length(), second.length());
            int j = 0;
            while (j < len && first.charAt(j) == second.charAt(j)) j++;

            if (j == len) {
                if (first.length() > second.length()) return "";
                continue;
            }
            char before = first.charAt(j);
            char after = second.charAt(j);
            if (graph.get(before).add(after)) {
                inDegree.merge(after, 1, Integer::sum);
            }
        }

        Queue<Character> queue = new ArrayDeque<>();
        for (Map.Entry<Character, Integer> e : inDegree.entrySet()) {
            if (e.getValue() == 0) queue.offer(e.getKey());
        }

        StringBuilder order = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            order.append(c);
            for (char next : graph.get(c)) {
                if (inDegree.merge(next, -1, Integer::sum) == 0) queue.offer(next);
            }
        }

        return order.length() == inDegree.size() ? order.toString() : "";
    }
}
