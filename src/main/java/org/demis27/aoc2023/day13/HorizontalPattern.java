package org.demis27.aoc2023.day13;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class HorizontalPattern {
    @Override
    public String toString() {
        return "HorizontalPattern{" +
                "patterns=" + patterns +
                '}';
    }

    public List<String> patterns = new ArrayList<>();

    public VerticalPattern reverse() {
        List<StringBuffer> buffers = new ArrayList<>();
        for (int i = 0; i < patterns.get(0).length(); i++) {
            buffers.add(new StringBuffer());
        }
        for (int i = 0; i < patterns.size(); i++) {
            for (int j = 0; j < patterns.get(i).length(); j++) {
                buffers.get(j).append(patterns.get(i).charAt(j));
            }
        }
        VerticalPattern result = new VerticalPattern();
        buffers.stream().forEach(b -> result.patterns.add(b.toString()));
        return result;
    }

    public int getNumber(boolean part) {
        return getNumberInternal(patterns, part);
    }

    public int getNumberInternal(List<String> p, boolean part) {
        int i = 0;
        int result = 0;
        boolean notFound = true;
        while (i < p.size() - 1 && notFound) {
            Pair<Boolean, Boolean> equals = isEquals(p.get(i), p.get(i + 1), part);
            if (equals.getValue0() && check(i, p, part, part && equals.getValue1())) {
                notFound = false;
                result = i;
            } else {
                i++;
            }
        }
        if (notFound) {
            return 0;
        }
        return (result + 1) * 100;
    }

    private static Pair<Boolean, Boolean> isEquals(String one, String two, boolean part) {
        if (!part) {
            return new Pair<>(one.equals(two), false);
        }
        else {
            int i = Integer.parseInt(one, 2) ^ Integer.parseInt(two, 2);
            int numberDiff = Integer.toBinaryString(i).replaceAll("0", "").length();
            return new Pair<>(numberDiff < 2, numberDiff == 1);
        }
    }

    public boolean check(int start, List<String> p, boolean part, boolean smudge) {
        int cursor = start + 2;
        int reverseCursor = start - 1;
        while (cursor < p.size()  && reverseCursor >= 0) {
            Pair<Boolean, Boolean> equals = isEquals(p.get(cursor), p.get(reverseCursor), !smudge);
            if (equals.getValue0()) {
                return false;
            }
            cursor++;
            reverseCursor--;
        }
        return true;
    }

}
