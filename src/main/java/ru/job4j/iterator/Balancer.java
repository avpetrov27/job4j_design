package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int nodesSize = nodes.size();
        int counter = 0;
        while (source.hasNext()) {
            nodes.get(counter++ % nodesSize).add(source.next());
        }
    }
}
