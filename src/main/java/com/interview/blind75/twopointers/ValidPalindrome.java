package com.interview.blind75.twopointers;

/**
 * ============================================================
 * PROBLEM : Valid Palindrome
 * LINK    : https://leetcode.com/problems/valid-palindrome/
 * DIFFICULTY: Easy
 * PATTERN : Two Pointers
 * ============================================================
 *
 * DESCRIPTION:
 * A phrase is a palindrome if, after converting all uppercase letters
 * to lowercase and removing all non-alphanumeric characters, it reads
 * the same forward and backward.
 *
 * EXAMPLES:
 *   Input : s = "A man, a plan, a canal: Panama"
 *   Output: true
 *
 *   Input : s = "race a car"
 *   Output: false
 *
 * CONSTRAINTS:
 *   - 1 <= s.length <= 2 * 10^5
 *   - s consists only of printable ASCII characters
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Two pointers, skipping non-alphanumerics  (primary)
 *   1. l at the start, r at the end.
 *   2. Skip characters that aren't letters or digits on each side.
 *   3. Compare lowercased characters; a mismatch → false. Move both inward.
 *   Intuition: a palindrome matches pairwise from the outside in.
 *   TIME O(n) · SPACE O(1)
 *
 * APPROACH 2: Clean, then compare with the reverse
 *   1. Build a lowercase string of only the letters and digits.
 *   2. It's a palindrome iff it equals its reverse.
 *   Intuition: removes the skipping logic at the cost of a copy.
 *   TIME O(n) · SPACE O(n)
 *
 * WHICH TO USE:
 *   #2 is quickest to write; interviewers then ask for O(1) space → #1.
 * ============================================================
 */
public class ValidPalindrome {

    /** Approach 1 — two pointers in place. TIME O(n) · SPACE O(1) */
    public boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r--;
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    /** Approach 2 — build cleaned string, compare with its reverse. TIME O(n) · SPACE O(n) */
    public boolean isPalindromeCleanReverse(String s) {
        StringBuilder clean = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) clean.append(Character.toLowerCase(c));
        }
        String forward = clean.toString();
        return forward.equals(clean.reverse().toString());
    }
}
