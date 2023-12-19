package org.demis27.aoc2023.day19;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day19Test {

    @Test
    void day19_part1_sample() throws IOException {
        Day19 day19 = new Day19();
        long result = day19.processPartOne("day19-part1-sample.txt");
        Assertions.assertEquals(19114, result);
    }

    @Test
    void day19_part1() throws IOException {
        Day19 day19 = new Day19();
        long result = day19.processPartOne("day19.txt");
        Assertions.assertEquals(446935, result);
    }

    @Test
    void day19_part2_sample() throws IOException {
        Day19 day19 = new Day19();
        long result = day19.processPartTwo("day19-part2-sample.txt");
        Assertions.assertEquals(167409079868000L, result);
    }
    @Test
    void day19_part2() throws IOException {
        Day19 day19 = new Day19();
        long result = day19.processPartTwo("day19.txt");
        Assertions.assertEquals(0, result);
    }

}
