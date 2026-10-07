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
 * APPROACH: Sorted String as HashMap Key
 * ============================================================
 * 1. Create a HashMap where key = sorted version of a word.
 * 2. For each word, sort its characters to get a canonical key.
 * 3. Append the original word to the list at that key.
 * 4. Return all map values as the grouped result.
 *
 * WHY THIS WORKS:
 * Anagrams share the same sorted character sequence. Using sorted
 * form as a key groups them naturally without manual comparison.
 *
 * TIME  : O(n · k · log k)  where k = max word length
 * SPACE : O(n · k)
 * ============================================================
 */
public class GroupAnagrams {

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
}
