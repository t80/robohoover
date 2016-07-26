package com.yoti.robohoover.services;

import com.yoti.robohoover.domain.Coordinate;
import com.yoti.robohoover.domain.RoomSize;
import com.yoti.robohoover.domain.Route;
import org.junit.Test;

import java.util.List;

import static com.yoti.robohoover.domain.Direction.*;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RouteBuilderTest {

    private final RouteBuilder routeBuilder = new RouteBuilder();
    private final RoomSize roomSize = new RoomSize(2, 2);
    private final Coordinate startingCoordinate = Coordinate.of(0, 0);

    @Test
    public void buildsCorrectRouteWhereHooverDoesntHitRoomWalls() {
        Route route = routeBuilder.routeFrom(
                Coordinate.of(0, 0),
                roomSize,
                asList(NORTH, EAST, SOUTH, WEST));

        List<Coordinate> coordinates = route.getCoordinates().stream().collect(toList());

        assertThat(coordinates.get(0), is(Coordinate.of(0,0)));
        assertThat(coordinates.get(1), is(Coordinate.of(0,1)));
        assertThat(coordinates.get(2), is(Coordinate.of(1,1)));
        assertThat(coordinates.get(3), is(Coordinate.of(1,0)));
        assertThat(coordinates.get(4), is(Coordinate.of(0,0)));
        assertThat(coordinates.size(), is(5));
    }

    @Test
    public void buildsCorrectRouteWhereHooverHitsNorthernWall() {
        Route route = routeBuilder.routeFrom(
                startingCoordinate,
                roomSize,
                asList(NORTH, NORTH, NORTH, EAST));

        List<Coordinate> coordinates = route.getCoordinates().stream().collect(toList());

        assertThat(coordinates.get(0), is(Coordinate.of(0,0)));
        assertThat(coordinates.get(1), is(Coordinate.of(0,1)));
        assertThat(coordinates.get(2), is(Coordinate.of(1,1)));
        assertThat(coordinates.size(), is(3));
    }

    @Test
    public void buildsCorrectRouteWhereHooverHitsEasternWall() {
        Route route = routeBuilder.routeFrom(
                startingCoordinate,
                roomSize,
                asList(EAST, EAST, EAST, NORTH));

        List<Coordinate> coordinates = route.getCoordinates().stream().collect(toList());

        assertThat(coordinates.get(0), is(Coordinate.of(0,0)));
        assertThat(coordinates.get(1), is(Coordinate.of(1,0)));
        assertThat(coordinates.get(2), is(Coordinate.of(1,1)));
        assertThat(coordinates.size(), is(3));
    }

    @Test
    public void buildsCorrectRouteWhereHooverHitsSouthernWall() {
        Route route = routeBuilder.routeFrom(
                startingCoordinate,
                roomSize,
                asList(SOUTH, SOUTH, EAST));

        List<Coordinate> coordinates = route.getCoordinates().stream().collect(toList());

        assertThat(coordinates.get(0), is(Coordinate.of(0,0)));
        assertThat(coordinates.get(1), is(Coordinate.of(1,0)));
        assertThat(coordinates.size(), is(2));
    }

    @Test
    public void buildsCorrectRouteWhereHooverHitsWesternWall() {
        Route route = routeBuilder.routeFrom(
                startingCoordinate,
                roomSize,
                asList(EAST, WEST, WEST));

        List<Coordinate> coordinates = route.getCoordinates().stream().collect(toList());

        assertThat(coordinates.get(0), is(Coordinate.of(0,0)));
        assertThat(coordinates.get(1), is(Coordinate.of(1,0)));
        assertThat(coordinates.get(2), is(Coordinate.of(0,0)));
        assertThat(coordinates.size(), is(3));
    }
}