package org.demis27.aoc2023.day15;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Box {


    public Map<String, Lens> map = new HashMap();

    public void addLens(Lens l) {
        if (map.containsKey(l.type)) {
            Lens lens = map.get(l.type);
            l.position = lens.position;
            map.replace(l.type, l);
        }
        else {
            l.position = map.size();
            map.put(l.type, l);
        }
    }

    public void removeLens(Lens l) {
        if (map.containsKey(l.type)) {
            Lens lens = map.get(l.type);
            int position = lens.position;
            map.values().stream().filter(l2 -> l2.position > position).forEach(l2 -> l2.position--);

            map.remove(l.type);
        }
    }

    public List<Lens> getLenses() {
        return map.values().stream().sorted(Comparator.comparingInt(l -> l.position)).toList();
    }

    public String toString(int position) {
        StringBuffer buffer = new StringBuffer("Box " + position + ": ");
        List<Lens> lenses = getLenses();
        lenses.stream().forEach(l -> buffer.append(l.toString()));

        return buffer.toString() ;
    }
}
