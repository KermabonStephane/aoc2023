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
        AtomicLong result = new AtomicLong(0);
        AtomicLong line = new AtomicLong(0);
        conditionRecords.stream().forEach(c -> {
            long simple = c.generateAllPossibilities().stream().filter(c2 -> c2.match()).count();
//            long two = c.transform().generateAllPossibilities().stream().filter(c2 -> c2.match()).count();
            result.addAndGet(simple);
//            System.out.println(c.records+"?"+c.records+"?"+c.records+"?"+c.records+"?"
//                    + c.records+" "
//                    + String.join(",", Arrays.stream(c.format).toList().stream().map(i -> ""+i).collect(Collectors.toList()))
//                    +","
//                    + String.join(",", Arrays.stream(c.format).toList().stream().map(i -> ""+i).collect(Collectors.toList()))
//                    +","
//                    + String.join(",", Arrays.stream(c.format).toList().stream().map(i -> ""+i).collect(Collectors.toList()))                    +","
//                    + String.join(",", Arrays.stream(c.format).toList().stream().map(i -> ""+i).collect(Collectors.toList()))                    +","
//                    + String.join(",", Arrays.stream(c.format).toList().stream().map(i -> ""+i).collect(Collectors.toList())));
//            System.out.println(line.addAndGet(1) + ";" + simple + ";" + two + ";" + (long)(simple * Math.pow((two / simple), 4.0)) + ";;");
            System.out.println(line.addAndGet(1) + ":" + simple);
        });

        return result.get();
    }

    public long processPartTwo(String s) throws IOException {
        List<ConditionRecord> conditionRecords = process(s);
        AtomicLong result = new AtomicLong(0);
        AtomicLong line = new AtomicLong(0);
        conditionRecords.stream().forEach(c -> {
            long simple = c.transform().generateAllPossibilities().stream().filter(c2 -> c2.match()).count();
//            long two = c.transform().generateAllPossibilities().stream().filter(c2 -> c2.match()).count();
            result.addAndGet(simple);
//            System.out.println(c.records+"?"+c.records+"?"+c.records+"?"+c.records+"?"
//                    + c.records+" "
//                    + String.join(",", Arrays.stream(c.format).toList().stream().map(i -> ""+i).collect(Collectors.toList()))
//                    +","
//                    + String.join(",", Arrays.stream(c.format).toList().stream().map(i -> ""+i).collect(Collectors.toList()))
//                    +","
//                    + String.join(",", Arrays.stream(c.format).toList().stream().map(i -> ""+i).collect(Collectors.toList()))                    +","
//                    + String.join(",", Arrays.stream(c.format).toList().stream().map(i -> ""+i).collect(Collectors.toList()))                    +","
//                    + String.join(",", Arrays.stream(c.format).toList().stream().map(i -> ""+i).collect(Collectors.toList())));
//            System.out.println(line.addAndGet(1) + ";" + simple + ";" + two + ";" + (long)(simple * Math.pow((two / simple), 4.0)) + ";;");
            System.out.println(line.addAndGet(1) + ":" + simple);
        });

        return result.get();
    }

    private List<ConditionRecord> process(final String filename) throws IOException {
        List<ConditionRecord> conditionRecords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            while (line != null) {
                String[] s = line.split(" ");
                ConditionRecord conditionRecord = new ConditionRecord(s[0].replaceAll("\\.\\.", ".").replaceAll("\\.\\.", ".").replaceAll("\\.\\.", "."),
                        Arrays.stream(s[1].split(",")).map(Long::valueOf).toList().toArray(Long[]::new), true);
                conditionRecords.add(conditionRecord);
                line = reader.readLine();
            }
        }
        return conditionRecords;
    }

}
