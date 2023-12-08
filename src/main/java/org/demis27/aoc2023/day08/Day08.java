package org.demis27.aoc2023.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
            } else {
                position = puzzleMap.getChoices().get(position).getValue1();
            }
            numberOfSteps++;
        }
        return numberOfSteps;
    }

    public long processPartTwo(String s) throws IOException {
        PuzzleMap puzzleMap = process(s);
        List<String> positions = puzzleMap.getAllStartPositions();
        long numberOfInstructions = puzzleMap.getInstruction().length();
        List<Long> results = new ArrayList<>();

        positions.forEach(position -> {
            long numberOfSteps = 0;
            while (position.charAt(2) != 'Z') {
                char instruction = puzzleMap.getInstruction().charAt((int) (numberOfSteps % numberOfInstructions));
                if (instruction == 'L') {
                    position = puzzleMap.getChoices().get(position).getValue0();
                } else {
                    position = puzzleMap.getChoices().get(position).getValue1();
                }
                numberOfSteps++;
            }
            results.add(numberOfSteps);
        });
        return lcm(results.toArray(new Long[positions.size()]));
    }

    private static long lcm(Long[] element_array)
    {
        long lcm_of_array_elements = 1;
        int divisor = 2;

        while (true) {
            int counter = 0;
            boolean divisible = false;

            for (int i = 0; i < element_array.length; i++) {

                // lcm_of_array_elements (n1, n2, ... 0) = 0.
                // For negative number we convert into
                // positive and calculate lcm_of_array_elements.

                if (element_array[i] == 0) {
                    return 0;
                }
                else if (element_array[i] < 0) {
                    element_array[i] = element_array[i] * (-1);
                }
                if (element_array[i] == 1) {
                    counter++;
                }

                // Divide element_array by devisor if complete
                // division i.e. without remainder then replace
                // number with quotient; used for find next factor
                if (element_array[i] % divisor == 0) {
                    divisible = true;
                    element_array[i] = element_array[i] / divisor;
                }
            }

            // If divisor able to completely divide any number
            // from array multiply with lcm_of_array_elements
            // and store into lcm_of_array_elements and continue
            // to same divisor for next factor finding.
            // else increment divisor
            if (divisible) {
                lcm_of_array_elements = lcm_of_array_elements * divisor;
            }
            else {
                divisor++;
            }

            // Check if all element_array is 1 indicate
            // we found all factors and terminate while loop.
            if (counter == element_array.length) {
                return lcm_of_array_elements;
            }
        }
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
