package com.interview.blind75.graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * ============================================================
 * PROBLEM : Word Ladder
 * LINK    : https://leetcode.com/problems/word-ladder/
 * DIFFICULTY: Hard
 * PATTERN : BFS (Shortest Path)
 * ============================================================
 *
 * DESCRIPTION:
 * Given beginWord, endWord, and a wordList, return the number of words in the
 * shortest transformation sequence from beginWord to endWord, where each step
 * changes exactly one letter and the intermediate word must be in wordList.
 * Return 0 if no such sequence exists.
 *
 * EXAMPLES:
 *   Input : beginWord="hit", endWord="cog", wordList=["hot","dot","dog","lot","log","cog"]
 *   Output: 5  (hit→hot→dot→dog→cog)
 *
 *   Input : beginWord="hit", endWord="cog", wordList=["hot","dot","dog","lot","log"]
 *   Output: 0  (cog not in wordList)
 *
 * CONSTRAINTS:
 *   - 1 <= beginWord.length <= 10
 *   - All words have the same length and are lowercase
 *   - endWord may be absent from wordList (then the answer is 0)
 *
 * ============================================================
 * APPROACHES  (n = wordList.size(), L = word length)
 * ============================================================
 * Model: every word is a node; two words are connected if they differ by one letter.
 * The answer is the shortest path length (counting words) — so BFS.
 *
 * APPROACH 1: BFS + letter substitution
 * 1. Put wordList in a HashSet; BFS level by level from beginWord (level = sequence length).
 * 2. For each word, try every position × every letter a–z; unseen dictionary words join the next level.
 * 3. Remove words from the set when queued so they're never revisited.
 * Intuition: BFS reaches endWord first along a shortest path.
 * TIME  : O(n * L² * 26) — n words × L positions × 26 letters × O(L) to build/hash each string
 * SPACE : O(n * L)
 *
 * APPROACH 2: Bidirectional BFS
 * 1. Grow two frontiers: one from beginWord, one from endWord.
 * 2. Always expand the SMALLER frontier by one level.
 * 3. As soon as a generated word is in the other frontier, the paths meet → done.
 * Intuition: two searches of depth d/2 explore far fewer words than one of depth d
 * (roughly b^(d/2) * 2 instead of b^d for branching factor b).
 * TIME  : O(n * L² * 26) worst case — same bound, but usually dramatically faster in practice
 * SPACE : O(n * L)
 *
 * ALTERNATIVE: precompute wildcard buckets ("h*t" → [hot, hit]) to find neighbours.
 * O(n * L²) time and O(n * L²) space — wins when the alphabet is large or words are long.
 *
 * WHICH TO USE:
 * Start with BFS; offer bidirectional BFS as the optimisation (a favourite follow-up).
 * ============================================================
 */
public class WordLadder {

    /** Approach 1 — BFS + letter substitution. TIME O(n * L² * 26) · SPACE O(n * L) */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int steps = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) return steps;
                char[] chars = word.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char orig = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == orig) continue;
                        chars[j] = c;
                        String next = new String(chars);
                        if (wordSet.contains(next)) {
                            wordSet.remove(next);
                            queue.offer(next);
                        }
                    }
                    chars[j] = orig;
                }
            }
            steps++;
        }
        return 0;
    }

    /** Approach 2 — Bidirectional BFS. TIME O(n * L² * 26) worst case (much less in practice) · SPACE O(n * L) */
    public int ladderLengthBidirectional(String beginWord, String endWord, List<String> wordList) {
        Set<String> unvisited = new HashSet<>(wordList);
        if (!unvisited.contains(endWord)) return 0;
        unvisited.remove(beginWord);
        unvisited.remove(endWord);

        Set<String> front = new HashSet<>(Set.of(beginWord));
        Set<String> back = new HashSet<>(Set.of(endWord));
        int steps = 1;   // words in the sequence so far (frontiers count separately until they meet)

        while (!front.isEmpty() && !back.isEmpty()) {
            if (front.size() > back.size()) {   // always expand the smaller side
                Set<String> t = front; front = back; back = t;
            }
            Set<String> next = new HashSet<>();
            for (String word : front) {
                char[] chars = word.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char orig = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == orig) continue;
                        chars[j] = c;
                        String candidate = new String(chars);
                        if (back.contains(candidate)) return steps + 1;
                        if (unvisited.remove(candidate)) next.add(candidate);
                    }
                    chars[j] = orig;
                }
            }
            front = next;
            steps++;
        }
        return 0;
    }
}
