package org.demis27.aoc2023.day06;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Race {

    private long time;

    private long record;

    public Race(long time) {
        this.time = time;
    }

//    private long preparationTime;

    public long numerbOfPossibilities() {
        long result = 0;
        for (int i = 1; i < time; i++) {
            if ((i * (time - i)) > record) {
                result++;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Race{" +
                "time=" + time +
                ", record=" + record +
//                ", preparationTime=" + preparationTime +
                '}';
    }
}
