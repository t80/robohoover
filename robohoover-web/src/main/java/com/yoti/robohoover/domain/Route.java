package com.yoti.robohoover.domain;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Route {
    private final ArrayDeque<Coordinate> route;

    public Route(Coordinate startingCoordinate) {
        this.route = new ArrayDeque<>();
        this.route.add(copyOf(startingCoordinate));
    }

    public Coordinate lastCoordinate() {
        return copyOf(route.getLast());
    }

    public Deque<Coordinate> getCoordinates() {
        return route.clone();
    }

    public void add(Coordinate coordinate) {
        route.add(copyOf(coordinate));
    }

    // Finds the intersection of the route and the dirty patches
    public Set<Coordinate> applyTo(List<Coordinate> dirtyPatches) {
        Set<Coordinate> toBeCleanedPatches = new HashSet<>(dirtyPatches);
        toBeCleanedPatches.retainAll(route);

        return toBeCleanedPatches;
    }

    private Coordinate copyOf(Coordinate coordinate) {
        return Coordinate.of(coordinate.getX(), coordinate.getY());
    }


}
