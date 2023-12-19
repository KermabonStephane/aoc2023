package org.demis27.aoc2023.days.day16;

import java.util.ArrayList;
import java.util.List;

import static org.demis27.aoc2023.days.day16.Direction.*;

public class Tile {

    int row;
    int column;

    char value;

    char updatedValue;

    boolean energized = false;

    List<Direction> froms = new ArrayList<>();

    public Tile(int row, int column, char value) {
        this.row = row;
        this.column = column;
        this.value = value;
        this.updatedValue = value;
    }

    public Direction[] direction(Direction from) {
        energized = true;
        switch (from) {
            case NORTH: {
                switch (value) {
                    case '.', '-':
                        if (froms.contains(SOUTH)) {
                            return null;
                        } else {
                            break;
                        }
                    case '\\':
                        if (froms.contains(EAST)) {
                            return null;
                        } else {
                            break;
                        }
                    case '/':
                        if (froms.contains(WEST)) {
                            return null;
                        } else {
                            break;
                        }
                    case '|':
                        break;
                }
                break;
            }
            case SOUTH: {
                switch (value) {
                    case '.', '|':
                        if (froms.contains(NORTH)) {
                            return null;
                        } else {
                            break;
                        }
                    case '\\':
                        if (froms.contains(WEST)) {
                            return null;
                        } else {
                            break;
                        }
                    case '/':
                        if (froms.contains(EAST)) {
                            return null;
                        } else {
                            break;
                        }
                    case '-':
                        break;
                }
                break;
            }
            case EAST: {
                switch (value) {
                    case '.', '|':
                        if (froms.contains(WEST)) {
                            return null;
                        } else {
                            break;
                        }
                    case '\\':
                        if (froms.contains(NORTH)) {
                            return null;
                        } else {
                            break;
                        }
                    case '/':
                        if (froms.contains(SOUTH)) {
                            return null;
                        } else {
                            break;
                        }
                    case '-':
                        break;
                }
                break;
            }
            case WEST: {
                switch (value) {
                    case '.', '|':
                        if (froms.contains(EAST)) {
                            return null;
                        } else {
                            break;
                        }
                    case '\\':
                        if (froms.contains(SOUTH)) {
                            return null;
                        } else {
                            break;
                        }
                    case '/':
                        if (froms.contains(NORTH)) {
                            return null;
                        } else {
                            break;
                        }
                    case '-':
                        break;
                }
                break;
            }
        }

        if (froms.contains(from) || updatedValue == '2') return null;
        else froms.add(from);
        if (value == '.') {
            if (updatedValue == '.') {
                switch (from) {
                    case NORTH -> updatedValue = 'v';
                    case SOUTH -> updatedValue = '^';
                    case WEST -> updatedValue = '>';
                    case EAST -> updatedValue = '<';
                }
            }
            return madeDirection(from);
        }
        switch (from) {
            case NORTH:
                return switch (value) {
                    case '/' -> madeDirection(EAST);
                    case '\\' -> madeDirection(WEST);
                    case '-' -> froms.contains(SOUTH) ? null : madeDirection(EAST, WEST);
                    case '|' -> madeDirection(NORTH);
                    default -> null;
                };
            case SOUTH:
                return switch (value) {
                    case '/' -> madeDirection(WEST);
                    case '\\' -> madeDirection(EAST);
                    case '-' -> froms.contains(NORTH) ? null : madeDirection(EAST, WEST);
                    case '|' -> madeDirection(SOUTH);
                    default -> null;
                };
            case WEST:
                return switch (value) {
                    case '/' -> madeDirection(SOUTH);
                    case '\\' -> madeDirection(NORTH);
                    case '-' -> madeDirection(WEST);
                    case '|' -> froms.contains(EAST) ? null : madeDirection(NORTH, SOUTH);
                    default -> null;
                };
            case EAST:
                return switch (value) {
                    case '/' -> madeDirection(NORTH);
                    case '\\' -> madeDirection(SOUTH);
                    case '-' -> madeDirection(EAST);
                    case '|' -> froms.contains(WEST) ? null : madeDirection(NORTH, SOUTH);
                    default -> null;
                };
        }
        return null;
    }

    public Direction[] madeDirection(Direction one) {
        Direction[] result = new Direction[1];
        result[0] = one;
        return result;
    }

    public Direction[] madeDirection(Direction one, Direction two) {
        Direction[] result = new Direction[2];
        result[0] = one;
        result[1] = two;
        return result;
    }
}
