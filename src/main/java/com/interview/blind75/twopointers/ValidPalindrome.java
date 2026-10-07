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
 * APPROACH: Two Pointers with Alphanumeric Skip
 * ============================================================
 * 1. Place left pointer at start, right pointer at end.
 * 2. Skip non-alphanumeric characters on both sides.
 * 3. Compare lowercased characters at l and r.
 * 4. If mismatch found, return false. Else converge inward.
 * 5. If pointers cross without mismatch, return true.
 *
 * WHY THIS WORKS:
 * Two pointers work from outside in — if each pair of corresponding
 * characters matches, the string is palindromic by definition.
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class ValidPalindrome {

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
}
