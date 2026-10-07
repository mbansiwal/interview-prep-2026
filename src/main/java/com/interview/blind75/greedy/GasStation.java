package com.interview.blind75.greedy;

/**
 * ============================================================
 * PROBLEM : Gas Station
 * LINK    : https://leetcode.com/problems/gas-station/
 * DIFFICULTY: Medium
 * PATTERN : Greedy
 * ============================================================
 *
 * DESCRIPTION:
 * There are n gas stations in a circle. gas[i] = amount of gas at station i.
 * cost[i] = cost to travel from station i to i+1. Return the starting station
 * index if you can travel around the circuit, or -1 if impossible.
 *
 * EXAMPLES:
 *   Input : gas=[1,2,3,4,5], cost=[3,4,5,1,2]  →  Output: 3
 *   Input : gas=[2,3,4], cost=[3,4,3]           →  Output: -1
 *
 * CONSTRAINTS:
 *   - 1 <= n <= 10^5
 *
 * ============================================================
 * APPROACH: Greedy — Single Pass
 * ============================================================
 * 1. If total gas < total cost, no solution exists → return -1.
 * 2. Track tank (running sum of net gas). When tank < 0:
 *    - We can't start from any station up to current.
 *    - Reset tank to 0 and move start to next station.
 * 3. The start index when we finish is the answer.
 *
 * WHY THIS WORKS:
 * If total gas >= total cost, a solution exists and is unique.
 * When tank goes negative starting from `start`, we know `start` through
 * current can't be starting points, so we move start forward greedily.
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0, tank = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
            int net = gas[i] - cost[i];
            totalGas += net;
            tank += net;
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        return totalGas >= 0 ? start : -1;
    }
}
