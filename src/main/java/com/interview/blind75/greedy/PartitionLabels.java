package com.interview.blind75.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * PROBLEM : Partition Labels
 * LINK    : https://leetcode.com/problems/partition-labels/
 * DIFFICULTY: Medium
 * PATTERN : Greedy
 * ============================================================
 *
 * DESCRIPTION:
 * Given a string s, partition it so that each letter appears in at most one
 * part. Return a list of integers representing the size of these parts.
 *
 * EXAMPLES:
 *   Input : "ababcbacadefegdehijhklij"  →  Output: [9,7,8]
 *   Input : "eccbbbbdec"                →  Output: [10]
 *
 * CONSTRAINTS:
 *   - 1 <= s.length <= 500
 *
 * ============================================================
 * APPROACH 1: Greedy — last occurrence of each character  → partitionLabels
 * ============================================================
 * 1. Record the last index of every character.
 * 2. Scan left to right, tracking `end` (max last-index seen so far).
 * 3. When i == end, we've reached the boundary → add partition size, reset.
 *
 * WHY THIS WORKS:
 * A partition is valid only if it includes every occurrence of every character
 * within it. Expanding `end` to cover all occurrences ensures this, and cutting
 * as soon as i == end gives the most (smallest) parts.
 *
 * TIME  : O(n)
 * SPACE : O(1) — 26-entry array (output list not counted)
 *
 * ALTERNATIVES: treating each letter's [first, last] as an interval and merging
 * overlaps (Merge Intervals) gives the same answer in O(n) — it's the same idea
 * with more code; no materially better alternative.
 * ============================================================
 */
public class PartitionLabels {

    /** Approach 1 — Greedy using last occurrences. TIME O(n) · SPACE O(1) */
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) last[s.charAt(i) - 'a'] = i;

        List<Integer> result = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                result.add(end - start + 1);
                start = i + 1;
            }
        }
        return result;
    }
}
