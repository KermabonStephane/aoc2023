package org.demis27.aoc2023.day12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day12Test {

    @Test
    void day12_part1_sample() throws IOException {
        Day12 day12 = new Day12();
        long result = day12.processPartOne("day12-part1-sample.txt");
        Assertions.assertEquals(21, result);
    }

    @Test
    void day12_part1_sample2() throws IOException {
        Day12 day12 = new Day12();
        long result = day12.processPartOne("day12-part2-sample2.txt");
        Assertions.assertEquals(21, result);
    }

    @Test
    void day12_part2_sample() throws IOException {
        Day12 day12 = new Day12();
        long result = day12.processPartTwo("day12-part2-sample.txt");
        Assertions.assertEquals(525152, result);
    }
    @Test
    void day12_part1() throws IOException {
        Day12 day12 = new Day12();
        long result = day12.processPartOne("day12.txt");
        Assertions.assertEquals(7732, result);
    }

    @Test
    void day12_part2() throws IOException {
        Day12 day12 = new Day12();
        long result = day12.processPartTwo("day12.txt");
        Assertions.assertEquals(0, result);
    }

    @Test
    void partialMatch() {
        Long[] format = new Long[4];
        format[0] = 2L;
        format[1] = 1L;
        format[2] = 2L;
        format[3] = 1L;
        ConditionRecord record = new ConditionRecord(".##.#.?.#.??", format);
        ConditionRecord result = record.reducePattern();
        Assertions.assertEquals(".#.?.#.??", result.records);
        Assertions.assertEquals(3, result.format.length);
        result = result.reducePattern();
        Assertions.assertEquals(".?.#.??", result.records);
        Assertions.assertEquals(2, result.format.length);

        record = new ConditionRecord(".##?.#.?.#.??", format);
        result = record.reducePattern();
        Assertions.assertNull(result);

        record = new ConditionRecord(".##.?.#.?.#.??", format);
        result = record.reducePattern();
        Assertions.assertEquals(".?.#.?.#.??", result.records);
        Assertions.assertEquals(3, result.format.length);

        record = new ConditionRecord(".##.?#.?.#.??", format);
        result = record.reducePattern();
        Assertions.assertEquals(".?#.?.#.??", result.records);
        Assertions.assertEquals(3, result.format.length);

        record = new ConditionRecord(".###.?#.?.#.??", format);
        result = record.reducePattern();
        Assertions.assertNull(result);
    }
}
