package com.interview.blind75.greedy;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PartitionLabelsTest {

    private final PartitionLabels solution = new PartitionLabels();

    @Test
    void example1() {
        assertEquals(List.of(9, 7, 8), solution.partitionLabels("ababcbacadefegdehijhklij"));
    }

    @Test
    void example2WholeString() {
        assertEquals(List.of(10), solution.partitionLabels("eccbbbbdec"));
    }

    @Test
    void allSame() {
        assertEquals(List.of(3), solution.partitionLabels("aaa"));
    }

    @Test
    void allDistinct() {
        assertEquals(List.of(1, 1, 1), solution.partitionLabels("abc"));
    }
}
