package org.demis27.aoc2023.day17;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.demis27.aoc2023.day17.Direction.*;

public class Day17 {

    long currentMin = 0;
    public long processPartOne(String s) throws IOException, IllegalAccessException {
        Block[][] blocks = process(s);
        Way way = new Way(blocks, blocks[0][0], new ArrayList<>());
//        way.already.add(blocks[0][0]);
        for (int row = 0; row < blocks.length; row++) {
            for (int column = 0; column < blocks.length; column++) {
                currentMin += blocks[row][column].loss;
            }
        }
        currentMin = blocks.length * 2 * currentMin / (blocks.length * blocks.length);
        return moveLava(way);
    }

    private long moveLava(Way way) throws IllegalAccessException {
        // stop recursive
        long value = way.already.stream().map(b -> b.loss).collect(Collectors.summarizingLong(i -> i)).getSum();
        if (way.current.row == way.blocks.length - 1 && way.current.column == way.blocks.length - 1) {
            currentMin = Math.min(currentMin, value);
            return value;
        }
        if (value > currentMin) {
            return Long.MAX_VALUE;
        }
        way.totalLoss += way.current.loss;
        // detect all possible direction
        List<Direction> directions = new ArrayList<>(3);
        switch (way.getLastDirection()) {
            case NORTH: {
                directions.add(EAST);
                directions.add(WEST);
                break;
            }
            case EAST: {
                directions.add(NORTH);
                directions.add(SOUTH);
                break;
            }
            case WEST: {
                directions.add(NORTH);
                directions.add(SOUTH);
                break;
            }
            case SOUTH: {
                directions.add(EAST);
                directions.add(WEST);
                break;
            }
        }

        if (way.lastDirections[2] == null && way.lastDirections[0] != null) {
            // same direction
            directions.add(way.lastDirections[0]);
        }
        // detect border
        if (way.current.row == 0) {
            directions.remove(NORTH);
        } else if (way.current.row == way.blocks.length - 1) {
            directions.remove(SOUTH);
        }
        if (way.current.column == 0) {
            directions.remove(WEST);
        } else if (way.current.column == way.blocks.length - 1) {
            directions.remove(EAST);
        }

        // detect loop
        List<Direction> newDirections = new ArrayList<>(3);
        for (int d = 0; d < directions.size(); d++) {
            if (!way.already.contains(nextBlock(way, directions.get(d)))) {
                newDirections.add(directions.get(d));
            }
        }
        directions = newDirections;

        if (directions.size() == 0) {
            return Long.MAX_VALUE;
        } else if (directions.size() == 1) {
            Block nextBlock = nextBlock(way, directions.get(0));
            reuseSameWay(way, directions.get(0));
            return moveLava(way);
        } else if (directions.size() == 2) {
            // reuse the same way and add another one
            if (directions.contains(way.getLastDirection())) {
                if (directions.get(0) == way.getLastDirection()) {
                    Way way1 = newDirection(way, directions.get(2));
                    reuseSameWay(way, directions.get(0));
                    return Math.min(moveLava(way), moveLava(way1));
                } else {
                    Way way1 = newDirection(way, directions.get(0));
                    reuseSameWay(way, directions.get(1));
                    return Math.min(moveLava(way), moveLava(way1));
                }
            } else {
                Way way1 = newDirection(way, directions.get(0));
                Way way2 = newDirection(way, directions.get(1));
                return Math.min(moveLava(way1), moveLava(way2));
            }
        } else {
            Way way1 = new Way(way.blocks, nextBlock(way, directions.get(0)), way.already);
            way1.update(directions.get(0));
            Way way2 = new Way(way.blocks, nextBlock(way, directions.get(1)), way.already);
            way2.update(directions.get(1));
            Way way3 = new Way(way.blocks, nextBlock(way, directions.get(2)), way.already);
            way3.update(directions.get(2));
            return Math.min(Math.min(moveLava(way1), moveLava(way2)), moveLava(way3));
        }
    }

    private Way newDirection(Way way, Direction direction) throws IllegalAccessException {
        Way way1 = new Way(way.blocks, nextBlock(way, direction), way.already);
        way1.totalLoss = way.totalLoss + way.current.loss;
        way1.update(direction);
        return way1;
    }

    private void reuseSameWay(Way way, Direction directions) throws IllegalAccessException {
        if (directions == way.getLastDirection()) {
            way.update(directions);
        } else {
            way.changeDirection(directions);
        }
        way.current = nextBlock(way, directions);
        way.already.add(way.current);
    }

    private Block nextBlock(Way way, Direction direction) {
        return switch (direction) {
            case NORTH -> way.blocks[way.current.row - 1][way.current.column];
            case EAST -> way.blocks[way.current.row][way.current.column + 1];
            case SOUTH -> way.blocks[way.current.row + 1][way.current.column];
            case WEST -> way.blocks[way.current.row][way.current.column - 1];
        };
    }

    public long processPartTwo(String s) throws IOException {
        Block[][] blocks = process(s);
        return 0L;
    }

    private Block[][] process(final String filename) throws IOException {
        Block[][] blocks;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            blocks = new Block[line.length()][line.length()];
            int row = 0;
            while (line != null) {
                for (int column = 0; column < line.length(); column++) {
                    blocks[row][column] = new Block(Integer.parseInt("" + line.charAt(column)), row, column);
                }
                row++;
                line = reader.readLine();
            }
        }
        return blocks;
    }

}
