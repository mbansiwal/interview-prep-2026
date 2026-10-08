package com.interview.blind75.greedy;

/**
 * ============================================================
 * PROBLEM : Jump Game II
 * LINK    : https://leetcode.com/problems/jump-game-ii/
 * DIFFICULTY: Medium
 * PATTERN : Greedy (BFS-like level traversal)
 * ============================================================
 *
 * DESCRIPTION:
 * Return the minimum number of jumps to reach the last index.
 * You can always reach the last index.
 *
 * EXAMPLES:
 *   Input : [2,3,1,1,4]  →  Output: 2  (jump to index 1, then to 4)
 *   Input : [2,3,0,1,4]  →  Output: 2
 *
 * CONSTRAINTS:
 *   - 1 <= nums.length <= 10^4
 *   - 0 <= nums[i] <= 1000
 *
 * ============================================================
 * APPROACH 1: Greedy BFS levels                            → jump
 * ============================================================
 * Think of each jump as a BFS level. Track:
 * - curEnd: the farthest index reachable in current jump.
 * - farthest: the farthest index reachable from any index in current level.
 * When i reaches curEnd, take a jump (increment jumps), set curEnd = farthest.
 *
 * WHY THIS WORKS:
 * Within each "level" (range of indices reachable in k jumps), we greedily
 * find the farthest reach, which becomes the boundary for k+1 jumps.
 *
 * TIME  : O(n)
 * SPACE : O(1)
 *
 * ALTERNATIVES: DP (dp[i] = min jumps to reach i, relaxing every jump from each
 * index) is O(n · max jump) ≈ O(n²) time and O(n) space — the greedy level scan
 * is the materially better answer, so it's the only one implemented.
 * ============================================================
 */
public class JumpGameII {

    /** Approach 1 — Greedy BFS levels. TIME O(n) · SPACE O(1) */
    public int jump(int[] nums) {
        int jumps = 0, curEnd = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = farthest;
            }
        }
        return jumps;
    }
}
