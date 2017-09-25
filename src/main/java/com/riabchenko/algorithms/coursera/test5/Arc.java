package com.riabchenko.algorithms.coursera.test5;

import java.util.Collection;

public class Arc {
    private final int from;
    private final int to;
    private final int weight;

    private Arc(final int from, final String arc) {
        this.from = from;
        String[] values = arc.split(",");
        this.to = Integer.parseInt(values[0]);
        this.weight = Integer.parseInt(values[1]);
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public int weight() {
        return weight;
    }

    public static int readArcs(String line, Collection<Arc> arcs) {
        int count = 0;
        String[] values = line.split("\t");
        if (values.length > 1) {
            int from = Integer.parseInt(values[0]);
            for (int i = 1; i < values.length; i ++) {
                arcs.add(new Arc(from, values[i]));
            }
            count = values.length - 1;
        }
        return count;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Arc arc = (Arc) o;

        if (from != arc.from)
            return false;
        if (to != arc.to)
            return false;
        if (weight != arc.weight)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = from;
        result = 31 * result + to;
        result = 31 * result + weight;
        return result;
    }

    @Override
    public String toString() {
        return String.format("%d-%d: %d", from, to, weight);
    }
}
