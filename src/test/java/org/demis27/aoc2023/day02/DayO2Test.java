package org.demis27.aoc2023.day02;

import org.demis27.aoc2023.day01.Day01;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DayO2Test {

    @Test
    void day2_part1_sample() throws IOException {
        Day02 day02 = new Day02();
        long result = day02.processPartOne("day2-part1-sample.txt");
        Assertions.assertEquals(8, result);
    }

    @Test
    void day2_part2_sample() throws IOException {
        Day02 day02 = new Day02();
        long result = day02.processPartTwo("day2-part2-sample.txt");
        Assertions.assertEquals(2286, result);
    }
    @Test
    void day1_part1() throws IOException {
        Day02 day02 = new Day02();
        long result = day02.processPartOne("day2.txt");
        Assertions.assertEquals(1931, result);
    }

    @Test
    void day1_part2() throws IOException {
        Day02 day02 = new Day02();
        long result = day02.processPartTwo("day2.txt");
        Assertions.assertEquals(83105, result);
    }

}
