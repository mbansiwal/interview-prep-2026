package com.interview.blind75.arrays;

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
 * APPROACH: Frequency Count Array
 * ============================================================
 * 1. If lengths differ, they can't be anagrams — return false early.
 * 2. Create an int[26] frequency array (a=0, b=1, ..., z=25).
 * 3. Increment count for each character in s.
 * 4. Decrement count for each character in t.
 * 5. If all counts are zero, t is an anagram of s.
 *
 * WHY THIS WORKS:
 * An anagram has identical character frequencies. Incrementing for s
 * and decrementing for t means any mismatch leaves a non-zero count.
 *
 * TIME  : O(n)
 * SPACE : O(1) — fixed 26-element array
 * ============================================================
 */
public class ValidAnagram {

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
}
