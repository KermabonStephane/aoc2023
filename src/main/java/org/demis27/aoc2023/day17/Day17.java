package org.demis27.aoc2023.day17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

import static org.demis27.aoc2023.day17.Direction.*;

public class Day17 {

    Block[][] blocks;

    long currentMin = 110;

    public long processPartOne(String s) throws IOException {
        blocks = process(s);
        Way way = new Way();
        way.blocks.add(blocks[0][0]);
        for (int row = 0; row < blocks.length; row++) {
            for (int column = 0; column < blocks.length; column++) {
                blocks[row][column].totalLoss = 6 * (row + column);
            }
        }
//        way.totalLoss = blocks[0][0].loss;
        way.addDirection(EAST);
        return moveLava(way);
    }

    public long processPartTwo(String s) throws IOException {
        blocks = process(s);
        return 0;
    }

    public long moveLava(Way way) {
        // stop recursive
        long currentLoss = way.totalLoss;
        if (way.getLastBlock().equals(blocks[blocks.length - 1][blocks.length - 1])) {
//            for (int i = 0; i < way.blocks.size(); i++) {
//                System.out.print(way.blocks.get(i));
//            }
//            System.out.println(" " + currentLoss);
            currentMin = Math.min(currentMin, currentLoss);
            return currentLoss;
        }
        // Stop if current loss greater than min loss
        if (currentLoss > currentMin) {
            return Long.MAX_VALUE;
        }

        List<Direction> newDirections = detectNewDirections(way);
        if (newDirections.isEmpty()) {
            return Long.MAX_VALUE;
        } else if (newDirections.size() == 1) {
            Block nextBlock = nextBlock(way, newDirections.get(0));
            if (way.blocks.contains(nextBlock)
                    || (way.totalLoss + nextBlock.loss >= nextBlock.totalLoss
                    && !way.directionsIfFull())
            ) {
                return Long.MAX_VALUE;
            } else {
                Way newWay = way.clone();
                newWay.addDirection(newDirections.get(0));
                newWay.blocks.add(nextBlock);
                newWay.totalLoss += nextBlock.loss;
                nextBlock.totalLoss = Math.min(nextBlock.totalLoss, newWay.totalLoss);
                return moveLava(newWay);
            }
        } else if (newDirections.size() == 2) {
            List<Way> newWays = new ArrayList<>();
            Block nextBlockOne = nextBlock(way, newDirections.get(0));
            if (!way.blocks.contains(nextBlockOne)
                    || (way.totalLoss + nextBlockOne.loss >= nextBlockOne.totalLoss
                    && !way.directionsIfFull())
            ) {
                Way newWayOne = way.clone();
                newWayOne.addDirection(newDirections.get(0));
                newWayOne.blocks.add(nextBlockOne);
                newWayOne.totalLoss += nextBlockOne.loss;
                nextBlockOne.totalLoss = Math.min(nextBlockOne.totalLoss, newWayOne.totalLoss);
                newWays.add(newWayOne);
            }
            Block nextBlockTwo = nextBlock(way, newDirections.get(1));
            if (!way.blocks.contains(nextBlockTwo)
                    || (way.totalLoss + nextBlockTwo.loss >= nextBlockTwo.totalLoss
                    && !way.directionsIfFull())
            ) {
                Way newWayTwo = way.clone();
                newWayTwo.addDirection(newDirections.get(1));
                newWayTwo.blocks.add(nextBlockTwo);
                newWayTwo.totalLoss += nextBlockTwo.loss;
                nextBlockTwo.totalLoss = Math.min(nextBlockTwo.totalLoss, newWayTwo.totalLoss);
                newWays.add(newWayTwo);
            }
            if (newWays.isEmpty()) {
                return Long.MAX_VALUE;
            } else if (newWays.size() == 1) {
                return moveLava(newWays.get(0));
            } else {
                long one = moveLava(newWays.get(0));
                long two = moveLava(newWays.get(1));
                return Math.min(one, two);
            }
        } else {
            List<Way> newWays = new ArrayList<>();
            Block nextBlockOne = nextBlock(way, newDirections.get(0));
            if (!way.blocks.contains(nextBlockOne)
                    || (way.totalLoss + nextBlockOne.loss >= nextBlockOne.totalLoss
                    && !way.directionsIfFull())
            ) {
                Way newWayOne = way.clone();
                newWayOne.addDirection(newDirections.get(0));
                newWayOne.blocks.add(nextBlockOne);
                newWayOne.totalLoss += nextBlockOne.loss;
                nextBlockOne.totalLoss = Math.min(nextBlockOne.totalLoss, newWayOne.totalLoss);
                newWays.add(newWayOne);
            }
            Block nextBlockTwo = nextBlock(way, newDirections.get(1));
            if (!way.blocks.contains(nextBlockTwo)
                    || (way.totalLoss + nextBlockTwo.loss >= nextBlockTwo.totalLoss
                    && !way.directionsIfFull())
            ) {
                Way newWayTwo = way.clone();
                newWayTwo.addDirection(newDirections.get(1));
                newWayTwo.blocks.add(nextBlockTwo);
                newWayTwo.totalLoss += nextBlockTwo.loss;
                nextBlockTwo.totalLoss = Math.min(nextBlockTwo.totalLoss, newWayTwo.totalLoss);
                newWays.add(newWayTwo);
            }
            Block nextBlockThree = nextBlock(way, newDirections.get(2));
            if (!way.blocks.contains(nextBlockThree)
                    || (way.totalLoss + nextBlockThree.loss >= nextBlockThree.totalLoss
                    && !way.directionsIfFull())
            ) {
                Way newWayThree = way.clone();
                newWayThree.addDirection(newDirections.get(2));
                newWayThree.blocks.add(nextBlockThree);
                newWayThree.totalLoss += nextBlockThree.loss;
                nextBlockThree.totalLoss = Math.min(nextBlockThree.totalLoss, newWayThree.totalLoss);
                newWays.add(newWayThree);
            }
            if (newWays.isEmpty()) {
                return Long.MAX_VALUE;
            } else if (newWays.size() == 1) {
                return moveLava(newWays.get(0));
            } else if (newWays.size() == 2) {
                long one = moveLava(newWays.get(0));
                long two = moveLava(newWays.get(1));
                return Math.min(one, two);
            } else {
                long one = moveLava(newWays.get(0));
                long two = moveLava(newWays.get(1));
                long three = moveLava(newWays.get(2));
                return Math.min(one, Math.min(two, three));
            }
        }
    }

