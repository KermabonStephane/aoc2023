package org.demis27.aoc2023.days.day06;

import org.demis27.aoc2023.days.day06.Day06;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day06Test {

    @Test
    void day06_part1_sample() throws IOException {
        Day06 day06 = new Day06();
        long result = day06.processPartOne("day06-part1-sample.txt");
        Assertions.assertEquals(288, result);
    }

    @Test
    void day06_part2_sample() throws IOException {
        Day06 day06 = new Day06();
        long result = day06.processPartTwo("day06-part2-sample.txt");
        Assertions.assertEquals(71503, result);
    }
    @Test
    void day06_part1() throws IOException {
        Day06 day06 = new Day06();
        long result = day06.processPartOne("day06.txt");
        Assertions.assertEquals(2612736, result);
    }

    @Test
    void day06_part2() throws IOException {
        Day06 day06 = new Day06();
        long result = day06.processPartTwo("day06.txt");
        Assertions.assertEquals(29891250, result);
    }

}
