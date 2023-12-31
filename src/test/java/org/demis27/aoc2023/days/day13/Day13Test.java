package org.demis27.aoc2023.days.day13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day13Test {

    @Test
    void day13_part1_sample() throws IOException {
        Day13 day13 = new Day13();
        long result = day13.processPartOne("day13-part1-sample.txt");
        Assertions.assertEquals(405, result);
    }

    @Test
    void day13_part1_sample2() throws IOException {
        Day13 day13 = new Day13();
        long result = day13.processPartOne("day13-part1-sample2.txt");
        Assertions.assertEquals(1112, result);
    }
    @Test
    void day13_part1() throws IOException {
        Day13 day13 = new Day13();
        long result = day13.processPartOne("day13.txt");
        Assertions.assertEquals(37025, result);
    }

    @Test
    void day13_part2_sample() throws IOException {
        Day13 day13 = new Day13();
        long result = day13.processPartTwo("day13-part2-sample.txt");
        Assertions.assertEquals(400, result);
    }
    @Test
    void day13_part2() throws IOException {
        Day13 day13 = new Day13();
        long result = day13.processPartTwo("day13.txt");
        Assertions.assertEquals(0, result);
    }

}
