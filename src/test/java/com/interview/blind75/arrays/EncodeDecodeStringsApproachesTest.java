package com.interview.blind75.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EncodeDecodeStringsApproachesTest {

    static Stream<UnaryOperator<List<String>>> roundTrips() {
        EncodeDecodeStrings s = new EncodeDecodeStrings();
        return Stream.of(l -> s.decode(s.encode(l)), l -> s.decodeEscaped(s.encodeEscaped(l)));
    }

    @ParameterizedTest @MethodSource("roundTrips")
    void basic(UnaryOperator<List<String>> rt) {
        List<String> in = List.of("lint", "code", "love", "you");
        assertEquals(in, rt.apply(in));
    }

    @ParameterizedTest @MethodSource("roundTrips")
    void emptyList(UnaryOperator<List<String>> rt) { assertEquals(List.of(), rt.apply(List.of())); }

    @ParameterizedTest @MethodSource("roundTrips")
    void emptyStrings(UnaryOperator<List<String>> rt) {
        List<String> in = List.of("", "", "x", "");
        assertEquals(in, rt.apply(in));
    }

    @ParameterizedTest @MethodSource("roundTrips")
    void delimitersAndDigitsInData(UnaryOperator<List<String>> rt) {
        List<String> in = List.of("#", "##;", "4#abc", "#;#;", "12#", ";");
        assertEquals(in, rt.apply(in));
    }
}
