package com.interview.blind75.binarysearch;

/**
 * ============================================================
 * PROBLEM : Median of Two Sorted Arrays
 * LINK    : https://leetcode.com/problems/median-of-two-sorted-arrays/
 * DIFFICULTY: Hard
 * PATTERN : Binary Search
 * ============================================================
 *
 * DESCRIPTION:
 * Given two sorted arrays nums1 and nums2, return the median of the
 * two sorted arrays. The overall run time complexity must be O(log(m+n)).
 *
 * EXAMPLES:
 *   Input : nums1 = [1,3], nums2 = [2]
 *   Output: 2.0
 *
 *   Input : nums1 = [1,2], nums2 = [3,4]
 *   Output: 2.5
 *
 * CONSTRAINTS:
 *   - 0 <= m, n <= 1000
 *   - -10^6 <= nums1[i], nums2[i] <= 10^6
 *
 * ============================================================
 * APPROACHES
 * ============================================================
 * APPROACH 1: Binary search on the partition of the smaller array  (primary)
 *   1. Binary-search i in the smaller array; j = (m + n + 1)/2 − i in the other.
 *   2. A valid split has every left-side value ≤ every right-side value:
 *      maxLeft1 ≤ minRight2 and maxLeft2 ≤ minRight1.
 *   3. Median = max(left side) for odd totals, or the average of max(left)
 *      and min(right) for even totals.
 *   Intuition: we never merge — we only search for where the merged halves would split.
 *   TIME O(log(min(m, n))) · SPACE O(1)
 *
 * APPROACH 2: Merge-walk to the middle (two pointers, no extra array)
 *   1. Walk both arrays like a merge, but only count elements.
 *   2. Stop at index (m + n)/2, remembering the previous and current values.
 *   3. Odd total → current; even total → average of previous and current.
 *   Intuition: the median is just the middle of the merged order, so we only
 *   need to walk halfway.
 *   TIME O(m + n) · SPACE O(1)
 *
 * WHICH TO USE:
 *   The problem asks for O(log(m+n)), so #1 is the target. Lead with #2 to show
 *   the baseline in a minute, then derive #1. Merging into a new array is O(m+n)
 *   space and unnecessary.
 * ============================================================
 */
public class MedianOfTwoSortedArrays {

    /** Approach 1 — binary search the partition of the smaller array. TIME O(log(min(m,n))) · SPACE O(1) */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);

        int m = nums1.length, n = nums2.length;
        int left = 0, right = m;
        int halfLen = (m + n + 1) / 2;

        while (left <= right) {
            int i = left + (right - left) / 2;
            int j = halfLen - i;

            int maxLeft1  = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int minRight1 = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int maxLeft2  = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int minRight2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                int maxLeft  = Math.max(maxLeft1, maxLeft2);
                int minRight = Math.min(minRight1, minRight2);
                if ((m + n) % 2 == 1) return maxLeft;
                return (maxLeft + minRight) / 2.0;
            } else if (maxLeft1 > minRight2) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }
        throw new IllegalArgumentException("Input arrays are not sorted");
    }

    /** Approach 2 — merge-walk to the middle, counting only. TIME O(m + n) · SPACE O(1) */
    public double findMedianSortedArraysMergeWalk(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        int target = total / 2;
        int i = 0, j = 0, previous = 0, current = 0;
        for (int k = 0; k <= target; k++) {
            previous = current;
            if (j >= nums2.length || (i < nums1.length && nums1[i] <= nums2[j])) current = nums1[i++];
            else current = nums2[j++];
        }
        return total % 2 == 1 ? current : (previous + (long) current) / 2.0;
    }
}
