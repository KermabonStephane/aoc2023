package org.demis27.aoc2023.day13;

import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 {
    public long processPartOne(String s) throws IOException {
        List<HorizontalPattern> patterns = process(s);
        return patterns.stream().map(p -> p.getNumber(false)).collect(Collectors.summarizingLong(i -> i)).getSum()
                + patterns.stream().map(p -> p.reverse()).map(p -> p.getNumber(false)).collect(Collectors.summarizingLong(i -> i)).getSum();
    }

    public long processPartTwo(String s) throws IOException {
        List<HorizontalPattern> patterns = process(s);
        return patterns.stream().map(p -> p.getNumber(true)).collect(Collectors.summarizingLong(i -> i)).getSum()
                + patterns.stream().map(p -> p.reverse()).map(p -> p.getNumber(true)).collect(Collectors.summarizingLong(i -> i)).getSum();
    }

    private List<HorizontalPattern> process(final String filename) throws IOException {
        List<HorizontalPattern> patterns = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            while (line != null) {
                HorizontalPattern tmp = new HorizontalPattern();
                while (line != null && !line.isEmpty()) {
                    tmp.patterns.add(line.replaceAll("\\.", "0").replace("#", "1"));
                    line = reader.readLine();
                }
                patterns.add(tmp);
                line = reader.readLine();
            }
        }
        return patterns;
    }

}
