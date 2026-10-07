package com.interview.blind75.binarysearch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TimeBasedKeyValueStoreTest {

    @Test
    void basicGet() {
        TimeBasedKeyValueStore store = new TimeBasedKeyValueStore();
        store.set("foo", "bar", 1);
        assertEquals("bar", store.get("foo", 1));
        assertEquals("bar", store.get("foo", 3));
    }

    @Test
    void floorTimestamp() {
        TimeBasedKeyValueStore store = new TimeBasedKeyValueStore();
        store.set("foo", "bar", 1);
        store.set("foo", "bar2", 4);
        assertEquals("bar", store.get("foo", 3));
        assertEquals("bar2", store.get("foo", 5));
    }

    @Test
    void noEntry() {
        TimeBasedKeyValueStore store = new TimeBasedKeyValueStore();
        assertEquals("", store.get("missing", 1));
    }

    @Test
    void timestampBeforeFirst() {
        TimeBasedKeyValueStore store = new TimeBasedKeyValueStore();
        store.set("foo", "bar", 5);
        assertEquals("", store.get("foo", 3));
    }
}
