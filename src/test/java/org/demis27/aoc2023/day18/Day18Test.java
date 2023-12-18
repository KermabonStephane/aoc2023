package org.demis27.aoc2023.day18;

import org.demis27.aoc2023.day18.Day18;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day18Test {

    @Test
    void day18_part1_sample() throws IOException {
        Day18 day18 = new Day18();
        long result = day18.processPartOne("day18-part1-sample.txt");
        Assertions.assertEquals(62, result);
    }

    @Test
    void day18_part1_sample2() throws IOException {
        Day18 day18 = new Day18();
        long result = day18.processPartOne("day18-part2-very-sample3.txt");
        Assertions.assertEquals(16, result);
    }

    @Test
    void day18_part1() throws IOException {
        Day18 day18 = new Day18();
        long result = day18.processPartOne("day18.txt");
        Assertions.assertEquals(35991, result);
    }

    @Test
    void day18_part2_sample() throws IOException {
        Day18 day18 = new Day18();
        long result = day18.processPartTwo("day18-part2-sample.txt");
        Assertions.assertEquals(952408144115L, result);
    }

    @Test
    void day18_part2_sample2() throws IOException {
        Day18 day18 = new Day18();
        long result = day18.processPartTwo("day18-part2-very-sample3.txt");
        Assertions.assertEquals(16, result);
    }

    @Test
    void day18_part2() throws IOException {
        Day18 day18 = new Day18();
        long result = day18.processPartTwo("day18.txt");
        Assertions.assertEquals(0, result);
    }

}
