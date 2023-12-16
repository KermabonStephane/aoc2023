package org.demis27.aoc2023.day16;

import java.util.ArrayList;
import java.util.List;

public class Tile {

    int row;
    int column;

    char value;

    char updatedValue;

    boolean energized = false;

    int count = 0;

    List<Character> froms = new ArrayList<>();

    public Tile(int row, int column, char value) {
        this.row = row;
        this.column = column;
        this.value = value;
        this.updatedValue = value;
    }
public char[] direction(char from) {
    char[] chars = directionI(from);
//    System.out.print("tile " + row + " " + column + " from " + from + " to ");
//    if (chars != null) {
//        for (int i = 0; i < chars.length; i++) {
//            System.out.print(chars[i]);
//        }
//        System.out.println("");
//    }
//    else { System.out.println("nowhere");}
    return chars;
}

    public char[] directionI(char from) {
        count++;
        energized = true;
        switch (from) {
            case 'N':{switch (value) {
                case '.' : if (froms.contains('S')) { return null;} else { break; }
                case '\\' : if (froms.contains('E')) { return null; } else { break; }
                case '/' :  if (froms.contains('W')) { return null; } else { break; }
                case '-' :  if (froms.contains('S')) { return null; } else { break; }
                case '|' : break;}
                break;
            }
            case 'S': {switch (value) {
                case '.' : if (froms.contains('N')) { return null;} else { break; }
                case '\\' : if (froms.contains('W')) { return null; } else { break; }
                case '/' :  if (froms.contains('E')) { return null; } else { break; }
                case '-' :  break;
                case '|' : if (froms.contains('N')) { return null; } else { break; }}
                break;
            }
            case 'E': {switch (value) {
                case '.' : if (froms.contains('W')) { return null;} else { break; }
                case '\\' : if (froms.contains('N')) { return null; } else { break; }
                case '/' :  if (froms.contains('S')) { return null; } else { break; }
                case '-' :  break;
                case '|' : if (froms.contains('W')) { return null; } else { break; }}
                break;
            }
            case 'W': {switch (value) {
                case '.' : if (froms.contains('E')) { return null;} else { break; }
                case '\\' : if (froms.contains('S')) { return null; } else { break; }
                case '/' :  if (froms.contains('N')) { return null; } else { break; }
                case '-' :  break;
                case '|' : if (froms.contains('E')) { return null; } else { break; }}
                break;
            }
        }

        if (froms.contains(from) || updatedValue == '2') {
            return null;
        } else {
            froms.add(from);
        }
        if (value == '.') {
            if (updatedValue == '.') {
                switch (from) {
                    case 'N':
                        updatedValue = 'v';
                        break;
                    case 'S':
                        updatedValue = '^';
                        break;
                    case 'W':
                        updatedValue = '>';
                        break;
                    case 'E':
                        updatedValue = '<';
                        break;
                }
            } else if (updatedValue == 'v' ) {
                if (from == 'S') {
                    return null;
                }
                updatedValue = '2';
            } else if (updatedValue == '^' ) {
                if (from == 'N') {
                    return null;
                }
                updatedValue = '2';
            } else if (updatedValue == '>' ) {
                if (from == 'E') {
                    return null;
                }
                updatedValue = '2';
            } else if (updatedValue == '<') {
                if (from == 'W') {
                    return null;
                }
                updatedValue = '2';
            }
            return madeDirection(from);
        }
        switch (from) {
            case 'N':
                switch (value) {
                    case '/':
                        return madeDirection('E');
                    case '\\':
                        return madeDirection('W');
                    case '-': {
                        if (froms.contains('S')) {
                            return null;
                        } else {
                            return madeDirection('E', 'W');
                        }
                    }
                    case '|':
                        return madeDirection('N');
                }
            case 'S':
                switch (value) {
                    case '/':
                        return madeDirection('W');
                    case '\\':
                        return madeDirection('E');
                    case '-': {
                        if (froms.contains('N')) {
                            return null;
                        } else {
                            return madeDirection('E', 'W');
                        }
                    }
                    case '|':
                        return madeDirection('S');
                }
            case 'W':
                switch (value) {
                    case '/':
                        return madeDirection('S');
                    case '\\':
                        return madeDirection('N');
                    case '-':
                        return madeDirection('W');
                    case '|': {
                        if (froms.contains('E')) {
                            return null;
                        } else {
                            return madeDirection('N', 'S');
                        }
                    }
                }
            case 'E':
                switch (value) {
                    case '/':
                        return madeDirection('N');
                    case '\\':
                        return madeDirection('S');
                    case '-':
                        return madeDirection('E');
                    case '|': {
                        if (froms.contains('W')) {
                            return null;
                        } else {
                            return madeDirection('N', 'S');
                        }
                    }
                }
        }
        return null;
    }

    public char[] madeDirection(char one) {
        char[] result = new char[1];
        result[0] = one;
        return result;
    }

    public char[] madeDirection(char one, char two) {
        char[] result = new char[2];
        result[0] = one;
        result[1] = two;
        return result;
    }
}
