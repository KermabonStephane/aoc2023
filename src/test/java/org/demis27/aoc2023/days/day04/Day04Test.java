package org.demis27.aoc2023.days.day04;

import org.demis27.aoc2023.days.day04.Day04;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day04Test {

    @Test
    void day04_part1_sample() throws IOException {
        Day04 day04 = new Day04();
        long result = day04.processPartOne("day04-part1-sample.txt");
        Assertions.assertEquals(13, result);
    }

    @Test
    void day04_part2_sample() throws IOException {
        Day04 day04 = new Day04();
        long result = day04.processPartTwo("day04-part2-sample.txt");
        Assertions.assertEquals(30, result);
    }
    @Test
    void day04_part1() throws IOException {
        Day04 day04 = new Day04();
        long result = day04.processPartOne("day04.txt");
        Assertions.assertEquals(19135, result);
    }

    @Test
    void day04_part2() throws IOException {
        Day04 day04 = new Day04();
        long result = day04.processPartTwo("day04.txt");
        Assertions.assertEquals(5704953, result);
    }

}
