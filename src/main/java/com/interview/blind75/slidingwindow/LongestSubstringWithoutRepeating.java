package com.interview.blind75.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * ============================================================
 * PROBLEM : Longest Substring Without Repeating Characters
 * LINK    : https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * DIFFICULTY: Medium
 * PATTERN : Sliding Window
 * ============================================================
 *
 * DESCRIPTION:
 * Given a string s, find the length of the longest substring without
 * repeating characters.
 *
 * EXAMPLES:
 *   Input : s = "abcabcbb"
 *   Output: 3  (substring "abc")
 *
 *   Input : s = "bbbbb"
 *   Output: 1  (substring "b")
 *
 * CONSTRAINTS:
 *   - 0 <= s.length <= 5 * 10^4
 *   - s consists of English letters, digits, symbols, spaces
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Sliding window with last-seen index map  (primary)
 *   1. Map each character to the last index where it appeared.
 *   2. If the current char was seen inside the window, jump left past it.
 *   3. Window length i − left + 1; track the max.
 *   Intuition: jumping straight past the repeat means left never moves backward
 *   and never steps one at a time.
 *   TIME O(n) · SPACE O(min(n, alphabet))
 *
 * APPROACH 2: Same idea with an int[128] array
 *   1. lastIndex[c] = last position of c + 1 (0 means "not seen").
 *   2. left = max(left, lastIndex[c]); update the best length.
 *   Intuition: identical logic; an array is faster than a HashMap for ASCII input.
 *   TIME O(n) · SPACE O(1) — 128 slots (ASCII per constraints)
 *
 * APPROACH 3: Sliding window with a HashSet, shrink one step at a time
 *   1. Expand right; while s[right] is already in the set, remove s[left++].
 *   2. Add s[right]; track the window size.
 *   Intuition: the most direct "grow until invalid, shrink until valid" window.
 *   TIME O(n) — each char added and removed at most once (≤ 2n steps) · SPACE O(min(n, alphabet))
 *
 * WHICH TO USE:
 *   #3 is easiest to derive on a whiteboard; #1/#2 improve it by jumping left
 *   directly. Say "#2 for ASCII, #1 for arbitrary Unicode".
 * ============================================================
 */
public class LongestSubstringWithoutRepeating {

    /** Approach 1 — last-seen index map, jump left. TIME O(n) · SPACE O(min(n, alphabet)) */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastSeen = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (lastSeen.containsKey(c) && lastSeen.get(c) >= left) {
                left = lastSeen.get(c) + 1;
            }
            lastSeen.put(c, i);
            maxLength = Math.max(maxLength, i - left + 1);
        }
        return maxLength;
    }

    /** Approach 2 — int[128] last index (ASCII). TIME O(n) · SPACE O(1) */
    public int lengthOfLongestSubstringArray(String s) {
        int[] nextAllowedLeft = new int[128]; // last index of char + 1; 0 = unseen
        int best = 0, left = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            left = Math.max(left, nextAllowedLeft[c]);
            best = Math.max(best, i - left + 1);
            nextAllowedLeft[c] = i + 1;
        }
        return best;
    }

    /** Approach 3 — HashSet window, shrink step by step. TIME O(n) (≤ 2n steps) · SPACE O(min(n, alphabet)) */
    public int lengthOfLongestSubstringSet(String s) {
        Set<Character> window = new HashSet<>();
        int best = 0, left = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            while (window.contains(c)) window.remove(s.charAt(left++));
            window.add(c);
            best = Math.max(best, right - left + 1);
        }
        return best;
    }
}
