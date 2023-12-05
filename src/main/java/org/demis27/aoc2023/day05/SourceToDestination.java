package org.demis27.aoc2023.day05;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SourceToDestination {

    private long sourceRangeStart;

    private long destinationRangeStart;

    private long rangeLength;

    public static SourceToDestination read(String line) {
        String[] elements = line.split(" ");
        return new SourceToDestination(Long.parseLong(elements[1]), Long.parseLong(elements[0]), Long.parseLong(elements[2]));
    }

    public boolean concerned(long source) {
        return source >= sourceRangeStart && source < sourceRangeStart + rangeLength;
    }

    @Override
    public String toString() {
        return "SourceToDestination{" +
                "sourceRangeStart=" + sourceRangeStart +
                ", destinationRangeStart=" + destinationRangeStart +
                ", rangeLength=" + rangeLength +
                '}';
    }

    public long getDestination(long tmp) {
        return tmp - sourceRangeStart + destinationRangeStart;
    }
}
