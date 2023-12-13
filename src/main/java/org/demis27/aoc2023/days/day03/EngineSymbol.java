package org.demis27.aoc2023.days.day03;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EngineSymbol {
    public EngineSymbol(int lineNumber, int position, char symbol) {
        this.lineNumber = lineNumber;
        this.position = position;
        this.symbol = symbol;
    }

    int lineNumber;
    int position;
    char symbol;

    List<EngineNumber> numbers = new ArrayList<>();

    @Override
    public String toString() {
        return "EngineSymbol{" +
                "lineNumber=" + lineNumber +
                ", position=" + position +
                ", symbol=" + symbol +
                ", gear=" + gear() +
                ", numbers=" + numbers +
                '}';
    }

    public void addEngineNumber(EngineNumber n) {
        numbers.add(n);
    }

    public long gear() {
        if (symbol != '*') {
            return 0L;
        }
        else {
            if (numbers.size() == 0) {
                return 0;
            }
            else if (numbers.size() == 1) {
                return 0L;
            }
            else {
                return numbers.stream().map(n ->  n.value).reduce(1L, (a, b) -> a * b);
            }
        }
    }
}
