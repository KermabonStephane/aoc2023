package org.demis27.aoc2023.day07;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day07Test {

    @Test
    void day07_part1_sample() throws IOException {
        Day07 day07 = new Day07();
        long result = day07.processPartOne("day07-part1-sample.txt");
        Assertions.assertEquals(35, result);
    }

    @Test
    void day07_part2_sample() throws IOException {
        Day07 day07 = new Day07();
        long result = day07.processPartTwo("day07-part2-sample.txt");
        Assertions.assertEquals(46, result);
    }
    @Test
    void day07_part1() throws IOException {
        Day07 day07 = new Day07();
        long result = day07.processPartOne("day07.txt");
        Assertions.assertEquals(579439039, result);
    }

    @Test
    void day07_part2() throws IOException {
        Day07 day07 = new Day07();
        long result = day07.processPartTwo("day07.txt");
        Assertions.assertEquals(7873084, result);
    }

}
