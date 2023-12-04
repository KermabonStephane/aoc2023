package org.demis27.aoc2023.day01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class Day01Test {

    @Test
    void day1_part1_sample() throws IOException {
        Day01 day01 = new Day01();
        long result = day01.getResultPartOne("day1-part1-sample.txt");
        Assertions.assertEquals(142, result);
    }

    @Test
    void day1_part2_sample() throws IOException {
        Day01 day01 = new Day01();
        long result = day01.getResultPartTwo("day1-part2-sample.txt");
        Assertions.assertEquals(281, result);
    }
    @Test
    void day1_part1() throws IOException {
        Day01 day01 = new Day01();
        long result = day01.getResultPartOne("day1.txt");
        Assertions.assertEquals(53921, result);
    }

    @Test
    void day1_part2() throws IOException {
        Day01 day01 = new Day01();
        long result = day01.getResultPartTwo("day1.txt");
        Assertions.assertEquals(54676, result);
    }
}