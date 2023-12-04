package org.demis27.aoc2023.day04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day04Test {

    @Test
    void day4_part1_sample() throws IOException {
        Day04 day04 = new Day04();
        long result = day04.processPartOne("day4-part1-sample.txt");
        Assertions.assertEquals(13, result);
    }

    @Test
    void day4_part2_sample() throws IOException {
        Day04 day04 = new Day04();
        long result = day04.processPartTwo("day4-part2-sample.txt");
        Assertions.assertEquals(30, result);
    }
    @Test
    void day4_part1() throws IOException {
        Day04 day04 = new Day04();
        long result = day04.processPartOne("day4.txt");
        Assertions.assertEquals(19135, result);
    }

    @Test
    void day4_part2() throws IOException {
        Day04 day04 = new Day04();
        long result = day04.processPartTwo("day4.txt");
        Assertions.assertEquals(5704953, result);
    }

}
