package org.demis27.aoc2023.days.day14;

import org.demis27.aoc2023.days.day14.Day14;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day14Test {

    @Test
    void day14_part1_sample() throws IOException {
        Day14 day14 = new Day14();
        long result = day14.processPartOne("day14-part1-sample.txt");
        Assertions.assertEquals(136, result);
    }

    @Test
    void day14_part1_sample_v2() throws IOException {
        Day14 day14 = new Day14();
        long result = day14.processPartOneV2("day14-part1-sample.txt");
        Assertions.assertEquals(136, result);
    }
    @Test
    void day14_part1_sample_sorted() throws IOException {
        Day14 day14 = new Day14();
        Character[][] characters = day14.readThree("day14-part1-sample.txt");
        day14.printData(characters);
        System.out.println("\n West \n");
        day14.printData(day14.moveToWestWithComparator(characters));
        long result = day14.processPartOne("day14-part1-sample.txt");
        Assertions.assertEquals(136, result);
    }

    @Test
    void day14_part1() throws IOException {
        Day14 day14 = new Day14();
        long result = day14.processPartOne("day14.txt");
        Assertions.assertEquals(106990, result);
    }

    @Test
    void day14_part2_sample() throws IOException {
        Day14 day14 = new Day14();
        long result = day14.processPartTwo("day14-part2-sample.txt");
        Assertions.assertEquals(64, result);
    }
    @Test
    void day14_part2() throws IOException {
        Day14 day14 = new Day14();
        long result = day14.processPartTwo("day14.txt");
        Assertions.assertEquals(100531, result);
    }

}
