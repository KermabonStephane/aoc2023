package org.demis27.aoc2023.day14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Day14 {
    public long processPartOne(String s) throws IOException {
        List<String> columns = readOne(s);
//        System.out.println(columns);
        List<String> toNorth = columns.stream().map(c -> move(c)).collect(Collectors.toList());
        return toNorth.stream().map(c -> valueOfColumn(c)).collect(Collectors.summarizingLong(l -> l)).getSum();
    }

    public long processPartOneV2(String s) throws IOException {
        char[][] data = readTwo(s);
        moveToNorth(data);
        printData(data);
        return processValue(data);
    }

    public String move(String column) {
        int max = column.length();
        int pos = 0;
        while (pos < max) {
            if (column.charAt(pos) == 'O' && pos > 0 && column.charAt(pos - 1) == '.') {
                column = column.substring(0, pos - 1) + "O." + column.substring(pos + 1);
                pos--;
            } else {
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

    public long processPartTwo(String s) throws IOException {
        char[][] data = readTwo(s);
//        printData(data);
//        System.out.println("Init " + processValue(data));
//        System.out.println("Start");
//        printData(data);
        for (int i = 0; i < 0; i++) {
            moveToNorth(data);
            moveToWest(data);
            moveToSouth(data);
            moveToEast(data);
        }
        List<Long> values = new ArrayList<>();
        for (long cycle = 0; cycle < 1000000000L - 4L; cycle++) {
            long previousValue = processValue(data);
            moveToNorth(data);
            moveToWest(data);
            moveToSouth(data);
            moveToEast(data);

            long value = processValue(data);
//            if (values.size() > 5 && value == values.get(0)) {
            if (values.contains(value) && values.indexOf(value) == values.indexOf(previousValue) - 1) {
                for (int v = 0; v < values.size(); v++) {
                    System.out.println(values.get(v));
                }
                return values.get((int) ((1000000000L) % values.size()));
            }
            values.add(value);
//                System.out.println(cycle + " " + value);
//            System.out.println(cycle+" 000 000");
        }
//        System.out.println("Move");
//        printData(data);
        return processValue(data);
    }

    public void printData(char[][] data) {
        for (int row = 0; row < data.length; row++) {
            for (int column = 0; column < data[0].length; column++) {
                System.out.print(data[row][column]);
            }
            System.out.println("");
        }
    }

    public void printData(Character[][] data) {
        for (int row = 0; row < data.length; row++) {
            for (int column = 0; column < data[0].length; column++) {
                System.out.print(data[row][column]);
            }
            System.out.println("");
        }
    }

    private long processValue(char[][] data) {
        long result = 0;
        for (int row = 0; row < data.length; row++) {
            for (int column = 0; column < data[0].length; column++) {
                if (data[row][column] == 'O') {
                    result += data.length - row;
                }
            }
        }
        return result;
    }

    private void moveToEast(char[][] data) {
        for (int row = 0; row < data.length; row++) {
            int pos = data.length - 1;
            int numberOfO = 0;
            int numberOfPoint = 0;
            int sharpPos = data.length;
            ;
            while (pos >= -1) {
                if (pos >= 0 && data[row][pos] != '#') {
                    if (data[row][pos] == '.') {
                        numberOfPoint++;
                    } else {
                        numberOfO++;
                    }
                    pos--;
                } else {
                    for (int o = 0; o < numberOfO; o++) {
                        data[row][sharpPos - 1 - o] = 'O';
                    }
                    for (int point = 0; point < numberOfPoint; point++) {
                        data[row][sharpPos - 1 - numberOfO - point] = '.';
                    }
                    numberOfO = 0;
                    numberOfPoint = 0;
                    sharpPos = pos;
                    pos--;
                }
            }
        }
    }

    private void moveToSouth(char[][] data) {
        for (int column = 0; column < data[0].length; column++) {
            int pos = 0;
            int numberOfO = 0;
            int numberOfPoint = 0;
            int sharpPos = -1;
            while (pos <= data.length) {
                if (pos < data.length && data[pos][column] != '#') {
                    if (data[pos][column] == '.') {
                        numberOfPoint++;
                    } else {
                        numberOfO++;
                    }
                    pos++;
                } else {
                    for (int point = 0; point < numberOfPoint; point++) {
                        data[sharpPos + 1 + point][column] = '.';
                    }
                    for (int o = 0; o < numberOfO; o++) {
                        data[sharpPos + 1 + +numberOfPoint + o][column] = 'O';
                    }
                    numberOfO = 0;
                    numberOfPoint = 0;
                    sharpPos = pos;
                    pos++;
                }
            }
        }
    }

    private void moveToWest(char[][] data) {
        for (int row = 0; row < data.length; row++) {
            int pos = 0;
            int numberOfO = 0;
            int numberOfPoint = 0;
            int sharpPos = -1;
            while (pos <= data.length) {
                if (pos <= data.length - 1 && data[row][pos] != '#') {
                    if (data[row][pos] == '.') {
                        numberOfPoint++;
                    } else {
                        numberOfO++;
                    }
                    pos++;
                } else {
                    for (int o = 0; o < numberOfO; o++) {
                        data[row][sharpPos + 1 + o] = 'O';
                    }
                    for (int point = 0; point < numberOfPoint; point++) {
                        data[row][sharpPos + 1 + numberOfO + point] = '.';
                    }
                    numberOfO = 0;
                    numberOfPoint = 0;
                    sharpPos = pos;
                    pos++;
                }
            }
        }
    }

    public Character[][] moveToWestWithComparator(Character[][] data) {
        for (int row = 0; row < data.length; row++) {
            data[row] = Arrays.stream(data[row]).sorted((c1, c2) -> {
                if (c1 == c2) return 0;
                if (c1 == '#' || c2 == '#') return 0;
                if (c1 == 'O' && c2 == '.') return 1;
                return -1;
            }).toArray(Character[]::new);
        }
        return data;
    }

    private void moveToNorth(char[][] data) {
        for (int column = 0; column < data[0].length; column++) {
            int pos = data.length - 1;
            int numberOfO = 0;
            int numberOfPoint = 0;
            int sharpPos = data.length;
            while (pos >= -1) {
                if (pos >= 0 && data[pos][column] != '#') {
                    if (data[pos][column] == '.') {
                        numberOfPoint++;
                    } else {
                        numberOfO++;
                    }
                    pos--;
                } else {
                    for (int point = 0; point < numberOfPoint; point++) {
                        data[sharpPos - 1 - point][column] = '.';
                    }
                    for (int o = 0; o < numberOfO; o++) {
                        data[sharpPos - 1 - numberOfPoint - o][column] = 'O';
                    }
                    numberOfO = 0;
                    numberOfPoint = 0;
                    sharpPos = pos;
                    pos--;
                }
            }
        }
    }

    private List<String> readOne(final String filename) throws IOException {
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

    private char[][] readTwo(final String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        }
        char[][] data = new char[lines.size()][lines.get(0).length()];
        for (int row = 0; row < lines.size(); row++) {
            for (int column = 0; column < lines.get(0).length(); column++) {
                data[row][column] = lines.get(row).charAt(column);
            }
        }
        return data;
    }

    public Character[][] readThree(final String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        }
        Character[][] data = new Character[lines.size()][lines.get(0).length()];
        for (int row = 0; row < lines.size(); row++) {
            for (int column = 0; column < lines.get(0).length(); column++) {
                data[row][column] = lines.get(row).charAt(column);
            }
        }
        return data;
    }
}
