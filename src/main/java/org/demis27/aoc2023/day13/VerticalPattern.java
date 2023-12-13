package org.demis27.aoc2023.day13;

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

    public int getNumber() {
        return getNumberInternal(patterns);
    }

    public int getNumberInternal(List<String> p) {
        int i = 0;
        int result = 0;
        boolean notFound = true;
        while (i < p.size() - 1 && notFound) {
            if (p.get(i).equals(p.get(i + 1)) && check(i, p)) {
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

    public boolean check(int start, List<String> p) {
        int cursor = start + 2;
        int reverseCursor = start - 1;
        while (cursor < p.size()  && reverseCursor >= 0) {
            if (!p.get(cursor).equals(p.get(reverseCursor))) {
                return false;
            }
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
