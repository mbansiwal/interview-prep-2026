package com.interview.blind75.slidingwindow;

/**
 * ============================================================
 * PROBLEM : Longest Repeating Character Replacement
 * LINK    : https://leetcode.com/problems/longest-repeating-character-replacement/
 * DIFFICULTY: Medium
 * PATTERN : Sliding Window
 * ============================================================
 *
 * DESCRIPTION:
 * Given a string s and integer k, you can replace at most k characters
 * in the string. Return the length of the longest substring containing
 * all the same letter after at most k replacements.
 *
 * EXAMPLES:
 *   Input : s = "ABAB", k = 2
 *   Output: 4
 *
 *   Input : s = "AABABBA", k = 1
 *   Output: 4
 *
 * CONSTRAINTS:
 *   - 1 <= s.length <= 10^5
 *   - 0 <= k <= s.length
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Sliding window with a never-decreasing maxFreq  (primary)
 *   1. Expand right, counting letters; maxFreq = highest count seen in any window.
 *   2. While (window length − maxFreq) > k, shrink from the left.
 *   3. Track the largest window.
 *   Intuition: the answer only grows when maxFreq grows, so a stale (too high)
 *   maxFreq can never make an invalid window beat the best answer.
 *   TIME O(n) · SPACE O(1) — 26 counters
 *
 * APPROACH 2: Sliding window, recompute the true max each step
 *   1. Same window, but after every change scan all 26 counts for the real max.
 *   2. Shrink while (length − realMax) > k.
 *   Intuition: easier to trust and explain — no "stale max" argument needed.
 *   TIME O(26 · n) = O(n) · SPACE O(1)
 *
 * WHICH TO USE:
 *   Start with #2 (obviously correct), then optimise to #1 and explain why the
 *   stale maxFreq is safe — that explanation is what interviewers look for.
 * ============================================================
 */
public class LongestRepeatingCharReplacement {

    /** Approach 1 — window with never-decreasing maxFreq. TIME O(n) · SPACE O(1) */
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26];
        int maxFreq = 0, left = 0, maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            freq[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);

            while ((right - left + 1) - maxFreq > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    /** Approach 2 — window, recompute the real max over 26 letters. TIME O(26·n) · SPACE O(1) */
    public int characterReplacementRecountMax(String s, int k) {
        int[] freq = new int[26];
        int left = 0, best = 0;
        for (int right = 0; right < s.length(); right++) {
            freq[s.charAt(right) - 'A']++;
            while ((right - left + 1) - max(freq) > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    private int max(int[] freq) {
        int m = 0;
        for (int f : freq) m = Math.max(m, f);
        return m;
    }
}
