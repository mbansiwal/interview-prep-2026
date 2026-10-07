package com.interview.blind75.linkedlist;

/**
 * ============================================================
 * PROBLEM : Find the Duplicate Number
 * LINK    : https://leetcode.com/problems/find-the-duplicate-number/
 * DIFFICULTY: Medium
 * PATTERN : Linked List (Floyd's Cycle Detection)
 * ============================================================
 *
 * DESCRIPTION:
 * Given an array nums containing n+1 integers where each integer is in [1,n],
 * find the one duplicate number. Must not modify array, use only O(1) extra space.
 *
 * EXAMPLES:
 *   Input : nums = [1,3,4,2,2]
 *   Output: 2
 *
 *   Input : nums = [3,1,3,4,2]
 *   Output: 3
 *
 * CONSTRAINTS:
 *   - 1 <= n <= 10^5
 *   - nums.length == n + 1
 *   - Each element is in range [1, n]
 *
 * ============================================================
 * APPROACH: Floyd's Cycle Detection on Implicit Linked List
 * ============================================================
 * 1. Treat the array as a linked list: index i points to nums[i].
 * 2. Since a value is duplicated, two indices point to the same next — a cycle.
 * 3. Phase 1: Find intersection using slow/fast pointers.
 * 4. Phase 2: Find cycle entry (duplicate) using two slow pointers.
 *
 * WHY THIS WORKS:
 * Two different indices point to the duplicate value, so the duplicate is
 * where the cycle begins (the node with two incoming edges).
 * Why phase 2 finds it: let F = distance from start to the cycle entry and
 * C = cycle length. When slow and fast meet, fast has gone twice as far, which
 * works out to: distance (start → entry) == distance (meeting point → entry),
 * modulo C. So one pointer from the start and one from the meeting point,
 * both moving 1 step at a time, meet exactly at the entry — the duplicate.
 *
 * TIME  : O(n)
 * SPACE : O(1)
 * ============================================================
 */
public class FindDuplicateNumber {

    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[0];

        // Phase 1: Find intersection point
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Phase 2: Find cycle entry (the duplicate)
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
