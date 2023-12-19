package org.demis27.aoc2023.days.day18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day18 {
    public long processPartOne(String s) throws IOException {
        List<DigPlanLine> plan = process(s);
        char[][] map = new char[plan.size() * 2][plan.size() * 2];
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map.length; column++) {
                map[row][column] = '.';
            }
        }
        int currentRow = plan.size() / 2;
        int currentColumn = plan.size() / 2;
        map[currentRow][currentColumn] = '#';

        for (int line = 0; line < plan.size(); line++) {
            DigPlanLine digPlanLine = plan.get(line);
            switch (digPlanLine.direction) {
                case 'D': {
                    for (int row = 0; row < digPlanLine.number; row++) {
                        map[currentRow + row][currentColumn] = '#';
                    }
                    currentRow += digPlanLine.number;
                    break;
                }
                case 'U': {
                    for (int row = 0; row < digPlanLine.number; row++) {
                        map[currentRow - row][currentColumn] = '#';
                    }
                    currentRow -= digPlanLine.number;
                    break;
                }
                case 'L': {
                    for (int column = 0; column < digPlanLine.number; column++) {
                        map[currentRow][currentColumn - column] = '#';
                    }
                    currentColumn -= digPlanLine.number;
                    break;
                }
                case 'R': {
                    for (int column = 0; column < digPlanLine.number; column++) {
                        map[currentRow][currentColumn + column] = '#';
                    }
                    currentColumn += digPlanLine.number;
                    break;
                }
                default:
                    break;
            }
        }
//        for (int row = 0; row < map.length; row++) {
//            System.out.println(map[row]);
//        }
        long count = 0;
        for (int row = 0; row < map.length; row++) {
            boolean up = false;
            boolean down = false;
            boolean inside = false;
//            System.out.println("");
            for (int column = 0; column < map.length; column++) {
                if (map[row][column] == '#') {
//                    System.out.print('#');
                    count++;
                    if (map[row][column - 1] == '.' && map[row - 1][column] == '#' && map[row + 1][column] == '#') {
                        inside = !inside;
                    } else if (map[row][column - 1] == '.' && map[row - 1][column] == '#' && map[row + 1][column] == '.') {
                        down = true;
                    } else if (map[row][column - 1] == '.' && map[row - 1][column] == '.' && map[row + 1][column] == '#') {
                        up = true;
                    } else if (map[row][column + 1] == '.' && map[row - 1][column] == '#' && map[row + 1][column] == '.') {
                        if (up) {
                            inside = !inside;
                        }
                    } else if (map[row][column + 1] == '.' && map[row - 1][column] == '.' && map[row + 1][column] == '#') {
                        if (down) {
                            inside = !inside;
                        }
                    }
                } else {
                    up = false;
                    down = false;
                    if (inside) {
                        count++;
//                        System.out.print('#');
                    } else {
//                        System.out.print('.');
                    }
                }
            }
        }


        return count;
    }

    public long processPartTwo(String s) throws IOException {
        List<DigPlanLine> plan = process(s);
        plan.forEach(d -> d.useColorAsNumber());
        long currentRow = 0;
        long currentColumn = 0;
        Map<Long, List<Wall>> walls = new HashMap<>();
        for (int dig = 0; dig < plan.size(); dig++) {
            DigPlanLine currentDig = plan.get(dig);
            DigPlanLine previousDig;
            DigPlanLine nextDig;
            if (dig == 0) {
                previousDig = plan.get(plan.size() - 1);
                nextDig = plan.get(1);
            } else if (dig == plan.size() - 1) {
                previousDig = plan.get(plan.size() - 2);
                nextDig = plan.get(0);
            } else {
                previousDig = plan.get(dig - 1);
                nextDig = plan.get(dig + 1);
            }
            switch (currentDig.direction) {
                case 'R': {
                    if (walls.get(currentRow) == null) {
                        walls.put(currentRow, new ArrayList<>());
                    }
                    Wall wall = new Wall();
                    wall.pos = currentColumn;
                    wall.size = currentDig.number + 1;
                    if ((previousDig.direction == 'U' && nextDig.direction == 'D')
                            || (previousDig.direction == 'D' && nextDig.direction == 'U')) {
                        wall.simple = true;
                    } else {
                        wall.simple = false;
                    }
                    walls.get(currentRow).add(wall);
                    currentColumn += currentDig.number;
                    break;
                }
                case 'L': {
                    if (walls.get(currentRow) == null) {
                        walls.put(currentRow, new ArrayList<>());
                    }
                    Wall wall = new Wall();
                    wall.pos = currentColumn - currentDig.number;
                    wall.size = currentDig.number + 1;
                    if ((previousDig.direction == 'U' && nextDig.direction == 'D')
                            || (previousDig.direction == 'D' && nextDig.direction == 'U')) {
                        wall.simple = true;
                    } else {
                        wall.simple = false;
                    }
                    walls.get(currentRow).add(wall);
                    currentColumn -= currentDig.number;
                    break;
                }
                case 'U': {
                    for (long row = currentRow - 1; row > currentRow - currentDig.number; row--) {
                        if (walls.get(row) == null) {
                            walls.put(row, new ArrayList<>());
                        }
                        Wall wall = new Wall();
                        wall.pos = currentColumn;
                        wall.size = 1;
                        wall.simple = true;
                        walls.get(row).add(wall);
                    }
                    currentRow -= currentDig.number;
                    break;
                }
                case 'D':{
                    for (long row = currentRow + 1; row < currentRow + currentDig.number; row++) {
                        if (walls.get(row) == null) {
                            walls.put(row, new ArrayList<>());
                        }
                        Wall wall = new Wall();
                        wall.pos = currentColumn;
                        wall.size = 1;
                        wall.simple = true;
                        walls.get(row).add(wall);
                    }
                    currentRow += currentDig.number;
                    break;
                }
                default:
                    break;
            }
        }
        long count = 0;
        for (List<Wall> wallsLine : walls.values()) {
            wallsLine.sort(Wall::compareTo);
            boolean inside = false;
            for (int wallIndex = 0; wallIndex < wallsLine.size(); wallIndex++) {
                Wall wall = wallsLine.get(wallIndex);
                if (wall.simple) {
                    if (inside) {
                        count += wall.pos - wallsLine.get(wallIndex - 1).pos -  wallsLine.get(wallIndex - 1).size;
                    }
                    if (wall.size == 1) {
                        inside = !inside;
                    }
                    count += wall.size;
                }
                else {
                    if (inside) {
                        count += wall.pos - wallsLine.get(wallIndex - 1).pos - wallsLine.get(wallIndex - 1).size;
                    }
                    count += wall.size;
                    inside = !inside;
                }
            }
        }

        return count;
    }

    private List<DigPlanLine> process(final String filename) throws IOException {
        List<DigPlanLine> plan = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(" ");
                DigPlanLine digPlanLine = new DigPlanLine();
                digPlanLine.direction = split[0].charAt(0);
                digPlanLine.number = Integer.parseInt(split[1]);
                digPlanLine.color = split[2];
                plan.add(digPlanLine);
                line = reader.readLine();
            }
        }
        return plan;
    }

}
