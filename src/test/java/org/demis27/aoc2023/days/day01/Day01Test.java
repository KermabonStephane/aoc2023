package org.demis27.aoc2023.days.day01;

import org.demis27.aoc2023.days.day01.Day01;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class Day01Test {

    @Test
    void day01_part1_sample() throws IOException {
        Day01 day01 = new Day01();
        long result = day01.getResultPartOne("day01-part1-sample.txt");
        Assertions.assertEquals(142, result);
    }

    @Test
    void day01_part2_sample() throws IOException {
        Day01 day01 = new Day01();
        long result = day01.getResultPartTwo("day01-part2-sample.txt");
        Assertions.assertEquals(281, result);
    }
    @Test
    void day01_part1() throws IOException {
        Day01 day01 = new Day01();
        long result = day01.getResultPartOne("day01.txt");
        Assertions.assertEquals(53921, result);
    }

    @Test
    void day01_part2() throws IOException {
        Day01 day01 = new Day01();
        long result = day01.getResultPartTwo("day01.txt");
        Assertions.assertEquals(54676, result);
    }
}