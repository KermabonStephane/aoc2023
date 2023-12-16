package org.demis27.aoc2023.day16;

import org.demis27.aoc2023.day16.Day16;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day16Test {

    @Test
    void day16_part1_sample() throws IOException {
        Day16 day16 = new Day16();
        long result = day16.processPartOne("day16-part1-sample.txt");
        day16.printUpdated();
        Assertions.assertEquals(46, result);
    }

    @Test
    void day16_part1() throws IOException {
        Day16 day16 = new Day16();
        long result = day16.processPartOne("day16.txt");
        Assertions.assertEquals(7884, result);
    }

    @Test
    void day16_part2_sample() throws IOException {
        Day16 day16 = new Day16();
        long result = day16.processPartTwo("day16-part2-sample.txt");
        Assertions.assertEquals(51, result);
    }
    @Test
    void day16_part2() throws IOException {
        Day16 day16 = new Day16();
        long result = day16.processPartTwo("day16.txt");
        Assertions.assertEquals(0, result);
    }

}
