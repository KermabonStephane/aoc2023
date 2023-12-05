package org.demis27.aoc2023.day05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day05Test {

    @Test
    void day05_part1_sample() throws IOException {
        Day05 day05 = new Day05();
        long result = day05.processPartOne("day05-part1-sample.txt");
        Assertions.assertEquals(35, result);
    }

    @Test
    void day05_part2_sample() throws IOException {
        Day05 day05 = new Day05();
        long result = day05.processPartTwo("day05-part2-sample.txt");
        Assertions.assertEquals(46, result);
    }
    @Test
    void day05_part1() throws IOException {
        Day05 day05 = new Day05();
        long result = day05.processPartOne("day05.txt");
        Assertions.assertEquals(579439039, result);
    }

    @Test
    void day05_part2() throws IOException {
        Day05 day05 = new Day05();
        long result = day05.processPartTwo("day05.txt");
        Assertions.assertEquals(7873084, result);
    }

}
