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
 * APPROACHES
 * ============================================================
 * DP "can I reach the end from i?" checking every jump length is O(n²) time,
 * O(n) space; both greedy scans below beat it.
 *
 * APPROACH 1: Forward — track the farthest reachable index → canJump
 *   1. maxReach = 0.
 *   2. For each i: if i > maxReach we're stuck → false; else extend
 *      maxReach = max(maxReach, i + nums[i]).
 *   Intuition: the reachable indices always form a prefix [0, maxReach].
 *   TIME  : O(n)    SPACE : O(1)
 *
 * APPROACH 2: Backward — shift the goal toward the start   → canJumpBackward
 *   1. goal = last index.
 *   2. Scan i from right to left: if i + nums[i] >= goal, index i can reach the
 *      goal, so it becomes the new goal.
 *   3. Answer: goal == 0.
 *   Intuition: "can I reach the end?" ⇔ "can I reach any index that reaches the end?"
 *   TIME  : O(n)    SPACE : O(1)
 *
 * WHICH TO USE:
 * Either. The forward scan generalises straight into Jump Game II (BFS levels);
 * the backward scan is often the easiest to prove correct out loud.
 * ============================================================
 */
public class JumpGame {

    /** Approach 1 — Forward max-reach scan. TIME O(n) · SPACE O(1) */
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        return true;
    }

    /** Approach 2 — Backward goal shifting. TIME O(n) · SPACE O(1) */
    public boolean canJumpBackward(int[] nums) {
        int goal = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= goal) goal = i;
        }
        return goal == 0;
    }
}
