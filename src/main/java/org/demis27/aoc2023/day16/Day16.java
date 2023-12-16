package org.demis27.aoc2023.day16;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day16 {

    public int size;

    public Tile[][] tiles;

    public long processPartOne(String s) throws IOException {
        process(s);
        getDirection(tiles[0][0], 'W');
//        printEnergized();
        return countEnergized();
    }

    public long processPartTwo(String s) throws IOException {
        long result = 0;
        // North
        process(s);
        for (int column = 0; column < size; column++) {
            getDirection(tiles[0][column], 'N');
            result = Math.max(result, countEnergized());
            process(s);
        }
        // South
        for (int column = 0; column < size; column++) {
            getDirection(tiles[size- 1][column], 'S');
            result = Math.max(result, countEnergized());
            process(s);
        }
        // East
        for (int row = 0; row < size; row++) {
            getDirection(tiles[row][size - 1], 'E');
            result = Math.max(result, countEnergized());
            process(s);
        }
        // West
        for (int row = 0; row < size; row++) {
            getDirection(tiles[row][0], 'W');
            result = Math.max(result, countEnergized());
            process(s);
        }
        return result;
    }



    public void printEnergized() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                System.out.print(tiles[row][column].energized ? "#" : ".");
            }
            System.out.println("");
        }
    }

    public void printUpdated() {
        System.out.println("0123456789");
        for (int row = 0; row < size; row++) {
            System.out.print(row);
            for (int column = 0; column < size; column++) {
                System.out.print(tiles[row][column].updatedValue);
            }
            System.out.println("");
        }
        System.out.println("");
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

    public void getDirection(Tile tile, char from) {
//        printUpdated();
//        System.out.println(tile.row + " " + tile.column);
        char[] tos = tile.direction(from);
        if (tos == null) {
            return;
        } else {
  //          System.out.println(tos);
        }
        for (int tosIndex = 0; tosIndex < tos.length; tosIndex++) {
            switch (tos[tosIndex]) {
                case 'N': {
                    if (tile.row == size - 1) {
                        break;
                    } else {
                        getDirection(tiles[tile.row + 1][tile.column], 'N'); break;
                    }
                }
                case 'S': {
                    if (tile.row == 0) {
                        break;
                    } else {
                        getDirection(tiles[tile.row - 1][tile.column], 'S');break;
                    }
                }
                case 'W': {
                    if (tile.column == size - 1) {
                        break;
                    } else {
                        getDirection(tiles[tile.row][tile.column + 1], 'W');break;
                    }
                }
                case 'E': {
                    if (tile.column == 0) {
                        break;
                    } else {
                        getDirection(tiles[tile.row][tile.column - 1], 'E');break;
                    }
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
