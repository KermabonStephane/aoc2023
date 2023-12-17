package org.demis27.aoc2023.day17;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Way {

    long totalLoss = 0;

    List<Block> way = new ArrayList<>();

    Direction[] lastDirections = new Direction[3];

    Block[][] blocks ;

    Block current;

    List<Block> already = new ArrayList<>();

    public Way(Block[][] blocks, Block current, List<Block> already) {
        // copy blocks
        this.blocks = new Block[blocks.length][blocks.length];
        for (int row = 0; row < blocks.length; row++) {
            for (int column = 0; column < blocks.length; column++) {
                this.blocks[row][column] = blocks[row][column];
            }
        }
        this.current = current;
        this.already.addAll(already);
        this.already.add(current);
        totalLoss = already.stream().map(b -> b.loss).collect(Collectors.summarizingLong(l -> l)).getSum();
    }


    public Direction getLastDirection() {
        if (lastDirections[2] != null) {
            return lastDirections[2];
        }
        else if (lastDirections[1] != null) {
            return lastDirections[1];
        }
        else if (lastDirections[0] != null) {
            return lastDirections[0];
        }
        else {
            return null;
        }
    }

    public Way update(Direction direction) throws IllegalAccessException {
        if (lastDirections[0] == null) {
            lastDirections[0] = direction;
        }
        else if (lastDirections[1] == null) {
             lastDirections[1] = direction;
        }
        else if (lastDirections[2] == null) {
            lastDirections[2] = direction;
        }
        else {
            throw new IllegalAccessException();
        }
        return this;
    }

    public void changeDirection(Direction direction) {
        lastDirections[0] = direction;
        lastDirections[1] = null;
        lastDirections[2] = null;
    }
}