    public long processPartOneDijkstra(String s) throws IOException {
        blocks = process(s);
        Way way = new Way();
        way.blocks.add(blocks[0][0]);

        Stack<Direction> lastDirections = new Stack<>();
        int currentRow = 0;
        int currentColumn = 0;

        // Step one
        int[] diskstra = new int[blocks.length * blocks.length];
        boolean[] marks = new boolean[blocks.length * blocks.length];
        for (int column = 0; column < blocks.length; column++) {
            for (int row = 0; row < blocks.length; row++) {
                diskstra[column + (row * (blocks.length))] = Integer.MAX_VALUE;
            }
        }
        diskstra[0] = 0;
        marks[0] = true;
        int totalLoss = 0;
        while (currentRow != blocks.length - 1 && currentColumn != blocks.length - 1) {
            List<Direction> directions = getDirection(currentRow, currentColumn, lastDirections);
            int newNorth = Integer.MAX_VALUE;
            if (directions.contains(NORTH) && !marks[(currentColumn) + ((currentRow - 1) * (blocks.length))]) {
                newNorth = Math.min(diskstra[(currentColumn) + ((currentRow - 1) * (blocks.length))], blocks[currentRow - 1][currentColumn].loss + totalLoss);
                diskstra[(currentColumn) + ((currentRow - 1) * (blocks.length))] = newNorth;
            }
            int newSouth = Integer.MAX_VALUE;
            if (directions.contains(SOUTH) && !marks[(currentColumn) + ((currentRow + 1) * (blocks.length ))]) {
                newSouth = Math.min(diskstra[(currentColumn) + ((currentRow + 1) * (blocks.length))], blocks[currentRow + 1][currentColumn].loss + totalLoss);
                diskstra[(currentColumn) + ((currentRow + 1) * (blocks.length))] = newSouth;
            }
            int newEast = Integer.MAX_VALUE;
            if(directions.contains(EAST) && !marks[(currentColumn + 1) + ((currentRow) * (blocks.length))]) {
                newEast = Math.min(diskstra[(currentColumn + 1) + (currentRow * (blocks.length))], blocks[currentRow][currentColumn + 1].loss + totalLoss);
                diskstra[(currentColumn + 1) + (currentRow * (blocks.length))] = newEast;
            }
            int newWest = Integer.MAX_VALUE;
            if (directions.contains(WEST) && !marks[(currentColumn - 1) + ((currentRow) * (blocks.length))]) {
                newWest = Math.min(diskstra[(currentColumn - 1) + (currentRow * (blocks.length))], blocks[currentRow][currentColumn - 1].loss);
                diskstra[(currentColumn - 1) + (currentRow * (blocks.length))] = newWest;
            }

            int min = Stream.of(newNorth, newEast, newWest, newSouth).min(Integer::compareTo).get();
            if (newEast == min) {
                currentColumn++;
                lastDirections.push(EAST);
            } else if (newWest == min) {
                currentColumn--;
                lastDirections.push(WEST);
            } else if (newNorth == min) {
                currentRow--;
                lastDirections.push(NORTH);
            } else if (newSouth == min) {
                currentRow++;
                lastDirections.push(SOUTH);
            }
            marks[currentColumn + (currentRow * (blocks.length))] = true;
            totalLoss = min;
        }

        return totalLoss + blocks[blocks.length - 1][blocks.length - 1].loss;
    }

