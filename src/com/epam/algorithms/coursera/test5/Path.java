package com.epam.algorithms.coursera.test5;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Path {
    private final int from;
    private int to;
    private int length;
    private Set<Arc> arcs = new LinkedHashSet<>();

    public Path(final int from) {
        this.from = from;
        this.to = from;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public int length() {
        return length;
    }

    public Set<Arc> arcs() {
        return Collections.unmodifiableSet(arcs);
    }

    public Path newPath(Arc arc) {
        Path path = null;
        if (arc != null && arc.from() == to && !arcs.contains(arc)) {
            path = new Path(from);
            path.arcs.addAll(arcs);
            path.arcs.add(arc);
            path.to = arc.to();
            path.length = length + arc.weight();
        }
        return path;
    }

    @Override
    public String toString() {
        return String.format("%d-%d: %d", from, to, length);
    }
}
