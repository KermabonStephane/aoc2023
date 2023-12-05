package org.demis27.aoc2023.day02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day02Test {

    @Test
    void day02_part1_sample() throws IOException {
        Day02 day02 = new Day02();
        long result = day02.processPartOne("day02-part1-sample.txt");
        Assertions.assertEquals(8, result);
    }

    @Test
    void day2_part2_sample() throws IOException {
        Day02 day02 = new Day02();
        long result = day02.processPartTwo("day02-part2-sample.txt");
        Assertions.assertEquals(2286, result);
    }
    @Test
    void day2_part1() throws IOException {
        Day02 day02 = new Day02();
        long result = day02.processPartOne("day02.txt");
        Assertions.assertEquals(1931, result);
    }

    @Test
    void day2_part2() throws IOException {
        Day02 day02 = new Day02();
        long result = day02.processPartTwo("day02.txt");
        Assertions.assertEquals(83105, result);
    }

}
