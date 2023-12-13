package org.demis27.aoc2023.days.day11;

import org.demis27.aoc2023.days.day11.Day11;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day11Test {

    @Test
    void day11_part1_sample() throws IOException {
        Day11 day11 = new Day11();
        long result = day11.processPartOne("day11-part1-sample.txt");
        Assertions.assertEquals(374, result);
    }

    @Test
    void day11_part2_sample() throws IOException {
        Day11 day11 = new Day11();
        long result = day11.processPartTwo("day11-part2-sample.txt");
        Assertions.assertEquals(82000210, result);
    }
    @Test
    void day11_part1() throws IOException {
        Day11 day11 = new Day11();
        long result = day11.processPartOne("day11.txt");
        Assertions.assertEquals(10033566, result);
    }

    @Test
    void day11_part2() throws IOException {
        Day11 day11 = new Day11();
        long result = day11.processPartTwo("day11.txt");
        Assertions.assertEquals(560822911938L, result);
    }

}
