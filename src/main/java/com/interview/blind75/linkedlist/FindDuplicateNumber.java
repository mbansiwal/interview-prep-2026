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
 * APPROACHES
 * ============================================================
 * APPROACH 1: Floyd's Cycle Detection on an Implicit Linked List
 * 1. Treat the array as a linked list: index i points to nums[i].
 * 2. Phase 1: move slow by 1 and fast by 2 until they meet inside the cycle.
 * 3. Phase 2: restart one pointer at the start; move both by 1 — they meet at the entry.
 * Intuition: two indices point to the duplicate value, so it is the node with two
 * incoming edges — the cycle entry. Distance start→entry equals distance
 * meeting-point→entry (mod cycle length), which is why phase 2 lands on it.
 * TIME  : O(n)
 * SPACE : O(1), array not modified
 *
 * APPROACH 2: Binary Search on the Value Range
 * 1. Search values lo=1..hi=n. For mid, count how many nums are <= mid.
 * 2. If count > mid, the duplicate is in [lo, mid] (pigeonhole); otherwise in [mid+1, hi].
 * 3. When lo == hi, that value is the duplicate.
 * Intuition: without a duplicate, exactly k numbers are <= k; an excess means it's in that half.
 * TIME  : O(n log n)
 * SPACE : O(1), array not modified
 *
 * APPROACH 3: Sign Marking (index as a visited flag)
 * 1. For each value v, look at index |v|; if nums[|v|] is already negative, |v| is the duplicate.
 * 2. Otherwise negate nums[|v|] to mark |v| as seen.
 * 3. Restore all signs before returning.
 * Intuition: values are 1..n, so each value has its own index to use as a flag.
 * TIME  : O(n)
 * SPACE : O(1) — but it temporarily MODIFIES the array, which this problem forbids
 *
 * WHICH TO USE:
 * Floyd's is the answer that meets every constraint (O(n), O(1), read-only).
 * Binary search is a strong alternative that's easier to derive in an interview.
 * Sign marking is worth mentioning only to show why the constraint rules it out.
 * (Brute force O(n²) or a HashSet O(n) space also violate the constraints.)
 * ============================================================
 */
public class FindDuplicateNumber {

    /** Approach 1 — Floyd's cycle detection. TIME O(n) · SPACE O(1) · read-only */
    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /** Approach 2 — Binary search on value range. TIME O(n log n) · SPACE O(1) · read-only */
    public int findDuplicateBinarySearch(int[] nums) {
        int lo = 1, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int countAtMostMid = 0;
            for (int num : nums) if (num <= mid) countAtMostMid++;
            if (countAtMostMid > mid) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    /** Approach 3 — Sign marking. TIME O(n) · SPACE O(1) · temporarily mutates input (restored) */
    public int findDuplicateSignMarking(int[] nums) {
        int duplicate = -1;
        for (int num : nums) {
            int idx = Math.abs(num);
            if (nums[idx] < 0) {
                duplicate = idx;
                break;
            }
            nums[idx] = -nums[idx];
        }
        for (int i = 0; i < nums.length; i++) nums[i] = Math.abs(nums[i]);
        return duplicate;
    }
}
