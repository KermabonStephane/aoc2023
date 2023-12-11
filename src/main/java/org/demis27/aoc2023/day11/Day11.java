package org.demis27.aoc2023.day11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day11 {
    public long processPartOne(String s) throws IOException {
        Map map = process(s);
        map.expand2();
        return map.galaxyPairs.stream()
                .map(pair -> Math.abs(pair.getValue0().getValue0() - pair.getValue1().getValue0())
                        +  Math.abs(pair.getValue0().getValue1() - pair.getValue1().getValue1()))
                .collect(Collectors.summarizingLong(i -> i)).getSum();
    }

    public long processPartTwo(String s) throws IOException {
        Map map = process(s);
        map.expand2(1000000 - 1);
        return map.galaxyPairs.stream()
                .map(pair -> Math.abs(pair.getValue0().getValue0() - pair.getValue1().getValue0())
                        +  Math.abs(pair.getValue0().getValue1() - pair.getValue1().getValue1()))
                .collect(Collectors.summarizingLong(i -> i)).getSum();
    }

    private Map process(final String filename) throws IOException {
        Map map = new Map();
        List<char[]> mapLine = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            while (line != null) {
                mapLine.add(line.toCharArray());
                line = reader.readLine();
            }
        }
        map.elements = new char[mapLine.size()][mapLine.get(0).length];
        for (int i = 0;  i < mapLine.size(); i++) {
            map.elements[i] = mapLine.get(i);
        }
        return map;
    }

}
