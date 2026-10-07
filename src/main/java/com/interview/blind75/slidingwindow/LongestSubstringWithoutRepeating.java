package com.interview.blind75.slidingwindow;

import java.util.HashMap;
import java.util.Map;

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
 * APPROACH: Sliding Window with Last-Seen Index Map
 * ============================================================
 * 1. Maintain a HashMap of char → last seen index.
 * 2. Keep a left pointer marking the start of valid window.
 * 3. For each char at index i:
 *    - If it was seen at index >= left, move left to (lastSeen + 1).
 *    - Update char's last-seen index.
 *    - Update maxLength with window size (i - left + 1).
 *
 * WHY THIS WORKS:
 * Jumping left past the last occurrence avoids slow character-by-character
 * shrinking. Window always holds distinct characters.
 *
 * TIME  : O(n)
 * SPACE : O(min(n, alphabet))
 * ============================================================
 */
public class LongestSubstringWithoutRepeating {

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
}
