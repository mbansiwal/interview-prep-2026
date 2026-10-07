package com.interview.blind75.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * PROBLEM : Minimum Window Substring
 * LINK    : https://leetcode.com/problems/minimum-window-substring/
 * DIFFICULTY: Hard
 * PATTERN : Sliding Window
 * ============================================================
 *
 * DESCRIPTION:
 * Given strings s and t, return the minimum window substring of s
 * such that every character in t (including duplicates) is included.
 * Return empty string "" if no such window exists.
 *
 * EXAMPLES:
 *   Input : s = "ADOBECODEBANC", t = "ABC"
 *   Output: "BANC"
 *
 *   Input : s = "a", t = "a"
 *   Output: "a"
 *
 * CONSTRAINTS:
 *   - 1 <= s.length, t.length <= 10^5
 *   - s and t consist of uppercase and lowercase English letters
 *
 * ============================================================
 * APPROACH: Sliding Window with Need/Have Counters
 * ============================================================
 * 1. Build a frequency map (need) for characters in t.
 * 2. Expand right pointer, updating window frequency map.
 * 3. When a char's window freq matches its needed freq, increment 'have'.
 * 4. When have == required (all chars satisfied), try shrinking from left.
 * 5. Track minimum window across all valid states.
 *
 * WHY THIS WORKS:
 * We greedily expand to satisfy all requirements, then shrink to minimize
 * while maintaining validity. The have/required counters avoid re-scanning.
 *
 * TIME  : O(n + m) where n = |s|, m = |t| — each pointer moves at most n times
 * SPACE : O(k) where k = distinct characters (at most 52 letters)
 * ============================================================
 */
public class MinWindowSubstring {

    public String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) return "";

        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) need.merge(c, 1, Integer::sum);

        Map<Character, Integer> window = new HashMap<>();
        int have = 0, required = need.size();
        int left = 0, minLen = Integer.MAX_VALUE, minLeft = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            window.merge(c, 1, Integer::sum);
            if (need.containsKey(c) && window.get(c).equals(need.get(c))) have++;

            while (have == required) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }
                char leftChar = s.charAt(left);
                window.merge(leftChar, -1, Integer::sum);
                if (need.containsKey(leftChar) && window.get(leftChar) < need.get(leftChar)) have--;
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }
}
