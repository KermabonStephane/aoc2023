package org.demis27.aoc2023.day08;

import org.demis27.aoc2023.day08.Day08;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day08Test {

    @Test
    void day08_part1_sample() throws IOException {
        Day08 day08 = new Day08();
        long result = day08.processPartOne("day08-part1-sample.txt");
        Assertions.assertEquals(2, result);
    }

    @Test
    void day08_part1_sample2() throws IOException {
        Day08 day08 = new Day08();
        long result = day08.processPartOne("day08-part1-sample2.txt");
        Assertions.assertEquals(6, result);
    }

    @Test
    void day08_part2_sample() throws IOException {
        Day08 day08 = new Day08();
        long result = day08.processPartTwo("day08-part2-sample.txt");
        Assertions.assertEquals(6, result);
    }
    @Test
    void day08_part1() throws IOException {
        Day08 day08 = new Day08();
        long result = day08.processPartOne("day08.txt");
        Assertions.assertEquals(14681, result);
    }

    @Test
    void day08_part2() throws IOException {
        Day08 day08 = new Day08();
        long result = day08.processPartTwo("day08.txt");
        Assertions.assertEquals(0, result);
    }

}
