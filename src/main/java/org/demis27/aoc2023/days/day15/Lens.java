package org.demis27.aoc2023.days.day15;

public class Lens {

    public String type;
    public int focal;

    public int position;

    public Lens(String s) {
        String[] split = s.split("=|-");
        type = split[0];
        if (split.length > 1) {
            focal = Integer.parseInt(split[1]);
        }
    }

    @Override
    public String toString() {
        return "[" + type + " " + focal +"] (" + position + " ) ";
    }
}
