package com.interview.blind75.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * ============================================================
 * PROBLEM : Car Fleet
 * LINK    : https://leetcode.com/problems/car-fleet/
 * DIFFICULTY: Medium
 * PATTERN : Sorting + Greedy (classic "stack" problem, solved without one)
 * ============================================================
 *
 * DESCRIPTION:
 * N cars drive toward a target. Each car has a position and speed.
 * A car that catches up to a slower car forms a fleet moving at the
 * slower car's speed. Return the number of fleets at the target.
 *
 * EXAMPLES:
 *   Input : target=12, position=[10,8,0,5,3], speed=[2,4,1,1,3]
 *   Output: 3
 *
 *   Input : target=10, position=[3], speed=[3]
 *   Output: 1
 *
 * CONSTRAINTS:
 *   - n == position.length == speed.length
 *   - 1 <= n <= 10^5
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Sort by position, track the slowest arrival ahead  (primary)
 *   1. Sort cars by position, closest to the target first.
 *   2. time = (target − position) / speed.
 *   3. A car slower to arrive than every car ahead starts a new fleet;
 *      otherwise it catches up and joins the fleet in front.
 *   Intuition: a car can never pass the one ahead, so only arrival times matter.
 *   TIME O(n log n) · SPACE O(n) for the sorted pairs
 *
 * APPROACH 2: Same order, explicit monotonic stack of fleet times
 *   1. Process cars from closest to farthest; push each arrival time.
 *   2. If the new time ≤ the stack top, it merges — pop it straight back off.
 *   3. The stack size is the number of fleets.
 *   Intuition: the stack holds one arrival time per fleet (the leader's).
 *   TIME O(n log n) · SPACE O(n)
 *
 * APPROACH 3: Counting sort by position (positions are distinct integers < target)
 *   1. arrival[position] = time for each car, in an array of size target.
 *   2. Walk positions from target−1 down to 0, applying the rule from #1.
 *   Intuition: the bounded integer positions let us replace the sort with indexing.
 *   TIME O(n + target) · SPACE O(target)
 *
 * WHICH TO USE:
 *   #1 (or #2, if you want to show the stack pattern) is expected. #3 is a good
 *   follow-up when target is small relative to n log n.
 * ============================================================
 */
public class CarFleet {

    /** Approach 1 — sort + slowest-ahead counter. TIME O(n log n) · SPACE O(n) */
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        int[][] cars = new int[n][2];
        for (int i = 0; i < n; i++) cars[i] = new int[]{position[i], speed[i]};
        Arrays.sort(cars, (a, b) -> Integer.compare(b[0], a[0]));

        int fleets = 0;
        double slowestAhead = 0;

        for (int[] car : cars) {
            double time = (double)(target - car[0]) / car[1];
            if (time > slowestAhead) {
                fleets++;
                slowestAhead = time;
            }
        }
        return fleets;
    }

    /** Approach 2 — sort + explicit stack of fleet arrival times. TIME O(n log n) · SPACE O(n) */
    public int carFleetStack(int target, int[] position, int[] speed) {
        int n = position.length;
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> Integer.compare(position[b], position[a]));

        Deque<Double> fleets = new ArrayDeque<>();
        for (int i : order) {
            double time = (double) (target - position[i]) / speed[i];
            fleets.push(time);
            if (fleets.size() >= 2) {
                double mine = fleets.pop();
                if (mine > fleets.peek()) fleets.push(mine); // slower: a new fleet
            }
        }
        return fleets.size();
    }

    /** Approach 3 — counting sort by position. TIME O(n + target) · SPACE O(target) */
    public int carFleetCountingSort(int target, int[] position, int[] speed) {
        double[] arrival = new double[target]; // 0 means "no car here"
        for (int i = 0; i < position.length; i++) {
            arrival[position[i]] = (double) (target - position[i]) / speed[i];
        }
        int fleets = 0;
        double slowestAhead = 0;
        for (int p = target - 1; p >= 0; p--) {
            if (arrival[p] > slowestAhead) {
                fleets++;
                slowestAhead = arrival[p];
            }
        }
        return fleets;
    }
}
