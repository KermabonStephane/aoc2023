package org.demis27.aoc2023.days.day11;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map {

    public char[][] elements;

    public List<Pair<Long, Long>> galaxies = new ArrayList<>();

    public List<Pair<Pair<Long, Long>, Pair<Long, Long>>> galaxyPairs = new ArrayList<>();

    @Override
    public String toString() {
        return "Map{" +
                "elements=" + Arrays.toString(elements) +
                ", galaxies=" + galaxies +
                '}';
    }

    public void expand2() {
        expand2(1);
    }

    public void expand2(long expense) {
        // found galaxies
        for (int y = 0; y < elements[0].length; y++) {
            for (int x = 0; x < elements.length; x++) {
                if (elements[y][x] == '#') {
                    galaxies.add(new Pair<>(Long.valueOf(x), Long.valueOf(y)));
                }
            }
        }
        // line to duplicate (reverse order)
        List<Integer> linesToDuplicate = new ArrayList<>();
        for (int y = 0; y < elements.length; y++) {
            if ((new String(elements[y])).indexOf('#') < 0 && !linesToDuplicate.contains(y)) {
                linesToDuplicate.add(0, y);
            }
        }

        // column to duplicate (reverse order)
        List<Integer> columnsToDuplicate = new ArrayList<>();
        for (int y = 0; y < elements[0].length; y++) {
            boolean galaxy = false;
            for (int x = 0; x < elements.length; x++) {
                if (elements[x][y] != '.') {
                    galaxy = true;
                }
            }
            if (!galaxy) {
                columnsToDuplicate.add(0, y);
                galaxy = false;
            }
        }

        // adjust galaxies positions
        // column
        final List<Pair<Long, Long>> newGalaxies = new ArrayList<>();
        newGalaxies.addAll(galaxies);
        columnsToDuplicate.stream().forEach(column -> {
            List<Pair<Long, Long>> tmp = new ArrayList<>();
            newGalaxies.stream().forEach(galaxy -> {
                if (galaxy.getValue0() > column) {
                    tmp.add(galaxy.setAt0(galaxy.getValue0() + expense));
                } else {
                    tmp.add(galaxy);
                }
            });
            newGalaxies.clear();
            newGalaxies.addAll(tmp);
        });
        galaxies.clear();
        galaxies.addAll(newGalaxies);

        // Adjust galaxies positions
        // line
        newGalaxies.clear();
        newGalaxies.addAll(galaxies);
        linesToDuplicate.stream().forEach(line -> {
            List<Pair<Long, Long>> tmp = new ArrayList<>();
            newGalaxies.stream().forEach(galaxy -> {
                if (galaxy.getValue1() > line) {
                    tmp.add(galaxy.setAt1(galaxy.getValue1() + expense));
                } else {
                    tmp.add(galaxy);
                }
            });
            newGalaxies.clear();
            newGalaxies.addAll(tmp);
        });
        galaxies = newGalaxies;

        // Pairs all galaxies
        for (int i = 0; i < galaxies.size(); i++) {
            Pair<Long, Long> galaxy = galaxies.get(i);
            for (int j = i; j < galaxies.size(); j++) {
                Pair<Long, Long> galaxy2 = galaxies.get(j);
                if (!galaxy.equals(galaxy2)) {
                    galaxyPairs.add(new Pair<>(galaxy, galaxy2));
                }
            }
        }
    }
}