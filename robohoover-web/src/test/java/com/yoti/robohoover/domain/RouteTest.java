package com.yoti.robohoover.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RouteTest {

    private final Route route = new Route(Coordinate.of(0, 0));

    @Before
    public void setUp() throws Exception {
        route.add(Coordinate.of(0, 1));
        route.add(Coordinate.of(0, 2));
        route.add(Coordinate.of(0, 3));
        route.add(Coordinate.of(0, 4));
        route.add(Coordinate.of(0, 5));
        route.add(Coordinate.of(0, 6));
        route.add(Coordinate.of(0, 6));
        route.add(Coordinate.of(0, 7));
        route.add(Coordinate.of(0, 8));
        route.add(Coordinate.of(0, 9));
    }

    @Test
    public void returnsIntersectionOfRouteAndSetOfCoordinates() {
        Set<Coordinate> intersection = route.applyTo(asList(Coordinate.of(0, 5)));

        assertThat(intersection.size(), is(1));
        assertTrue(intersection.contains(Coordinate.of(0, 5)));
    }

    @Test
    public void returnsEmptySetWhenNoCoordinates() {
        Set<Coordinate> intersection = route.applyTo(Collections.emptyList());

        assertThat(intersection.size(), is(0));
    }

    @Test
    public void returnsSetOfAllCoordinatesWhenTheyFullyIntersect() {
        List<Coordinate> coordinates = asList(
                Coordinate.of(0, 1),
                Coordinate.of(0, 2),
                Coordinate.of(0, 3),
                Coordinate.of(0, 4),
                Coordinate.of(0, 5),
                Coordinate.of(0, 6),
                Coordinate.of(0, 6),
                Coordinate.of(0, 7),
                Coordinate.of(0, 8),
                Coordinate.of(0, 9));

        Set<Coordinate> intersection = route.applyTo(coordinates);

        assertThat(intersection.size(), is(9));
        assertTrue(intersection.contains(Coordinate.of(0, 1)));
        assertTrue(intersection.contains(Coordinate.of(0, 2)));
        assertTrue(intersection.contains(Coordinate.of(0, 3)));
        assertTrue(intersection.contains(Coordinate.of(0, 4)));
        assertTrue(intersection.contains(Coordinate.of(0, 5)));
        assertTrue(intersection.contains(Coordinate.of(0, 6)));
        assertTrue(intersection.contains(Coordinate.of(0, 7)));
        assertTrue(intersection.contains(Coordinate.of(0, 8)));
        assertTrue(intersection.contains(Coordinate.of(0, 9)));
    }

}