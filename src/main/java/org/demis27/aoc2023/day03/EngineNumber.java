package org.demis27.aoc2023.day03;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EngineNumber {

    long value;
    int lineNumber;
    int startPosition;
    int endPosition;
    boolean valid = false;

    public EngineNumber(int value, int lineNumber, int startPosition) {
        this.value = value;
        this.lineNumber = lineNumber;
        this.startPosition = startPosition;
        this.endPosition = startPosition + ("" + value).length() - 1;
    }

    @Override
    public String toString() {
        return "EngineNumber{" +
                "value=" + value +
                ", lineNumber=" + lineNumber +
                ", startPosition=" + startPosition +
                ", endPosition=" + endPosition +
                ", valid=" + valid +
                '}';
    }
}
