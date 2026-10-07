package com.interview.blind75.stack;

import java.util.Arrays;

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
 * APPROACH: Sort by Position + Track Slowest Arrival Time
 * ============================================================
 * 1. Sort cars by position descending (closest to target first).
 * 2. For each car, compute time = (target - position) / speed.
 * 3. Keep slowestAhead = arrival time of the fleet directly in front.
 *    If this car's time > slowestAhead, it can never catch up: new fleet,
 *    and it becomes the new slowestAhead.
 * 4. Otherwise it catches up and merges into the fleet ahead (no count change).
 *
 * WHY THIS WORKS:
 * A car can't pass the car ahead, so if it would arrive sooner it gets
 * blocked and arrives with that fleet. Only a car that is slower than
 * everything in front starts a new fleet. A stack would only ever need
 * its top element, so a single variable replaces it.
 *
 * TIME  : O(n log n) — sorting
 * SPACE : O(n) — the sorted cars array
 * ============================================================
 */
public class CarFleet {

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
}
