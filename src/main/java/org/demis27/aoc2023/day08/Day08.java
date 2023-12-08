package org.demis27.aoc2023.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Day08 {
    public long processPartOne(String s) throws IOException {
        PuzzleMap puzzleMap = process(s);
        String position = "AAA";
        long numberOfSteps = 0;
        while (!position.equals("ZZZ")) {
            char instruction = puzzleMap.getInstruction().charAt((int) (numberOfSteps % puzzleMap.getInstruction().length()));
            if (instruction == 'L') {
                position = puzzleMap.getChoices().get(position).getValue0();
            }
            else {
                position = puzzleMap.getChoices().get(position).getValue1();
            }
            numberOfSteps++;
        }
        return numberOfSteps;
    }

    public long processPartTwo(String s) throws IOException {
        PuzzleMap puzzleMap = process(s);
        List<String> positions = puzzleMap.getAllStartPositions();
        long numberOfSteps = 0;
        long numberOfInstructions = puzzleMap.getInstruction().length();
        while (!destinationIsGood(positions)) {
            char instruction = puzzleMap.getInstruction().charAt((int) (numberOfSteps % numberOfInstructions));
            if (instruction == 'L') {
                positions = positions.stream().map(p -> puzzleMap.getChoices().get(p).getValue0()).toList();
            }
            else {
                positions = positions.stream().map(p -> puzzleMap.getChoices().get(p).getValue1()).toList();
            }
            numberOfSteps++;
        }
        return numberOfSteps;
    }

    private boolean destinationIsGood(List<String> positions) {
        return positions.stream().allMatch(p -> p.charAt(2) == 'Z');
    }

    private PuzzleMap process(final String filename) throws IOException {
        PuzzleMap puzzleMap = new PuzzleMap();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            puzzleMap.setInstruction(line);
            line = reader.readLine();
            while (line != null) {
                if (!line.isEmpty()) {
                    puzzleMap.addChoice(line);
                }
                line = reader.readLine();
            }
        }
        return puzzleMap;
    }

}
