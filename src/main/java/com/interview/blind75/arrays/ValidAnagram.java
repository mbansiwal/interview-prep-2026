package com.interview.blind75.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * PROBLEM : Valid Anagram
 * LINK    : https://leetcode.com/problems/valid-anagram/
 * DIFFICULTY: Easy
 * PATTERN : Arrays & Hashing
 * ============================================================
 *
 * DESCRIPTION:
 * Given two strings s and t, return true if t is an anagram of s,
 * and false otherwise. An anagram uses the exact same characters
 * in the same frequencies, just rearranged.
 *
 * EXAMPLES:
 *   Input : s = "anagram", t = "nagaram"
 *   Output: true
 *
 *   Input : s = "rat", t = "car"
 *   Output: false
 *
 * CONSTRAINTS:
 *   - 1 <= s.length, t.length <= 5 * 10^4
 *   - s and t consist of lowercase English letters
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Frequency count array  (primary)
 *   1. If lengths differ → false.
 *   2. int[26]: +1 for each char of s, −1 for each char of t.
 *   3. Anagram iff every count ends at 0.
 *   Intuition: anagrams have identical letter frequencies.
 *   TIME O(n) · SPACE O(1) — fixed 26 counters
 *
 * APPROACH 2: Sort both strings
 *   1. Sort the characters of s and of t.
 *   2. Anagram iff the sorted arrays are equal.
 *   Intuition: sorting gives every anagram the same canonical form.
 *   TIME O(n log n) · SPACE O(n) for the char arrays
 *
 * APPROACH 3: HashMap counts (Unicode follow-up)
 *   1. Same as #1, but count in a HashMap<Integer, Integer> keyed by code point.
 *   Intuition: works for any alphabet, not just 'a'–'z'.
 *   TIME O(n) · SPACE O(k), k = distinct characters
 *
 * WHICH TO USE:
 *   Lead with #1. #2 is the simplest to write. #3 answers the common
 *   follow-up "what if the input contains Unicode characters?"
 * ============================================================
 */
public class ValidAnagram {

    /** Approach 1 — int[26] frequency count. TIME O(n) · SPACE O(1) */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;
        for (char c : t.toCharArray()) freq[c - 'a']--;

        for (int count : freq) {
            if (count != 0) return false;
        }
        return true;
    }

    /** Approach 2 — sort both strings and compare. TIME O(n log n) · SPACE O(n) */
    public boolean isAnagramSorting(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }

    /** Approach 3 — HashMap counts by code point (any alphabet). TIME O(n) · SPACE O(k) */
    public boolean isAnagramHashMap(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Integer, Integer> counts = new HashMap<>();
        s.codePoints().forEach(cp -> counts.merge(cp, 1, Integer::sum));
        for (int cp : t.codePoints().toArray()) {
            int left = counts.getOrDefault(cp, 0) - 1;
            if (left < 0) return false;
            counts.put(cp, left);
        }
        return true;
    }
}
