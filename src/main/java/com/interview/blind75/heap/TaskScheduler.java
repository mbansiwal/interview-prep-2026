package com.interview.blind75.heap;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.PriorityQueue;

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
 * APPROACHES  (T = number of tasks)
 * ============================================================
 * APPROACH 1: Math formula on the most frequent task
 * 1. Count each task; find maxFreq and how many tasks share it (countMaxFreq).
 * 2. answer = max(T, (maxFreq - 1) * (n + 1) + countMaxFreq).
 * Intuition: the most frequent task forms (maxFreq - 1) "frames" of length n + 1 plus a
 * final partial frame; other tasks fill the gaps, and if they overflow there's no idle at all.
 * TIME  : O(T)
 * SPACE : O(1) — 26 counters
 *
 * APPROACH 2: Max-heap + cooldown queue (simulation)
 * 1. Max-heap of remaining counts; queue of (count, readyAtTime) for tasks cooling down.
 * 2. Each step run the task with the most remaining work; if it still has work, queue it
 *    until time + n + 1.
 * 3. If nothing is runnable, jump the clock straight to the next ready time (that gap is idle).
 * Intuition: always run the task with the most work left, so it never becomes the bottleneck.
 * TIME  : O(T log 26) = O(T) (idle stretches are skipped in one jump)
 * SPACE : O(26) = O(1)
 *
 * WHICH TO USE:
 * The formula is optimal and short, but you must justify it. The heap simulation is
 * easier to explain, extends to "print the schedule", and is what many interviewers expect first.
 * ============================================================
 */
public class TaskScheduler {

    /** Approach 1 — Math formula. TIME O(T) · SPACE O(1) */
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

    /** Approach 2 — Max-heap + cooldown queue. TIME O(T log 26) · SPACE O(1) */
    public int leastIntervalHeap(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char task : tasks) freq[task - 'A']++;

        PriorityQueue<Integer> ready = new PriorityQueue<>(Collections.reverseOrder());
        for (int f : freq) if (f > 0) ready.offer(f);

        Deque<int[]> coolingDown = new ArrayDeque<>();   // {remainingCount, readyAtTime}
        int time = 0;
        while (!ready.isEmpty() || !coolingDown.isEmpty()) {
            if (ready.isEmpty()) time = coolingDown.peek()[1] - 1;   // idle until the next task is ready
            time++;
            while (!coolingDown.isEmpty() && coolingDown.peek()[1] <= time) {
                ready.offer(coolingDown.poll()[0]);
            }
            int remaining = ready.poll() - 1;
            if (remaining > 0) coolingDown.offer(new int[]{remaining, time + n + 1});
        }
        return time;
    }
}
