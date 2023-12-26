package org.demis27.aoc2023.day21;

import org.demis27.aoc2023.day21.Day21;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day21Test {

    @Test
    void day21_part1_sample() throws IOException {
        Day21 day21 = new Day21();
        long result = day21.processPartOne("day21-part1-sample.txt");
        Assertions.assertEquals(64, result);
    }

    @Test
    void day21_part1() throws IOException {
        Day21 day21 = new Day21();
        long result = day21.processPartOne("day21.txt");
        Assertions.assertEquals(0, result);
    }

    @Test
    void day21_part2_sample() throws IOException {
        Day21 day21 = new Day21();
        long result = day21.processPartTwo("day21-part2-sample.txt");
        Assertions.assertEquals(0, result);
    }
    @Test
    void day21_part2() throws IOException {
        Day21 day21 = new Day21();
        long result = day21.processPartTwo("day21.txt");
        Assertions.assertEquals(0, result);
    }

}
