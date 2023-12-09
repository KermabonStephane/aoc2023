package org.demis27.aoc2023.day09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day09 {
    public long processPartOne(String s) throws IOException {
        List<HistoryValues> historyValues = process(s);

        return historyValues.stream().map(HistoryValues::getPrediction).collect(Collectors.summarizingLong(i -> i)).getSum();
    }

    public long processPartTwo(String s) throws IOException {
        List<HistoryValues> historyValues = process(s);

        return historyValues.stream().map(HistoryValues::getPast).collect(Collectors.summarizingLong(i -> i)).getSum();
    }

    private List<HistoryValues> process(final String filename) throws IOException {
        List<HistoryValues> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            while (line != null) {
                HistoryValues values = (new HistoryValues()).addValues(line);
                result.add(values);
                line = reader.readLine();
            }
        }
        return result;
    }

}
