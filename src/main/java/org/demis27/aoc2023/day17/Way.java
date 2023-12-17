package org.demis27.aoc2023.day17;

import java.util.ArrayList;
import java.util.List;

public class Way {

    long totalLoss = 0;

    List<Block> blocks = new ArrayList<>();

    Direction[] lastDirections = new Direction[3];

    public Direction getLastDirection() {
        if (lastDirections[2] != null) {
            return lastDirections[2];
        } else if (lastDirections[1] != null) {
            return lastDirections[1];
        } else if (lastDirections[0] != null) {
            return lastDirections[0];
        } else {
            return null;
        }
    }

    public Way addDirection(Direction direction) {
        if (direction.equals(getLastDirection())) {
            if (lastDirections[0] == null) {
                lastDirections[0] = direction;
            } else if (lastDirections[1] == null) {
                lastDirections[1] = direction;
            } else if (lastDirections[2] == null) {
                lastDirections[2] = direction;
            } else {
                System.out.print("strange add direction");
            }
        } else {
            lastDirections[0] = direction;
            lastDirections[1] = null;
            lastDirections[2] = null;
        }
        return this;
    }

    public void changeDirection(Direction direction) {
        lastDirections[0] = direction;
        lastDirections[1] = null;
        lastDirections[2] = null;
    }

    public Block getLastBlock() {
        return blocks.get(blocks.size() - 1);
    }

    public Way clone() {
        Way way = new Way();
        way.blocks.addAll(this.blocks);
        way.lastDirections[0] = this.lastDirections[0];
        way.lastDirections[1] = this.lastDirections[1];
        way.lastDirections[2] = this.lastDirections[2];
        way.totalLoss = totalLoss;
        return way;
    }

    public boolean directionsIfFull() {
        return lastDirections[2] != null;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(totalLoss + " ");
        for (int i = 0; i < blocks.size(); i++) {
            buffer.append(blocks.get(i));
        }
        return buffer.toString();
    }
}
