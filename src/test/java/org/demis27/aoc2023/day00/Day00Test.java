package org.demis27.aoc2023.day00;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day00Test {

    @Test
    void day00_part1_sample() throws IOException {
        Day00 day00 = new Day00();
        long result = day00.processPartOne("day00-part1-sample.txt");
        Assertions.assertEquals(0, result);
    }

    @Test
    void day00_part2_sample() throws IOException {
        Day00 day00 = new Day00();
        long result = day00.processPartTwo("day00-part2-sample.txt");
        Assertions.assertEquals(0, result);
    }
    @Test
    void day00_part1() throws IOException {
        Day00 day00 = new Day00();
        long result = day00.processPartOne("day00.txt");
        Assertions.assertEquals(0, result);
    }

    @Test
    void day00_part2() throws IOException {
        Day00 day00 = new Day00();
        long result = day00.processPartTwo("day00.txt");
        Assertions.assertEquals(0, result);
    }

}
