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
 * APPROACH: BFS + Letter Substitution
 * ============================================================
 * 1. Convert wordList to HashSet for O(1) lookup.
 * 2. BFS: level by level (each level = one transformation).
 * 3. For each word in current level, try replacing each character with a-z.
 * 4. If the new word is in wordList, add to queue and remove from set (visited).
 * 5. Return level count when endWord is reached.
 *
 * WHY THIS WORKS:
 * BFS guarantees the shortest path. Removing from wordSet prevents revisits.
 *
 * TIME  : O(n * L² * 26) — n words × L positions × 26 letters × O(L) to build/hash each string
 * SPACE : O(n * L)
 *
 * FOLLOW-UP: Bidirectional BFS — expand from both beginWord and endWord,
 * always growing the smaller frontier; meets in the middle and explores far fewer words.
 * ============================================================
 */
public class WordLadder {

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
}
