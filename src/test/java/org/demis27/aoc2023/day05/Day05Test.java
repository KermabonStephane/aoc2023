package org.demis27.aoc2023.day05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day05Test {

    @Test
    void day5_part1_sample() throws IOException {
        Day05 day04 = new Day05();
        long result = day04.processPartOne("day5-part1-sample.txt");
        Assertions.assertEquals(0, result);
    }

    @Test
    void day5_part2_sample() throws IOException {
        Day05 day04 = new Day05();
        long result = day04.processPartTwo("day5-part2-sample.txt");
        Assertions.assertEquals(0, result);
    }
    @Test
    void day5_part1() throws IOException {
        Day05 day04 = new Day05();
        long result = day04.processPartOne("day5.txt");
        Assertions.assertEquals(0, result);
    }

    @Test
    void day5_part2() throws IOException {
        Day05 day04 = new Day05();
        long result = day04.processPartTwo("day5.txt");
        Assertions.assertEquals(0, result);
    }

}
