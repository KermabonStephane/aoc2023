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

    public boolean partialMatch() {
        String startRecord = records.substring(0, records.indexOf('?') >= 0 ? records.indexOf('?') : records.length());
        String[] split = startRecord.split("\\.");
        split = Arrays.stream(split).filter(s -> !s.isEmpty()).toArray(String[]::new);

        boolean result = true;
        if (split.length > format.length) {
//            //System.out.println("false " + records);
            return false;
        }
        for (int i = 0; i < split.length; i++) {
            if (split[i].length() > format[i]) {
//                //System.out.println("false " + records);
                return false;
            }
            result = result && (split[i].length() <= format[i]);
        }
        //System.out.println(result + " " + records);
        return result;
    }

    public boolean match() {
        if (records.indexOf('?') >= 0) {
            //System.out.println(false + " " + records);
            return false;
        }
        if (records.replaceAll("\\.", "").length() != neededHashTag) {
            //System.out.println(false + " " + records);
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
            //System.out.println(false + " " + records);
            return false;
        } else {
            boolean result = true;
            for (int i = 0; i < split.length; i++) {
                result = result && (split[i].length() == format[i]);
            }
            //System.out.println(result + " " + records);
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
        //System.out.println("start " + records);
        List<ConditionRecord> conditionRecords = recursiveGenerateAllPossibilities(pattern);
        return conditionRecords;
    }

    public List<ConditionRecord> recursiveGenerateAllPossibilities(Pattern pattern) {
//        System.out.print("*");
        if (records.indexOf('?') < 0) {
            if (match()) {
                return List.of(this);
            }
            else {
                return Collections.emptyList();
            }
        }
        if (!partialMatch()) {
            return Collections.emptyList();
        }
        if (records.chars().filter(c -> c == '#').count() > neededHashTag) {
            return Collections.emptyList();
        } else {
            List<ConditionRecord> result = new ArrayList<>();
            List<ConditionRecord> second = (new ConditionRecord(records.replaceFirst("\\?", "#"), format)).recursiveGenerateAllPossibilities(pattern);
            List<ConditionRecord> first = (new ConditionRecord(records.replaceFirst("\\?", "."), format)).recursiveGenerateAllPossibilities(pattern);
            result.addAll(first);
            result.addAll(second);
            return result;
        }
    }
}
