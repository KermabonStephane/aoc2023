package org.demis27.aoc2023.day20;

import org.demis27.aoc2023.day20.Day20;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day20Test {

    @Test
    void day20_part1_sample_1() throws IOException {
        Day20 day20 = new Day20();
        long result = day20.processPartOne("day20-part1-sample1.txt");
        Assertions.assertEquals(32000000, result);
    }

    @Test
    void day20_part1_sample_2() throws IOException {
        Day20 day20 = new Day20();
        long result = day20.processPartOne("day20-part1-sample2.txt");
        Assertions.assertEquals(11687500, result);
    }

    @Test
    void day20_part1() throws IOException {
        Day20 day20 = new Day20();
        long result = day20.processPartOne("day20.txt");
        Assertions.assertEquals(763500168L, result);
    }

    @Test
    void day20_part2_sample() throws IOException {
        Day20 day20 = new Day20();
        long result = day20.processPartTwo("day20-part2-sample.txt");
        Assertions.assertEquals(0, result);
    }
    @Test
    void day20_part2() throws IOException {
        Day20 day20 = new Day20();
        long result = day20.processPartTwo("day20.txt");
        Assertions.assertEquals(0, result);
    }

    @Test
    void day20_part2_dv() throws IOException {
        Day20 day20 = new Day20();
        long result = day20.processPartTwo("day20-dv.txt");
        Assertions.assertEquals(0, result);
    }

    @Test
    void day20_part2_lf() throws IOException {
        Day20 day20 = new Day20();
        long result = day20.processPartTwo("day20-lf.txt");
        Assertions.assertEquals(0, result);
    }
}
