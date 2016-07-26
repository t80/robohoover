package com.yoti.robohoover.services;

import com.yoti.robohoover.domain.Coordinate;
import com.yoti.robohoover.domain.Direction;
import com.yoti.robohoover.domain.RoomSize;
import com.yoti.robohoover.domain.Route;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteBuilder {

    public static final int GRID_JUMP = 1;

    public Route routeFrom(Coordinate startingCoordinate, RoomSize roomSize, List<Direction> instructions) {
        Route route = new Route(startingCoordinate);
        instructions.stream().forEach((direction) -> appendNextCoordinate(direction, roomSize, route));

        return route;
    }

    private void appendNextCoordinate(Direction direction, RoomSize roomSize, Route route) {
        switch (direction) {
            case NORTH:
                int nextNortherlyCoord = route.lastCoordinate().getY() + GRID_JUMP;
                if(nextNortherlyCoord < roomSize.getHeight()) {
                    route.add(Coordinate.of(route.lastCoordinate().getX(), nextNortherlyCoord));
                }
                break;
            case EAST:
                int nextEasterlyCoord = route.lastCoordinate().getX() + GRID_JUMP;
                if(nextEasterlyCoord < roomSize.getWidth()) {
                    route.add(Coordinate.of(nextEasterlyCoord, route.lastCoordinate().getY()));
                }
                break;
            case SOUTH:
                int nextSoutherlyCoord = route.lastCoordinate().getY() - GRID_JUMP;
                if(nextSoutherlyCoord >= 0) {
                    route.add(Coordinate.of(route.lastCoordinate().getX(), nextSoutherlyCoord));
                }
                break;
            case WEST:
                int nextWesterlyCoord = route.lastCoordinate().getX() - GRID_JUMP;
                if(nextWesterlyCoord >=0) {
                    route.add(Coordinate.of(nextWesterlyCoord, route.lastCoordinate().getY()));
                }
                break;
            default: throw new IllegalArgumentException("Unknown direction");
        }
    }
}
