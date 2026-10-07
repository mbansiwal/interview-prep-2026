package com.interview.blind75.greedy;

/**
 * ============================================================
 * PROBLEM : Jump Game
 * LINK    : https://leetcode.com/problems/jump-game/
 * DIFFICULTY: Medium
 * PATTERN : Greedy
 * ============================================================
 *
 * DESCRIPTION:
 * Given an integer array nums where nums[i] is the maximum jump length from
 * index i, return true if you can reach the last index starting from index 0.
 *
 * EXAMPLES:
 *   Input : [2,3,1,1,4]  →  Output: true
 *   Input : [3,2,1,0,4]  →  Output: false
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 10^4
 *
 * ============================================================
 * APPROACH: Greedy — Track Maximum Reachable Index
 * ============================================================
 * Maintain `maxReach`: the farthest index we can reach so far.
 * At each index i:
 * - If i > maxReach, we cannot reach this index → return false.
 * - Update maxReach = max(maxReach, i + nums[i]).
 *
 * WHY THIS WORKS:
 * If at any point we're trying to visit an index beyond our reach, we're stuck.
 * Otherwise we greedily extend the reachable range.
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class JumpGame {

    public boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        return true;
    }
}
