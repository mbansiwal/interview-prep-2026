package com.interview.blind75.binarysearch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TimeBasedKeyValueStoreApproachesTest {

    interface Setter { void set(String key, String value, int timestamp); }
    record Store(Setter set, BiFunction<String, Integer, String> get) {}

    static Stream<Supplier<Store>> approaches() {
        return Stream.of(
            () -> { TimeBasedKeyValueStore s = new TimeBasedKeyValueStore(); return new Store(s::set, s::get); },
            () -> { TimeBasedKeyValueStore.TreeMapStore s = new TimeBasedKeyValueStore.TreeMapStore(); return new Store(s::set, s::get); });
    }

    @ParameterizedTest @MethodSource("approaches")
    void leetCodeTrace(Supplier<Store> make) {
        Store s = make.get();
        s.set().set("foo", "bar", 1);
        assertEquals("bar", s.get().apply("foo", 1));
        assertEquals("bar", s.get().apply("foo", 3));
        s.set().set("foo", "bar2", 4);
        assertEquals("bar2", s.get().apply("foo", 4));
        assertEquals("bar2", s.get().apply("foo", 5));
    }

    @ParameterizedTest @MethodSource("approaches")
    void beforeFirstTimestamp(Supplier<Store> make) {
        Store s = make.get();
        s.set().set("k", "v", 10);
        assertEquals("", s.get().apply("k", 9));
    }

    @ParameterizedTest @MethodSource("approaches")
    void unknownKey(Supplier<Store> make) { assertEquals("", make.get().get().apply("missing", 1)); }

    @ParameterizedTest @MethodSource("approaches")
    void keysAreIndependent(Supplier<Store> make) {
        Store s = make.get();
        s.set().set("a", "a1", 1);
        s.set().set("b", "b5", 5);
        assertEquals("a1", s.get().apply("a", 100));
        assertEquals("", s.get().apply("b", 4));
    }
}
