package org.demis27.aoc2023.day18;

public class Wall implements Comparable<Wall> {

    // simple true, double false
    // if simple we change the
    boolean simple;

    long pos;

    long size;

    @Override
    public int compareTo(Wall o) {
        if (o.pos == pos) {
            return 0;
        }
        return o.pos > pos ? -1 : 1;
    }
}
