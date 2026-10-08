package com.interview.blind75.dp;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
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
 * APPROACHES
 * ============================================================
 * All three treat position i as "s[0..i-1] is segmentable" and only try word
 * lengths up to L = longest dictionary word. Plain backtracking without memo is
 * exponential on inputs like "aaaa…ab". (n = s.length, W = total dictionary chars.)
 *
 * APPROACH 1: Bottom-up DP over end positions              → wordBreak
 *   1. dp[0] = true (empty prefix).
 *   2. For each end i, try starts j in [i-L, i): if dp[j] and s[j..i) is a word → dp[i] = true.
 *   Intuition: we can reach i only from a reachable j followed by a dictionary word.
 *   TIME  : O(n · L²) — n ends × L starts × O(L) substring + hash
 *   SPACE : O(n + W)
 *
 * APPROACH 2: Top-down DFS + memo on start index           → wordBreakMemo
 *   1. canBreak(start) = true if start == n, else any word s[start..end) with
 *      canBreak(end).
 *   2. Cache each start's result.
 *   Intuition: the backtracking solution with each suffix solved once.
 *   TIME  : O(n · L²)    SPACE : O(n + W) + O(n) recursion stack
 *
 * APPROACH 3: BFS over start indices                       → wordBreakBfs
 *   1. Queue starts at index 0; from each start, enqueue every end where
 *      s[start..end) is a word.
 *   2. A visited[] array expands each index once; reaching n means true.
 *   Intuition: indices are nodes, dictionary words are edges — is n reachable from 0?
 *   TIME  : O(n · L²)    SPACE : O(n + W)
 *
 * WHICH TO USE:
 * Approach 1 is the standard answer; Approach 2 is the natural first idea and the
 * memo is what interviewers want to see you add. Mention a Trie over the
 * dictionary to avoid building substrings (O(n · L) character steps).
 * ============================================================
 */
public class WordBreak {

    /** Approach 1 — Bottom-up DP over end positions. TIME O(n·L²) · SPACE O(n + W) */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int maxWordLen = maxLength(wordDict);

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

    /** Approach 2 — Top-down DFS + memo on start index. TIME O(n·L²) · SPACE O(n + W) */
    public boolean wordBreakMemo(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        return canBreak(s, 0, wordSet, maxLength(wordDict), new Boolean[s.length()]);
    }

    private boolean canBreak(String s, int start, Set<String> words, int maxLen, Boolean[] memo) {
        if (start == s.length()) return true;
        if (memo[start] != null) return memo[start];
        int limit = Math.min(s.length(), start + maxLen);
        for (int end = start + 1; end <= limit; end++) {
            if (words.contains(s.substring(start, end)) && canBreak(s, end, words, maxLen, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }

    /** Approach 3 — BFS over start indices. TIME O(n·L²) · SPACE O(n + W) */
    public boolean wordBreakBfs(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int maxLen = maxLength(wordDict), n = s.length();
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            int start = queue.poll();
            int limit = Math.min(n, start + maxLen);
            for (int end = start + 1; end <= limit; end++) {
                if (!visited[end] && wordSet.contains(s.substring(start, end))) {
                    if (end == n) return true;
                    visited[end] = true;
                    queue.offer(end);
                }
            }
        }
        return false;
    }

    private int maxLength(List<String> words) {
        int max = 0;
        for (String word : words) max = Math.max(max, word.length());
        return max;
    }
}
