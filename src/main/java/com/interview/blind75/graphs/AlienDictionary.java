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
 * APPROACHES  (C = total characters, U = unique letters ≤ 26)
 * ============================================================
 * Shared step — build the letter graph:
 * 1. Every letter that appears is a node.
 * 2. For each pair of ADJACENT words, the first position where they differ gives one rule:
 *    first[j] comes before second[j]. Only the first difference matters.
 * 3. If no difference is found and the first word is longer ("abc" before "ab") → invalid → "".
 * Then the alphabet is any topological order of the graph (a cycle → "").
 *
 * APPROACH 1: Kahn's algorithm (BFS on in-degrees)
 * 1. Queue every letter with in-degree 0; pop one, append it, decrement its successors,
 *    queue any that drop to 0.
 * 2. If fewer letters were emitted than exist, there was a cycle → "".
 * Intuition: emit a letter only once every letter that must precede it is out.
 * TIME  : O(C) — building the graph dominates; the sort itself is O(U + edges)
 * SPACE : O(U + min(U², N)) — O(1) for a fixed 26-letter alphabet
 *
 * APPROACH 2: DFS post-order (3-colour)
 * 1. DFS each letter along before → after edges; a grey (on-stack) letter again means a cycle → "".
 * 2. Append a letter after all its successors are finished, then REVERSE the list.
 * Intuition: post-order lists a letter after everything that must come after it,
 * so reversing gives "before" letters first.
 * TIME  : O(C)
 * SPACE : O(U + min(U², N)) + O(U) recursion depth
 *
 * WHICH TO USE:
 * Kahn's is the usual answer — cycle detection is just a count check and it's iterative.
 * DFS is equally optimal; remember the final reverse. The real traps are the prefix rule
 * and "only the first differing letter counts".
 * ============================================================
 */
public class AlienDictionary {

    /** Approach 1 — Letter graph + Kahn's BFS. TIME O(C) · SPACE O(U + min(U², N)) */
    public String alienOrder(List<String> words) {
        Map<Character, Set<Character>> graph = buildGraph(words);
        if (graph == null) return "";

        Map<Character, Integer> inDegree = new HashMap<>();
        for (char c : graph.keySet()) inDegree.putIfAbsent(c, 0);
        for (Set<Character> successors : graph.values()) {
            for (char next : successors) inDegree.merge(next, 1, Integer::sum);
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

        return order.length() == graph.size() ? order.toString() : "";
    }

    /** Approach 2 — Letter graph + DFS post-order. TIME O(C) · SPACE O(U + min(U², N)) */
    public String alienOrderDfs(List<String> words) {
        Map<Character, Set<Character>> graph = buildGraph(words);
        if (graph == null) return "";

        Map<Character, Integer> state = new HashMap<>();   // absent = unvisited, 1 = on stack, 2 = done
        StringBuilder postOrder = new StringBuilder();
        for (char c : graph.keySet()) {
            if (!dfs(c, graph, state, postOrder)) return "";
        }
        return postOrder.reverse().toString();
    }

    private boolean dfs(char c, Map<Character, Set<Character>> graph,
                        Map<Character, Integer> state, StringBuilder postOrder) {
        Integer s = state.get(c);
        if (s != null) return s == 2;   // 1 means we're back on the current path → cycle
        state.put(c, 1);
        for (char next : graph.get(c)) {
            if (!dfs(next, graph, state, postOrder)) return false;
        }
        state.put(c, 2);
        postOrder.append(c);
        return true;
    }

    // Returns letter → set of letters that must come after it, or null if the input is invalid.
    private Map<Character, Set<Character>> buildGraph(List<String> words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) graph.putIfAbsent(c, new HashSet<>());
        }
        for (int i = 0; i + 1 < words.size(); i++) {
            String first = words.get(i);
            String second = words.get(i + 1);
            int len = Math.min(first.length(), second.length());
            int j = 0;
            while (j < len && first.charAt(j) == second.charAt(j)) j++;

            if (j == len) {
                if (first.length() > second.length()) return null;
                continue;
            }
            graph.get(first.charAt(j)).add(second.charAt(j));
        }
        return graph;
    }
}
