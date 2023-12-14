package org.demis27.aoc2023.day14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day14 {
    public long processPartOne(String s) throws IOException {
        List<String> columns = process(s);
        List<String> toNorth = columns.stream().map(c -> moveNorth(c)).collect(Collectors.toList());
        return toNorth.stream().map(c -> valueOfColumn(c)).collect(Collectors.summarizingLong(l -> l)).getSum();
    }

    private List<String> turn(List<String> toNorth) {
        return toNorth;
    }

    public String moveNorth(String column) {
        int max = column.length();
        int pos = 0;
        while (pos < max) {
            if (column.charAt(pos)== 'O' && pos > 0 && column.charAt(pos-1) == '.') {
                column = column.substring(0, pos-1) + "O." + column.substring(pos+1);
                pos--;
            }
            else  {
                pos++;
            }
        }
        return column;
    }

    public long valueOfColumn(String column) {
        long value = 0;
        for (int i = column.length(); i > 0; i--) {
            if (column.charAt(column.length() - i) == 'O') {
                value += i;
            }
        }
        return value;
    }

    public List<String> splitColumn(String column) {
        List<String> split = Arrays.stream(column.split("##")).filter(s -> !s.isEmpty())
                .map(s -> "##" + s).toList();
//        System.out.println("Split column " + column + " to " + split);
        return split;
    }

    public long processPartTwo(String s) throws IOException {
        List<String> columns = process(s);
        List<String> toNorth = columns.stream().map(c -> moveNorth(c)).collect(Collectors.toList());
        return toNorth.stream().map(c -> valueOfColumn(c)).collect(Collectors.summarizingLong(l -> l)).getSum();
    }

    private List<String> process(final String filename) throws IOException {
        List<StringBuffer> columns = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            for (int i = 0; i < line.length(); i++) {
                columns.add(new StringBuffer());
            }
            while (line != null) {
                for (int i = 0; i < line.length(); i++) {
                    columns.get(i).append(line.charAt(i));
                }
                line = reader.readLine();
            }
        }
        return columns.stream().map(b -> b.toString()).toList();
    }

}
