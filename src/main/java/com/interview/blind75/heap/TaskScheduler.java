package com.interview.blind75.heap;

/**
 * ============================================================
 * PROBLEM : Task Scheduler
 * LINK    : https://leetcode.com/problems/task-scheduler/
 * DIFFICULTY: Medium
 * PATTERN : Heap / Greedy
 * ============================================================
 *
 * DESCRIPTION:
 * Given a list of tasks represented by letters and a cooldown n,
 * find the minimum intervals required to execute all tasks where the
 * same task must be at least n intervals apart.
 *
 * EXAMPLES:
 *   Input : tasks = ["A","A","A","B","B","B"], n = 2
 *   Output: 8  (A→B→idle→A→B→idle→A→B)
 *
 *   Input : tasks = ["A","A","A","B","B","B"], n = 0
 *   Output: 6
 *
 * CONSTRAINTS:
 *   - 1 <= tasks.length <= 10^4
 *
 * ============================================================
 * APPROACH: Math Formula with Max Frequency
 * ============================================================
 * 1. Count frequency of each task. Find maxFreq and how many tasks have maxFreq.
 * 2. result = max(tasks.length, (maxFreq-1)*(n+1) + countMaxFreq)
 *
 * WHY THIS WORKS:
 * (maxFreq-1) full "cycles" of length (n+1), plus a final row of countMaxFreq.
 * If tasks fill all gaps, total = tasks.length (no idle needed).
 *
 * TIME  : O(n) — n = number of tasks
 * SPACE : O(1) — at most 26 frequencies
 * ============================================================
 */
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char task : tasks) freq[task - 'A']++;

        int maxFreq = 0;
        for (int f : freq) maxFreq = Math.max(maxFreq, f);

        int countMaxFreq = 0;
        for (int f : freq) if (f == maxFreq) countMaxFreq++;

        int minIntervals = (maxFreq - 1) * (n + 1) + countMaxFreq;
        return Math.max(minIntervals, tasks.length);
    }
}
