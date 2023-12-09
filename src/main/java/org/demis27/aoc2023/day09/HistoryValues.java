package org.demis27.aoc2023.day09;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
public class HistoryValues {

    private List<List<Long>> values = new ArrayList<>();

    public HistoryValues() {
    }

    public HistoryValues addValues(String line) {
        values.add(Arrays.stream(line.split(" ")).map(v -> Long.parseLong(v)).collect(Collectors.toList()));
        return this;
    }

    public Long getPrediction() {
        reduceInitialValues();

        IntStream.iterate(values.size() - 2, i -> i >= 0, i -> i - 1).forEach(i -> values.get(i).add(values.get(i + 1).get(values.get(i + 1).size() - 1) + values.get(i).get(values.get(i).size() - 1)));

        return values.get(0).get(values.get(0).size() - 1);
    }

    private void reduceInitialValues() {
        List<Long> reduce = values.get(0);
        while (!isNull(reduce)) {
            reduce = reduceLine(reduce);
            values.add(reduce);
        }
    }

    public Long getPast() {
        reduceInitialValues();

        IntStream.iterate(values.size() - 2, i -> i >= 0, i -> i - 1).forEach(i -> values.get(i).add(0, -values.get(i + 1).get(0) + values.get(i).get(0)));
        return values.get(0).get(0);
    }

    private List<Long> reduceLine(List<Long> values) {
        List<Long> result = new ArrayList<>();
        for (int i = 1; i < values.size(); i++) {
            result.add(values.get(i) - values.get(i - 1));
        }
        return result;
    }

    private boolean isNull(List<Long> values) {
        return values.stream().allMatch(e -> e.equals(0L));
    }
}
