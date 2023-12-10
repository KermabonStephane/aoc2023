package org.demis27.aoc2023.day10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day10Test {

    @Test
    void day10_part1_sample() throws IOException {
        Day10 day10 = new Day10();
        long result = day10.processPartOne("day10-part1-sample.txt");
        Assertions.assertEquals(8, result);
    }

    @Test
    void day10_part2_sample() throws IOException {
        Day10 day10 = new Day10();
        long result = day10.processPartTwo("day10-part2-sample.txt");
        Assertions.assertEquals(4, result);
    }
    @Test
    void day10_part1() throws IOException {
        Day10 day10 = new Day10();
        long result = day10.processPartOne("day10.txt");
        Assertions.assertEquals(6947, result);
    }

    @Test
    void day10_part2() throws IOException {
        Day10 day10 = new Day10();
        long result = day10.processPartTwo("day10.txt");
        Assertions.assertEquals(0, result);
    }

}
