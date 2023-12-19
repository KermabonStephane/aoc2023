package org.demis27.aoc2023.days.day16;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day16 {

    public int size;

    public Tile[][] tiles;

    public long processPartOne(String s) throws IOException {
        process(s);
        getDirection(tiles[0][0], Direction.WEST);
//        printEnergized();
        return countEnergized();
    }

    public long processPartTwo(String s) throws IOException {
        long result = 0;
        // North
        process(s);
        for (int column = 0; column < size; column++) {
            getDirection(tiles[0][column], Direction.NORTH);
            result = Math.max(result, countEnergized());
            process(s);
        }
        // South
        for (int column = 0; column < size; column++) {
            getDirection(tiles[size- 1][column], Direction.SOUTH);
            result = Math.max(result, countEnergized());
            process(s);
        }
        // East
        for (int row = 0; row < size; row++) {
            getDirection(tiles[row][size - 1], Direction.EAST);
            result = Math.max(result, countEnergized());
            process(s);
        }
        // West
        for (int row = 0; row < size; row++) {
            getDirection(tiles[row][0], Direction.WEST);
            result = Math.max(result, countEnergized());
            process(s);
        }
        return result;
    }

    private long countEnergized() {
        long result = 0;
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                result += tiles[row][column].energized ? 1 : 0;
            }
        }
        return result;
    }

    public void getDirection(Tile tile, Direction from) {
        Direction[] tos = tile.direction(from);
        if (tos == null) {
            return;
        }
        for (int tosIndex = 0; tosIndex < tos.length; tosIndex++) {
            switch (tos[tosIndex]) {
                case NORTH -> {
                    if (tile.row != size - 1) getDirection(tiles[tile.row + 1][tile.column], Direction.NORTH);
                }
                case SOUTH -> {
                    if (tile.row != 0) getDirection(tiles[tile.row - 1][tile.column], Direction.SOUTH);
                }
                case WEST -> {
                    if (tile.column != size - 1) getDirection(tiles[tile.row][tile.column + 1], Direction.WEST);
                }
                case EAST -> {
                    if (tile.column != 0) getDirection(tiles[tile.row][tile.column - 1], Direction.EAST);
                }
            }
        }
    }

    private void process(final String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            tiles = new Tile[line.length()][line.length()];
            size = line.length();
            int row = 0;
            while (line != null) {
                for (int column = 0; column < line.length(); column++) {
                    tiles[row][column] = new Tile(row, column, line.charAt(column));
                }
                row++;
                line = reader.readLine();
            }
        }
    }

}
