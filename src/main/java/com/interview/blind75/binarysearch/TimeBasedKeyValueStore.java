package com.interview.blind75.binarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ============================================================
 * PROBLEM : Time Based Key-Value Store
 * LINK    : https://leetcode.com/problems/time-based-key-value-store/
 * DIFFICULTY: Medium
 * PATTERN : Binary Search
 * ============================================================
 *
 * DESCRIPTION:
 * Design a time-based key-value store. set(key, value, timestamp) stores
 * the value. get(key, timestamp) returns the value with the largest
 * timestamp <= given timestamp, or "" if none exists.
 *
 * EXAMPLES:
 *   set("foo","bar",1); get("foo",1) → "bar"
 *   set("foo","bar2",4); get("foo",3) → "bar"; get("foo",5) → "bar2"
 *
 * CONSTRAINTS:
 *   - 1 <= key.length, value.length <= 100
 *   - All timestamps passed to set() are strictly increasing
 *
 * ============================================================
 * APPROACH: HashMap of Lists + Binary Search
 * ============================================================
 * 1. Map each key to a list of (timestamp, value) pairs.
 * 2. set(): append to the list (timestamps always increase, so list stays sorted).
 * 3. get(): binary search for the largest timestamp <= query. Return value or "".
 *
 * WHY THIS WORKS:
 * Since timestamps are monotonically increasing per key, the list is
 * always sorted — binary search finds the floor in O(log n).
 *
 * TIME  : O(1) amortized set, O(log n) get
 * SPACE : O(n total entries)
 *
 * ALTERNATIVE: TreeMap<Integer,String> per key + floorEntry(ts) — same
 * O(log n), less code, but hides the binary search interviewers want to see.
 * ============================================================
 */
public class TimeBasedKeyValueStore {

    private record Entry(int timestamp, String value) {}

    private final Map<String, List<Entry>> store = new HashMap<>();

    public void set(String key, String value, int timestamp) {
        store.computeIfAbsent(key, k -> new ArrayList<>()).add(new Entry(timestamp, value));
    }

    public String get(String key, int timestamp) {
        List<Entry> entries = store.get(key);
        if (entries == null) return "";

        int left = 0, right = entries.size() - 1;
        String best = "";
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (entries.get(mid).timestamp() <= timestamp) {
                best = entries.get(mid).value();
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return best;
    }
}
