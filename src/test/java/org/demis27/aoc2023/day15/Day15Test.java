package org.demis27.aoc2023.day15;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day15Test {

    @Test
    void day15_part1_sample() throws IOException {
        Day15 day15 = new Day15();
        long result = day15.processPartOne("day15-part1-sample.txt");
        Assertions.assertEquals(0, result);
    }

    @Test
    void day15_part1() throws IOException {
        Day15 day15 = new Day15();
        long result = day15.processPartOne("day15.txt");
        Assertions.assertEquals(0, result);
    }

    @Test
    void day15_part2_sample() throws IOException {
        Day15 day15 = new Day15();
        long result = day15.processPartTwo("day15-part2-sample.txt");
        Assertions.assertEquals(0, result);
    }
    @Test
    void day15_part2() throws IOException {
        Day15 day15 = new Day15();
        long result = day15.processPartTwo("day15.txt");
        Assertions.assertEquals(0, result);
    }

}
