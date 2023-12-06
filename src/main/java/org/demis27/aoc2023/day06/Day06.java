package org.demis27.aoc2023.day06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day06 {
    public long processPartOne(String s) throws IOException {
        List<Race> races = process(s);
        List<Long> list = races.stream().map(Race::numerbOfPossibilities).toList();
        long result = 1;
        for (int i = 0; i < list.size(); i++) {
            result = result * list.get(i);
        }
        return result;
    }

    public long processPartTwo(String s) throws IOException {
        return  process2(s).numerbOfPossibilities();
    }

    private List<Race> process(final String filename) throws IOException {
        List<Race> races = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String[] split1 = reader.readLine().split(" ");
            List<Long> times = Arrays.stream(split1).map(time -> time.replaceAll("[a-zA-Z:]", "")).filter(time -> !time.trim().isEmpty()).map(time ->Long.parseLong(time.trim())).toList();

            String[] split2 = reader.readLine().split(" ");
            List<Long> records = Arrays.stream(split2).map(record -> record.replaceAll("[a-zA-Z:]", "")).filter(resord -> !resord.trim().isEmpty()).map(record -> Long.parseLong(record.trim())).toList();

            for (int i = 0; i < times.size(); i++) {
                races.add(new Race(times.get(i), records.get(i)));
            }
        }
        return races;
    }

    private Race process2(final String filename) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            long time = Long.parseLong(reader.readLine().replaceAll("[a-zA-Z: ]", ""));
            long record = Long.parseLong(reader.readLine().replaceAll("[a-zA-Z: ]", ""));
            return  new Race(time, record);
        }
    }
}
