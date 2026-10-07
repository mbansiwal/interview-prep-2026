package com.interview.blind75.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ============================================================
 * PROBLEM : Word Break
 * LINK    : https://leetcode.com/problems/word-break/
 * DIFFICULTY: Medium
 * PATTERN : Dynamic Programming (1D)
 * ============================================================
 *
 * DESCRIPTION:
 * Given a string s and a dictionary wordDict, return true if s can be
 * segmented into a space-separated sequence of one or more dictionary words.
 * The same word may be reused.
 *
 * EXAMPLES:
 *   Input : s = "leetcode", wordDict = ["leet","code"]  →  Output: true
 *   Input : s = "applepenapple", wordDict = ["apple","pen"]  →  Output: true
 *   Input : s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]  →  Output: false
 *
 * CONSTRAINTS:
 *   - 1 <= s.length <= 300
 *   - 1 <= wordDict[i].length <= 20
 *
 * ============================================================
 * APPROACH: DP — dp[i] = can s[0..i-1] be segmented?
 * ============================================================
 * 1. dp[0] = true (the empty prefix is trivially segmentable).
 * 2. For each end position i from 1 to n, try each start j where the last
 *    word s[j..i-1] is no longer than the longest dictionary word (L).
 * 3. If dp[j] is true AND s[j..i-1] is in the dictionary → dp[i] = true.
 *
 * WHY THIS WORKS:
 * dp[i] asks "can we reach position i?" We reach i only from an earlier
 * reachable position j whose following chunk is a dictionary word. No word is
 * longer than L, so starts further back than i-L can never match — skip them.
 *
 * TIME  : O(n · L²) — n end positions × L starts × O(L) substring + hash
 *         (without the L bound it is O(n³))
 * SPACE : O(n + total dictionary chars)
 * ============================================================
 */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int maxWordLen = 0;
        for (String word : wordDict) maxWordLen = Math.max(maxWordLen, word.length());

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = Math.max(0, i - maxWordLen); j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
