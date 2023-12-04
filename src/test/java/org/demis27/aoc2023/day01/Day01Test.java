package org.demis27.aoc2023.day01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class Day01Test {

    @Test
    void day1() throws IOException {
        Day01 day01 = new Day01();
        long result = day01.getResult("day1.txt");
        Assertions.assertEquals(54676, result);
    }
}