package com.interview.blind75.linkedlist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.IntFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {

    interface Cache {
        int get(int key);
        void put(int key, int value);
    }

    static Stream<IntFunction<Cache>> approaches() {
        IntFunction<Cache> handBuilt = cap -> {
            LRUCache c = new LRUCache(cap);
            return new Cache() {
                public int get(int key) { return c.get(key); }
                public void put(int key, int value) { c.put(key, value); }
            };
        };
        IntFunction<Cache> linkedHashMap = cap -> {
            LRUCache.LinkedHashMapCache c = new LRUCache.LinkedHashMapCache(cap);
            return new Cache() {
                public int get(int key) { return c.get(key); }
                public void put(int key, int value) { c.put(key, value); }
            };
        };
        return Stream.of(handBuilt, linkedHashMap);
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void basicEviction(IntFunction<Cache> factory) {
        Cache cache = factory.apply(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1));
        cache.put(3, 3);  // evicts key 2
        assertEquals(-1, cache.get(2));
        cache.put(4, 4);  // evicts key 1
        assertEquals(-1, cache.get(1));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void updateExistingRefreshesRecency(IntFunction<Cache> factory) {
        Cache cache = factory.apply(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(1, 10);  // key 1 becomes most recent
        cache.put(3, 3);   // evicts key 2
        assertEquals(10, cache.get(1));
        assertEquals(-1, cache.get(2));
        assertEquals(3, cache.get(3));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void capacityOne(IntFunction<Cache> factory) {
        Cache cache = factory.apply(1);
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
    }

    @ParameterizedTest
    @MethodSource("approaches")
    void missingKey(IntFunction<Cache> factory) {
        assertEquals(-1, factory.apply(2).get(42));
    }
}
