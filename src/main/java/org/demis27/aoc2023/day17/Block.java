package org.demis27.aoc2023.day17;

import java.util.Objects;

public class Block {

    int loss;

    int row;

    int column;

    public Block(int loss, int row, int column) {
        this.loss = loss;
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return loss == block.loss && row == block.row && column == block.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(loss, row, column);
    }

    @Override
    public String toString() {
        return "([" + row + "," + column + "]" + loss + ")";
    }
}
