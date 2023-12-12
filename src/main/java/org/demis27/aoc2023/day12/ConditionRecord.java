package org.demis27.aoc2023.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ConditionRecord {

    public String records;

    public Long[] format;

    public long neededHashTag;

    public Pattern pattern;

    public ConditionRecord(String records, Long[] format, boolean withPattern) {
        this.records = records.replaceAll("\\.\\.", ".");
        this.format = format;
        neededHashTag = Arrays.stream(this.format).collect(Collectors.summarizingLong(i -> i)).getSum();
        if (withPattern) {
            StringBuilder regularExpression = new StringBuilder();
            regularExpression.append("[\\.\\?]*");
            for (int i = 0; i < format.length - 1; i++) {
                regularExpression.append("[\\#\\?]{");
                regularExpression.append(format[i]);
                regularExpression.append("}[\\.\\?]+");
            }
            regularExpression.append("[\\#\\?]{");
            regularExpression.append(format[format.length - 1]);
            regularExpression.append("}[\\.\\?]*");
            pattern = Pattern.compile(regularExpression.toString());
        }
    }

    public ConditionRecord(String records, Long[] format) {
        this(records, format, false);
    }

    public boolean match() {
        if (records.indexOf('?') >= 0) {
            return false;
        }
        if (records.replaceAll("\\.", "").length() != neededHashTag) {
            return false;
        }
        String[] split = records
                .replaceAll("\\.\\.", ".")
                .replaceAll("\\.\\.", ".")
                .replaceAll("\\.\\.", ".")
                .replaceAll("\\.\\.", ".")
                .replaceAll("\\.\\.", ".")
                .replaceAll("\\.\\.", ".")
                .replaceAll("\\.\\.", ".")
                .replaceAll("\\.\\.", ".")
                .split("\\.", -1);
        split = Arrays.stream(split).filter(s -> !s.isEmpty()).toArray(String[]::new);
        if (split.length != format.length) {
            return false;
        } else {
            boolean result = true;
            for (int i = 0; i < split.length; i++) {
                result = result && (split[i].length() == format[i]);
            }
            return result;
        }
    }

    public ConditionRecord transform() {
        String recordAsString = this.records + "?" + this.records + "?" + this.records + "?" + this.records + "?" + this.records;
        Long[] newFormat = new Long[this.format.length * 5];
        for (int i = 0; i < this.format.length; i++) {
            newFormat[i] = this.format[i];
            newFormat[i + this.format.length] = this.format[i];
            newFormat[i + (this.format.length * 2)] = this.format[i];
            newFormat[i + (this.format.length * 3)] = this.format[i];
            newFormat[i + (this.format.length * 4)] = this.format[i];
        }


        return new ConditionRecord(recordAsString, newFormat, true);
    }

    public List<ConditionRecord> generateAllPossibilities() {
        return recursiveGenerateAllPossibilities(pattern);
    }

    public List<ConditionRecord> recursiveGenerateAllPossibilities(Pattern pattern) {
        if (!pattern.matcher(records).find()) {
            return Collections.emptyList();
        }
        if (records.indexOf('?') < 0) {
            return List.of(this);
        }
        if (records.chars().filter(c -> c == '#').count() > neededHashTag) {
            return Collections.emptyList();
        } else {
            List<ConditionRecord> result = new ArrayList<>();
            List<ConditionRecord> first = (new ConditionRecord(records.replaceFirst("\\?", "."), format)).recursiveGenerateAllPossibilities(pattern);
            List<ConditionRecord> second = (new ConditionRecord(records.replaceFirst("\\?", "#"), format)).recursiveGenerateAllPossibilities(pattern);
            result.addAll(second);
            result.addAll(first);
            return result;
        }
    }
}