    public List<Direction> getDirection(int row, int column, Stack<Direction> lastDirections) {
        List<Direction> directions = new ArrayList<>(3);
        if (lastDirections.size() > 2) {
            Direction lastDirection = lastDirections.peek();
            Direction minusTwo = lastDirections.elementAt(lastDirections.size() - 2);
            Direction minusThree = lastDirections.elementAt(lastDirections.size() - 2);
            if (lastDirection != minusTwo || minusTwo != minusThree) {
                directions.add(lastDirection);
            }
            switch (lastDirection) {
                case NORTH, SOUTH: {
                    directions.add(EAST);
                    directions.add(WEST);
                    break;
                }
                case EAST, WEST: {
                    directions.add(NORTH);
                    directions.add(SOUTH);
                    break;
                }
            }
        } else if (lastDirections.size() == 0) {
            directions.add(EAST);
            directions.add(SOUTH);
        } else if (lastDirections.size() == 1 || lastDirections.size() == 2) {
            if (!directions.contains(lastDirections.peek())) {
                directions.add(lastDirections.peek());
            }
            Direction lastDirection = lastDirections.peek();
            switch (lastDirection) {
                case NORTH, SOUTH: {
                    directions.add(EAST);
                    directions.add(WEST);
                    break;
                }
                case EAST, WEST: {
                    directions.add(NORTH);
                    directions.add(SOUTH);
                    break;
                }
            }
        }
        // detect border
        if (row == 0) {
            directions.remove(NORTH);
        } else if (row == blocks.length - 1) {
            directions.remove(SOUTH);
        }
        if (column == 0) {
            directions.remove(WEST);
        } else if (column == blocks.length - 1) {
            directions.remove(EAST);
        }

        return directions;
    }


    private List<Direction> detectNewDirections(Way way) {
        List<Direction> directions = new ArrayList<>(3);
        // detect all possible direction
        if (way.lastDirections[2] == null && way.lastDirections[0] != null) {
            // same direction
            directions.add(way.lastDirections[0]);
        }
        switch (way.getLastDirection()) {
            case NORTH, SOUTH: {
                directions.add(EAST);
                directions.add(WEST);
                break;
            }
            case EAST, WEST: {
                directions.add(NORTH);
                directions.add(SOUTH);
                break;
            }
        }

        // detect border
        if (way.getLastBlock().row == 0) {
            directions.remove(NORTH);
        } else if (way.getLastBlock().row == blocks.length - 1) {
            directions.remove(SOUTH);
//            directions.remove(WEST);
        }
        if (way.getLastBlock().column == 0) {
            directions.remove(WEST);
        } else if (way.getLastBlock().column == blocks.length - 1) {
            directions.remove(EAST);
//            directions.remove(NORTH);
        }

        // detect loop
        List<Direction> newDirections = new ArrayList<>(3);
        for (int d = 0; d < directions.size(); d++) {
            if (!way.blocks.contains(nextBlock(way, directions.get(d)))) {
                newDirections.add(directions.get(d));
            }
        }
        return newDirections;
    }

    private Block nextBlock(Way way, Direction direction) {
        return switch (direction) {
            case NORTH -> blocks[way.getLastBlock().row - 1][way.getLastBlock().column];
            case EAST -> blocks[way.getLastBlock().row][way.getLastBlock().column + 1];
            case SOUTH -> blocks[way.getLastBlock().row + 1][way.getLastBlock().column];
            case WEST -> blocks[way.getLastBlock().row][way.getLastBlock().column - 1];
        };
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
