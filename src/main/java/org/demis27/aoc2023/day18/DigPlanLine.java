package org.demis27.aoc2023.day18;

import java.util.HexFormat;

public class DigPlanLine {

    public char direction;

    public long number;

    public String color;

    public void useColorAsNumber() {
        number = HexFormat.fromHexDigitsToLong(color.substring(2, 7));
        direction = switch (color.charAt(7)) {
            case '0' -> 'R';
            case '1' -> 'D';
            case '2' -> 'L';
            case '3' -> 'U';
            default -> '*';
        };
    }
}
