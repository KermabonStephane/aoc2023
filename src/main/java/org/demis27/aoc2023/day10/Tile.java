package org.demis27.aoc2023.day10;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Tile {
    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                ", value=" + value +
                ", distance=" + distance +
                ", start=" + start +
                '}';
    }

    private int x;
    private int y;
    private char value;
    private long distance;
    private boolean start;
    private Tile next;
    private Tile previous;

    public Tile(int x, int y, char value) {
        this.x = x;
        this.y = y;
        this.value = value;
        start = (value == 'S');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return x == tile.x && y == tile.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static Tile getNext(Tile[][] tiles, Tile tile) {
        if (tile.isStart()) {
            tile.setDistance(1);
            if (tile.y != tiles[0].length - 1) {
                switch (right(tiles, tile).value) {
                    case 'J', '-', '7': {
                        return right(tiles, tile);
                    }
                }
            }
            if (tile.x != 0) {
                switch (up(tiles, tile).value) {
                    case '|', 'F', '7': {
                        return up(tiles, tile);
                    }
                }
            }
            if (tile.x != tiles.length -1) {
                switch (down(tiles, tile).value) {
                    case '|', 'J', 'L': {
                        return down(tiles, tile);
                    }
                }
            }
            if (tile.y != 0) {
                switch (left(tiles, tile).value) {
                    case '-', 'F', 'L': {
                        return left(tiles, tile);
                    }
                }
            }
        } else {
            switch (tile.value) {
                case 'L' -> {
                    if (tile.x != 0) {
                        if (up(tiles, tile).distance > 0 ) {
                            tile.distance = up(tiles, tile).distance + 1;
                            return right(tiles, tile);
                        } else {
                            tile.distance = right(tiles, tile).distance + 1;
                            return up(tiles, tile);
                        }
                    }
                    else {
                        tile.value = '.';
                    }
                }
                case '|' -> {
                    if (up(tiles, tile).distance > 0 && !up(tiles, tile).isStart()) {
                        tile.distance =  up(tiles, tile).distance + 1;
                        return down(tiles, tile);
                    }
                    else {
                        tile.distance = down(tiles, tile).distance + 1;
                        return up(tiles, tile);
                    }
                }
                case '-' -> {
                    if (left(tiles, tile).distance > 0) {
                        tile.distance = left(tiles, tile).distance + 1;
                        return right(tiles, tile);
                    }
                    else {
                        tile.distance = right(tiles, tile).distance + 1;
                        return left(tiles, tile);
                    }
                }
                case 'J' -> {
                    if (up(tiles, tile).distance > 0) {
                        tile.distance = up(tiles, tile).distance + 1;
                        return left(tiles, tile);
                    }
                    else {
                        tile.distance = left(tiles, tile).distance + 1;
                        return up(tiles, tile);
                    }
                }
                case '7' -> {
                    if (left(tiles, tile).distance > 0) {
                        tile.distance = left(tiles, tile).distance + 1;
                        return down(tiles, tile);
                    }
                    else {
                        tile.distance = down(tiles, tile).distance + 1;
                        return left(tiles, tile);
                    }
                }
                case 'F' -> {
                    if (right(tiles, tile).distance > 0) {
                        tile.distance = right(tiles, tile).distance + 1;
                        return down(tiles, tile);
                    }
                    else {
                        tile.distance = down(tiles, tile).distance + 1;
                        return right(tiles, tile);
                    }
                }
                default -> {return null;}
            }
        }
        return null;
    }

    private static Tile left(Tile[][] tiles, Tile tile) {
        return tiles[tile.x][tile.y-1];
    }

    private static Tile down(Tile[][] tiles, Tile tile) {
        return tiles[tile.x+1][tile.y];
    }

    private static Tile right(Tile[][] tiles, Tile tile) {
        return tiles[tile.x][tile.y + 1];
    }

    private static Tile up(Tile[][] tiles, Tile tile) {
        return tiles[tile.x-1][tile.y];
    }

}
