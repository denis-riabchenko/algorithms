package com.epam.algorithms.coursera.test5;

import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dijkstra {
    public Map<Integer, Path> findShortestPaths(int from, Collection<Arc> arcs) {
        Map<Integer, Path> pathMap = new HashMap<>();
        pathMap.put(from, new Path(from));

        arcs = new HashSet<>(arcs);

        Set<Integer> consumedVertices = Sets.newHashSet(from);
        Set<Arc> outgoingArcs = findOutgoingArcs(arcs, consumedVertices);
        while (!outgoingArcs.isEmpty()) {
            for (Arc arc : outgoingArcs) {
                Path pathToParent = pathMap.get(arc.from());
                Path path = pathMap.get(arc.to());
                if (path == null || path.length() > pathToParent.length() + arc.weight()) {
                    path = pathToParent.newPath(arc);
                    pathMap.put(arc.to(), path);
                }
                consumedVertices.add(arc.to());
            }
            arcs.removeAll(outgoingArcs);
            outgoingArcs = findOutgoingArcs(arcs, consumedVertices);
        }

        return pathMap;
    }

    private Set<Arc> findOutgoingArcs(Collection<Arc> arcs, Set<Integer> consumedVertices) {
        Set<Arc> outgoingArcs = new HashSet<>();

        arcs.stream()
                .filter(arc -> consumedVertices.contains(arc.from()))
                .forEach(outgoingArcs::add);

        return outgoingArcs;
    }
}
