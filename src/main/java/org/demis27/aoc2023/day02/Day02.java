package org.demis27.aoc2023.day02;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Day02 {

    public long processPartOne(final String filename) throws IOException {
        long sum = getLines(filename).stream().filter(l -> l.isValid(12, 13, 14)).collect(Collectors.summarizingInt(l -> l.id)).getSum();
        log.info("Day 02, part 1 : {}", sum);
        return sum;
    }

    public long processPartTwo(final String filename) throws IOException {
        long sum = getLines(filename).stream().map(l -> l.minimum()).map(p -> p.blue * p.red * p.green).collect(Collectors.summarizingInt(i -> i)).getSum();
        log.info("Day 02, part 2 : {}", sum);
        return sum;
    }

    private List<Line> getLines(final String filename) throws IOException {
        List<Line> lines = new ArrayList<>(100);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            while (line != null) {
                if (line != null && line.length() > 0) {
                    lines.add(Line.readLine(line));
                }
                line = reader.readLine();
            }
        }
        return lines;
    }
}
