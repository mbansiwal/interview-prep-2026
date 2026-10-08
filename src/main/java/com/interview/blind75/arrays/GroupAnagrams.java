package com.interview.blind75.arrays;

import java.util.*;

/**
 * ============================================================
 * PROBLEM : Group Anagrams
 * LINK    : https://leetcode.com/problems/group-anagrams/
 * DIFFICULTY: Medium
 * PATTERN : Arrays & Hashing
 * ============================================================
 *
 * DESCRIPTION:
 * Given an array of strings strs, group the anagrams together.
 * You can return the answer in any order.
 *
 * EXAMPLES:
 *   Input : strs = ["eat","tea","tan","ate","nat","bat"]
 *   Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 *   Input : strs = [""]
 *   Output: [[""]]
 *
 * CONSTRAINTS:
 *   - 1 <= strs.length <= 10^4
 *   - 0 <= strs[i].length <= 100
 *   - strs[i] consists of lowercase English letters
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Sorted word as the key  (primary)
 *   1. For each word, sort its characters → canonical key ("eat" → "aet").
 *   2. Append the word to map[key].
 *   3. Return the map's values.
 *   Intuition: all anagrams share the same sorted form.
 *   TIME O(n · k log k) · SPACE O(n · k), k = max word length
 *
 * APPROACH 2: Letter-count signature as the key
 *   1. For each word, count its 26 letters.
 *   2. Turn the counts into a key such as "#1#0#0…" (separators avoid ambiguity).
 *   3. Group by that key.
 *   Intuition: anagrams have identical letter counts; counting avoids the sort.
 *   TIME O(n · k) · SPACE O(n · k)
 *
 * WHICH TO USE:
 *   #1 is shortest and usually accepted. Offer #2 when asked to remove the
 *   log k factor — it's faster for long words, slightly slower for short ones.
 * ============================================================
 */
public class GroupAnagrams {

    /** Approach 1 — sorted word as key. TIME O(n·k log k) · SPACE O(n·k) */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(groups.values());
    }

    /** Approach 2 — 26-letter count signature as key. TIME O(n·k) · SPACE O(n·k) */
    public List<List<String>> groupAnagramsCountKey(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String word : strs) {
            int[] counts = new int[26];
            for (char c : word.toCharArray()) counts[c - 'a']++;
            StringBuilder key = new StringBuilder();
            for (int count : counts) key.append('#').append(count);
            groups.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(groups.values());
    }
}
