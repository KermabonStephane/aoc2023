package org.demis27.aoc2023.day02;

import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayLine {

    int red = 0;
    int green = 0;
    int blue = 0;

    public static PlayLine readPlayLine(String playLines) {
        PlayLine result = new PlayLine();
        String[] cubes = playLines.split(",");
        for (int i = 0; i < cubes.length; i++) {
            if (cubes[i].indexOf("red") > 0) {
                result.red = Integer.parseInt(cubes[i].replaceAll("[a-zA-Z]", "").trim());
            }
            else if (cubes[i].indexOf("green") > 0) {
                result.green = Integer.parseInt(cubes[i].replaceAll("[a-zA-Z]", "").trim());
            }
            else if (cubes[i].indexOf("blue") > 0) {
                result.blue = Integer.parseInt(cubes[i].replaceAll("[a-zA-Z]", "").trim());
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "PlayLine{" +
                "red=" + red +
                ", blue=" + blue +
                ", green=" + green +
                '}';
    }
}
