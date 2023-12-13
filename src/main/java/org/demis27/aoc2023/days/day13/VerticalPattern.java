package org.demis27.aoc2023.days.day13;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class VerticalPattern {

    public List<String> patterns = new ArrayList<>();

    @Override
    public String toString() {
        return "VerticalPattern{" +
                "patterns=" + patterns +
                '}';
    }

    public int getNumber(boolean withSmudge) {
        return getNumberInternal(patterns, withSmudge, withSmudge ? getNumberInternal(patterns, false, -1) : -1);
    }

    public int getNumberInternal(List<String> p, boolean withSmudge, int oldValue) {
        int i = 0;
        int result = 0;
        boolean notFound = true;
        while (i < p.size() - 1 && notFound) {
            Pair<Boolean, Boolean> equals = linesEquals(p.get(i), p.get(i + 1), withSmudge, false);
            if ((!withSmudge && equals.getValue0() && check(i, p, withSmudge, equals.getValue1()))
                    || (withSmudge && equals.getValue0() && (i + 1 != oldValue || oldValue == 0) && check(i, p, withSmudge, equals.getValue1()))
            ) {
                notFound = false;
                result = i;
            } else {
                i++;
            }
        }
        if (notFound) {
            return 0;
        }
        return result + 1;
    }

    public static Pair<Boolean, Boolean> linesEquals(String one, String two, boolean withSmudge, boolean smudgeAlreadyFound) {
        if (!withSmudge) {
            return new Pair<>(one.equals(two), false);
        } else if (smudgeAlreadyFound) {
            return new Pair<>(one.equals(two), false);
        } else {
            int i = Integer.parseInt(one, 2) ^ Integer.parseInt(two, 2);
            int numberDiff = Integer.toBinaryString(i).replaceAll("0", "").length();
            return new Pair<>(numberDiff < 2, numberDiff == 1);
        }
    }

    public boolean check(int start, List<String> p, boolean withSmudge, boolean smudgeAlreadyFound) {
        int cursor = start + 2;
        int reverseCursor = start - 1;
        while (cursor < p.size() && reverseCursor >= 0) {
            Pair<Boolean, Boolean> equals = linesEquals(p.get(cursor), p.get(reverseCursor), withSmudge, smudgeAlreadyFound);
            if (smudgeAlreadyFound && equals.getValue1()) {
                return false;
            }
            else if (!equals.getValue0()) {
                return false;
            }
            smudgeAlreadyFound = equals.getValue1();
            cursor++;
            reverseCursor--;
        }
        return true;
    }

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
}
