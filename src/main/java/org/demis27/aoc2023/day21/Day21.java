package org.demis27.aoc2023.day21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Day21 {

    public int rowStart = 0;
    public int columnStart = 0;
    public long processPartOne(String s) throws IOException {
        char[][] garden = process(s);
        Stack<Plot> plots = new Stack<>();


        return 0L;
    }

    public long processPartTwo(String s) throws IOException {
        process(s);
        return 0L;
    }

    private char[][] process(final String filename) throws IOException {
        char[][] garden;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            garden = new char[line.length()][line.length()];
            int row = 0;
            while (line != null) {
                garden[row] = line.toCharArray();
                if (line.contains("S")) {
                    rowStart = row;
                    columnStart = line.indexOf("S");
                }
                line = reader.readLine();
                row++;
            }
        }
        return garden;
    }

}
