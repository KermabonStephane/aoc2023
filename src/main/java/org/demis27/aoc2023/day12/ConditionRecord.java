package org.demis27.aoc2023.day12;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConditionRecord {

    public String records;

    public String format;

    public int numberOfFormat;

    public ConditionRecord(String records, String format) {
        this.records = records;
        this.format = format;
        this.numberOfFormat = format.split(",").length;
    }

    public ConditionRecord transform() {
        String recordAsString = this.records + "?" + this.records + "?" + this.records + "?" + this.records + "?" + this.records;
        String newFormat = format + format + format + format + format;

        return new ConditionRecord(recordAsString, newFormat);
    }

    public List<ConditionRecord> generateAllPossibilities() {
        if (records.indexOf('?') < 0) {
            return List.of(this);
        } else {
            long partialMatches = partialMatch();
            if (partialMatches > 0) {
                if (numberOfFormat - partialMatches == 0) {
                    return List.of(this);
                }
                String newFormat = format;
                for (int i = 0; i < partialMatches; i++) {
                    if (newFormat.isEmpty()) {
                        return List.of(this);
                    }
                    newFormat = newFormat.substring(format.indexOf(",") + 1);
                }
                List<ConditionRecord> result = new ArrayList<>();
                List<ConditionRecord> first = (new ConditionRecord(records.substring(records.indexOf("?")).replaceFirst("\\?", "."), newFormat)).generateAllPossibilities();
                List<ConditionRecord> second = (new ConditionRecord(records.substring(records.indexOf("?")).replaceFirst("\\?", "#"), newFormat)).generateAllPossibilities();
                result.addAll(second);
                result.addAll(first);
                return result;
            } else if (partialMatches == 0) {
                List<ConditionRecord> result = new ArrayList<>();
                List<ConditionRecord> first = (new ConditionRecord(records.replaceFirst("\\?", "."), format)).generateAllPossibilities();
                List<ConditionRecord> second = (new ConditionRecord(records.replaceFirst("\\?", "#"), format)).generateAllPossibilities();
                result.addAll(second);
                result.addAll(first);
                return result;
            } else {
                return Collections.emptyList();
            }
        }
    }

    private long partialMatch() {
        String start = records.substring(0, records.indexOf("?"));
        if (start.isEmpty() || start.charAt(start.length() - 1) != '.' || !start.contains("#")) {
            return 0L;
        }
        boolean result = format.startsWith(Arrays.stream(start.split("\\.")).filter(record -> !record.isEmpty()).map(record -> String.valueOf(record.length())).collect(Collectors.joining(",")));
        return result ? Arrays.stream(start.split("\\.")).filter(record -> !record.isEmpty()).count() : -1;
    }

    public boolean match() {
        String match = Arrays.stream(records.split("\\.")).filter(record -> !record.isEmpty()).map(record -> String.valueOf(record.length())).collect(Collectors.joining(","));
        return match.equals(format);
    }
}
