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
 * APPROACH: Binary Search on Smaller Array Partition
 * ============================================================
 * 1. Binary search on the smaller array for a partition point.
 * 2. Partition both arrays so left halves together have (m+n+1)/2 elements.
 * 3. Check if maxLeft1 <= minRight2 AND maxLeft2 <= minRight1 (valid partition).
 * 4. If not, adjust the search range.
 * 5. Median = max(maxLeft) for odd total, or avg(max(maxLeft), min(minRight)) for even.
 *
 * WHY THIS WORKS:
 * The median is the point where both arrays are perfectly split into
 * equal halves. Binary search finds this split in O(log(min(m,n))).
 *
 * TIME  : O(log(min(m,n)))
 * SPACE : O(1)
 * ============================================================
 */
public class MedianOfTwoSortedArrays {

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
}
