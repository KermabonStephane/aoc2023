package org.demis27.aoc2023.days.day19;


public class Part {

    public Data x;
    public Data m;
    public Data a;
    public Data s;

    //{x=787,m=2655,a=1222,s=2876}
    public Part(String line) {
        String[] split = line.substring(1, line.length() - 1).split(",");
        x = new Data(DataType.X, Integer.parseInt(split[0].substring(2)));
        m = new Data(DataType.M, Integer.parseInt(split[1].substring(2)));
        a = new Data(DataType.A, Integer.parseInt(split[2].substring(2)));
        s = new Data(DataType.S, Integer.parseInt(split[3].substring(2)));
    }

    public Data getData(DataType type) {
        return switch (type) {
            case X ->  x;
            case M ->  m;
            case A ->  a;
            case S ->  s;
        };
    }
}
