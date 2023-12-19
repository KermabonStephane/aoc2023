package org.demis27.aoc2023.day19;

import java.util.Objects;

public class PartRange {

    public long start;

    public long end;

    public PartRange(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PartRange partRange)) return false;
        return start == partRange.start && end == partRange.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "(" + start +
                "to " + end +
                ')';
    }
}

