package org.demis27.aoc2023.day10;

import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 {
    public long processPartOne(String s) throws IOException {
        Tile[][] tiles = process(s);
        Tile start = findStart(tiles);
//        System.out.println(start);
        boolean notEnd = true;
        Tile current = start;
        while (notEnd) {
            Tile next = Tile.getNext(tiles, current);
            if (next.equals(start)) {
                start.setDistance(current.getDistance() + 1);
            }
            notEnd = !next.equals(start);
            System.out.println(current + " " + next);
            current = next;
        }
        return (start.getDistance()) / 2;
    }


    private Tile findStart(Tile[][] tiles) {
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                if (tiles[x][y].isStart()) {
                    return tiles[x][y];
                }
            }
        }
        return null;
    }

    public long processPartTwo(String s) throws IOException {
        Tile[][] tiles = process(s);


        Tile start = findStart(tiles);
        boolean notEnd = true;
        Tile current = start;
        while (notEnd) {
            Tile next = Tile.getNext(tiles, current);
            if (next.equals(start)) {
                start.setDistance(current.getDistance() + 1);
            }
            notEnd = !next.equals(start);
//            System.out.println(current);
            current.setNext(next);
            next.setPrevious(current);
            current = next;
        }
/*
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                System.out.print(tiles[x][y].getValue() + " " + tiles[x][y].getDistance());
            }
            System.out.println("");
        }
*/

        System.out.println((start.getDistance()) / 2);

        if (((start.getPrevious().getX() == start.getX() && start.getPrevious().getY() == start.getY() - 1)
                && (start.getNext().getY() == start.getY() && start.getNext().getX() == start.getX() - 1))
                || ((start.getNext().getX() == start.getX() && start.getNext().getY() == start.getY() - 1)
                && (start.getPrevious().getY() == start.getY() && start.getPrevious().getX() == start.getX() - 1))) {
            start.setValue('J');
        } else if (((start.getPrevious().getX() == start.getX() && start.getPrevious().getY() == start.getY() - 1)
                && (start.getNext().getX() == start.getX() && start.getNext().getY() == start.getY() + 1))
                || ((start.getNext().getX() == start.getX() && start.getNext().getY() == start.getY() - 1)
                && (start.getPrevious().getX() == start.getX() && start.getPrevious().getY() == start.getY() + 1))) {
            start.setValue('-');
        } else if (((start.getPrevious().getX() == start.getX() && start.getPrevious().getY() == start.getY() - 1)
                && (start.getNext().getY() == start.getY() && start.getNext().getX() == start.getX() + 1))
                || ((start.getNext().getX() == start.getX() && start.getNext().getY() == start.getY() - 1)
                && (start.getPrevious().getY() == start.getY() && start.getPrevious().getX() == start.getX() + 1))) {
            start.setValue('7');
        } else if (((start.getPrevious().getY() == start.getY() && start.getPrevious().getX() == start.getX() - 1)
                && (start.getNext().getX() == start.getX() && start.getNext().getY() == start.getY() + 1))
                || ((start.getNext().getY() == start.getY() && start.getNext().getX() == start.getX() - 1)
                && (start.getPrevious().getX() == start.getX() && start.getPrevious().getY() == start.getY() + 1))) {
            start.setValue('L');
        } else if (((start.getPrevious().getY() == start.getY() && start.getPrevious().getX() == start.getX() - 1)
                && (start.getNext().getY() == start.getY() && start.getNext().getX() == start.getX() + 1))
                || ((start.getNext().getY() == start.getY() && start.getNext().getX() == start.getX() - 1)
                && (start.getPrevious().getY() == start.getY() && start.getPrevious().getX() == start.getX() + 1))) {
            start.setValue('|');
        } else {
            start.setValue('F');
        }

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                Tile tile = tiles[x][y];
                if (tile.getDistance() == 0) {
                    tile.setValue(' ');
                }
            }
        }

        long count = 0;
        List<String> strings = new ArrayList<>();
        for (int x = 0; x < tiles.length; x++) {
            String line = new String(Arrays.stream(tiles[x]).map(c -> String.valueOf(c.getValue())).reduce("", (a, b) -> a + b)).trim();
            line = line.replaceAll("F-*7", "");
            line = line.replaceAll("F-*J", "O");
            line = line.replaceAll("L-*J", "");
            line = line.replaceAll("L-*7", "O");
            line = line.replaceAll("\\|", "O");
            line = line.trim();
            line = line.replaceAll("OO", "");
            line = line.trim();
            strings.add(line);
            System.out.println(x + " " + line);
            count += line.trim().replaceAll("O", "").length();
        }

        for (int x = 0; x < tiles.length; x++) {
//            System.out.print(x + " ");
            for (int y = 0; y < tiles[0].length; y++) {
                System.out.print(tiles[x][y].getValue());
            }
            System.out.println("");
        }

        return count;
    }

    private Tile[][] process(final String filename) throws IOException {
        Tile[][] result;
        List<char[]> tmp = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filename)))) {
            String line = reader.readLine();
            while (line != null) {
                tmp.add(line.toCharArray());
                line = reader.readLine();
            }
        }
        result = new Tile[tmp.size()][tmp.get(0).length];
        for (int x = 0; x < tmp.size(); x++) {
            for (int y = 0; y < tmp.get(0).length; y++) {
                result[x][y] = new Tile(x, y, tmp.get(x)[y]);
            }
        }


        return result;
    }

}
