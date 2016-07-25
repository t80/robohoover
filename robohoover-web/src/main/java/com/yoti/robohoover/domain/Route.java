package com.yoti.robohoover.domain;

import com.yoti.robohoover.client.Coordinate;

import java.util.ArrayDeque;
import java.util.Deque;

public final class Route {

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

    private Coordinate copyOf(Coordinate coordinate) {
        return new Coordinate(coordinate.getX(), coordinate.getY());
    }

    public static void main(String[] args) {
        Coordinate exposed = new Coordinate(1, 2);
        Route route = new Route(exposed);
        Coordinate coordinate = route.getCoordinates().peekFirst();
        System.out.println();
    }
}
