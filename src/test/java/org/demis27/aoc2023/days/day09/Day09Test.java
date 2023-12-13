package org.demis27.aoc2023.days.day09;

import org.demis27.aoc2023.days.day09.Day09;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day09Test {

    @Test
    void day09_part1_sample() throws IOException {
        Day09 day09 = new Day09();
        long result = day09.processPartOne("day09-part1-sample.txt");
        Assertions.assertEquals(114, result);
    }

    @Test
    void day09_part2_sample() throws IOException {
        Day09 day09 = new Day09();
        long result = day09.processPartTwo("day09-part2-sample.txt");
        Assertions.assertEquals(2, result);
    }
    @Test
    void day09_part1() throws IOException {
        Day09 day09 = new Day09();
        long result = day09.processPartOne("day09.txt");
        Assertions.assertEquals(1995001648, result);
    }

    @Test
    void day09_part2() throws IOException {
        Day09 day09 = new Day09();
        long result = day09.processPartTwo("day09.txt");
        Assertions.assertEquals(988, result);
    }

}
