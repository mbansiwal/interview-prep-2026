package com.interview.blind75.arrays;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EncodeDecodeStringsTest {

    private final EncodeDecodeStrings codec = new EncodeDecodeStrings();

    private void assertRoundTrip(List<String> input) {
        assertEquals(input, codec.decode(codec.encode(input)));
    }

    @Test
    void standardExample() {
        List<String> input = List.of("lint", "code", "love", "you");
        assertEquals("4#lint4#code4#love3#you", codec.encode(input));
        assertRoundTrip(input);
    }

    @Test
    void emptyList() {
        assertEquals("", codec.encode(List.of()));
        assertEquals(List.of(), codec.decode(""));
    }

    @Test
    void stringsContainingDelimiterAndDigits() {
        assertRoundTrip(List.of("#", "12#ab", "3#", "##3##", "100"));
    }

    @Test
    void emptyStrings() {
        assertRoundTrip(List.of("", "", "a", ""));
    }

    @Test
    void longString() {
        assertRoundTrip(List.of("x".repeat(150), "y"));
    }
}
