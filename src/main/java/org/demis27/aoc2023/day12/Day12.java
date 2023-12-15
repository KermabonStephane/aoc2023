package org.demis27.aoc2023.day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class Day12 {
    public long processPartOne(String s) throws IOException {
        List<ConditionRecord> conditionRecords = process(s);
        return conditionRecords.stream()
                .flatMap(conditionRecord -> conditionRecord.generateAllPossibilities().stream())
                .filter(conditionRecord -> conditionRecord.match())
                .count();
    }

    public long processPartTwo(String s) throws IOException {
        List<ConditionRecord> conditionRecords = process(s);
        return 0;
    }

    private List<ConditionRecord> process(final String filename) throws IOException {
        List<ConditionRecord> conditionRecords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            while (line != null) {
                String[] s = line.split(" ");
                ConditionRecord conditionRecord = new ConditionRecord(s[0], s[1]);
                conditionRecords.add(conditionRecord);
                line = reader.readLine();
            }
        }
        return conditionRecords;
    }

}
