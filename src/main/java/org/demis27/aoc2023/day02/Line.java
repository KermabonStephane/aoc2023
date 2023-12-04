package org.demis27.aoc2023.day02;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Line {

    int id;
    boolean valid;
    List<PlayLine> playLines = new ArrayList<>();

    @Override
    public String toString() {
        return "Line{" +
                "id=" + id +
                ", valid=" + valid +
                ", playLines=" + playLines +
                '}';
    }

    public boolean isValid(int red, int green, int blue) {
        valid = playLines.stream().allMatch(play -> play.red <= red && play.blue <= blue && play.green <= green) ;
        return valid;
    }

    public PlayLine minimum() {
        PlayLine result = new PlayLine();

        playLines.stream().forEach(play -> {
            result.red = Math.max(result.red, play.red);
            result.green = Math.max(result.green, play.green);
            result.blue = Math.max(result.blue, play.blue);
        });


        return result;
    }

    public static Line readLine(String line) {
        String[] split = line.split("[:;]");
        Line result = new Line();

        result.id = Integer.parseInt(split[0].replaceAll("[a-zA-Z]", "").trim());
        for (int i = 1; i < split.length; i++) {
            result.playLines.add(PlayLine.readPlayLine(split[i]));
        }

        return result;
    }
}
