package org.demis27.aoc2023.day17;

import org.demis27.aoc2023.day17.Day17;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day17Test {

    @Test
    void day17_part1_sample() throws IOException, IllegalAccessException {
        Day17 day17 = new Day17();
        long result = day17.processPartOne("day17-part1-sample.txt");
        Assertions.assertEquals(102, result);
    }

    @Test
    void day17_part1() throws IOException, IllegalAccessException {
        Day17 day17 = new Day17();
        long result = day17.processPartOne("day17.txt");
        Assertions.assertEquals(0, result);
    }

    @Test
    void day17_part2_sample() throws IOException {
        Day17 day17 = new Day17();
        long result = day17.processPartTwo("day17-part2-sample.txt");
        Assertions.assertEquals(0, result);
    }
    @Test
    void day17_part2() throws IOException {
        Day17 day17 = new Day17();
        long result = day17.processPartTwo("day17.txt");
        Assertions.assertEquals(0, result);
    }

}
