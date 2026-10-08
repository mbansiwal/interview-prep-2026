package com.interview.blind75.binarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
 * APPROACHES
 * ============================================================
 * APPROACH 1: HashMap of append-only lists + binary search  (primary: TimeBasedKeyValueStore)
 *   1. set: append (timestamp, value) to the key's list — timestamps arrive increasing,
 *      so the list stays sorted for free.
 *   2. get: binary-search for the last entry with timestamp ≤ the query.
 *   Intuition: "most recent value at or before t" is a floor search on a sorted list.
 *   TIME set O(1) amortized, get O(log n) · SPACE O(N) total entries
 *
 * APPROACH 2: HashMap of TreeMaps  (TimeBasedKeyValueStore.TreeMapStore)
 *   1. set: treeMap.put(timestamp, value).
 *   2. get: treeMap.floorEntry(timestamp).
 *   Intuition: a balanced BST gives the floor lookup directly — and still works
 *   if timestamps arrive out of order.
 *   TIME set O(log n), get O(log n) · SPACE O(N), with higher constant overhead
 *
 * WHICH TO USE:
 *   #1 is optimal here because set timestamps are strictly increasing, and it
 *   shows you can write the floor binary search. #2 is shorter and is the right
 *   choice if that ordering guarantee is dropped.
 * ============================================================
 */
public class TimeBasedKeyValueStore {

    private record Entry(int timestamp, String value) {}

    private final Map<String, List<Entry>> store = new HashMap<>();

    /** Approach 1 — append to the key's list. TIME O(1) amortized · SPACE O(1) per entry */
    public void set(String key, String value, int timestamp) {
        store.computeIfAbsent(key, k -> new ArrayList<>()).add(new Entry(timestamp, value));
    }

    /** Approach 1 — floor binary search on the key's list. TIME O(log n) · SPACE O(1) */
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

    /** Approach 2 — HashMap of TreeMaps with floorEntry. set/get TIME O(log n) · SPACE O(N) */
    public static class TreeMapStore {
        private final Map<String, TreeMap<Integer, String>> store = new HashMap<>();

        /** TreeMap put. TIME O(log n) · SPACE O(1) per entry */
        public void set(String key, String value, int timestamp) {
            store.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
        }

        /** TreeMap floorEntry. TIME O(log n) · SPACE O(1) */
        public String get(String key, int timestamp) {
            TreeMap<Integer, String> versions = store.get(key);
            if (versions == null) return "";
            Map.Entry<Integer, String> floor = versions.floorEntry(timestamp);
            return floor == null ? "" : floor.getValue();
        }
    }
}
