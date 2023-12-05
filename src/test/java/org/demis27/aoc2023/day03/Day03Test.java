package org.demis27.aoc2023.day03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Slf4j
public class Day03Test {

    @Test
    void day03_part1_sample() throws IOException {
        Day03 day03 = new Day03();
        long result = day03.processPartOne("day03-part1-sample.txt");
        Assertions.assertEquals(4361, result);
    }

    @Test
    void day03_part2_sample() throws IOException {
        Day03 day03 = new Day03();
        long result = day03.processPartTwo("day03-part2-sample.txt");
        Assertions.assertEquals(467835, result);
    }
    @Test
    void day03_part1() throws IOException {
        Day03 day03 = new Day03();
        long result = day03.processPartOne("day03.txt");
        Assertions.assertEquals(520135, result);
    }

    @Test
    void day03_part2() throws IOException {
        Day03 day03 = new Day03();
        long result = day03.processPartTwo("day03.txt");
        Assertions.assertEquals(72514855, result);
    }
}
