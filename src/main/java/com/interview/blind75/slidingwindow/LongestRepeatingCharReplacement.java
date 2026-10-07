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
 * APPROACH: Sliding Window with Max Frequency Tracking
 * ============================================================
 * 1. Maintain frequency array of characters in current window.
 * 2. Track maxFreq = most frequent char in window.
 * 3. Window is valid if (windowSize - maxFreq) <= k.
 *    (Replacements needed = total - most_frequent_count)
 * 4. If invalid, shrink from left (decrement freq of left char).
 *
 * WHY THIS WORKS:
 * A window can be made all-one-letter iff (windowSize - maxFreq) <= k:
 * keep the most common letter, replace everything else.
 *
 * Subtle point (interviewers ask this): we never lower maxFreq when the
 * left side shrinks, so it can be "stale" (too high). That's safe because
 * a window of size L needs maxFreq >= L - k to beat our best answer.
 * The answer can only grow when maxFreq itself reaches a new high, so a
 * stale maxFreq never lets an invalid window be counted as a new best.
 *
 * TIME  : O(n)
 * SPACE : O(1) — fixed 26-char array
 * ============================================================
 */
public class LongestRepeatingCharReplacement {

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
}
