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
 * APPROACHES
 * ============================================================
 * APPROACH 1: Sliding window with need/have counters (HashMap)  (primary)
 *   1. need = counts of t. Expand right; when a char's window count reaches its
 *      need, have++.
 *   2. While have == required, record the window and shrink from the left.
 *   Intuition: expand until valid, then shrink while still valid — every
 *   minimal window is visited.
 *   TIME O(n + m), n = |s|, m = |t| · SPACE O(k), k = distinct chars (≤ 52)
 *
 * APPROACH 2: Same window with an int[128] array and a "missing" counter
 *   1. need[c] = count in t; missing = |t|.
 *   2. Expand: if need[c] > 0 before decrementing, missing--. need[c]--.
 *   3. When missing == 0, shrink while need[s[left]] < 0 (surplus), record,
 *      then drop s[left] and set missing++ to move on.
 *   Intuition: one array stores need − have, so positive means "still needed".
 *   TIME O(n + m) · SPACE O(1) — 128 slots
 *
 * WHICH TO USE:
 *   #1 is easiest to explain; #2 is the tighter implementation with smaller
 *   constants (no boxing). Both are optimal. Brute force over all substrings
 *   is O(n² · alphabet) or worse.
 * ============================================================
 */
public class MinWindowSubstring {

    /** Approach 1 — HashMap need/have window. TIME O(n + m) · SPACE O(k) */
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

    /** Approach 2 — int[128] balance + missing counter. TIME O(n + m) · SPACE O(1) */
    public String minWindowArray(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) return "";
        int[] need = new int[128];
        for (char c : t.toCharArray()) need[c]++;

        int missing = t.length(), left = 0, bestLeft = 0, bestLen = Integer.MAX_VALUE;
        for (int right = 0; right < s.length(); right++) {
            if (need[s.charAt(right)]-- > 0) missing--;
            if (missing == 0) {
                while (need[s.charAt(left)] < 0) need[s.charAt(left++)]++; // drop surplus chars
                if (right - left + 1 < bestLen) {
                    bestLen = right - left + 1;
                    bestLeft = left;
                }
                need[s.charAt(left++)]++; // give up one required char, look for the next window
                missing++;
            }
        }
        return bestLen == Integer.MAX_VALUE ? "" : s.substring(bestLeft, bestLeft + bestLen);
    }
}
