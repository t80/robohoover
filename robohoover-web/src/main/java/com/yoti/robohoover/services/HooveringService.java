package com.yoti.robohoover.services;

import com.yoti.robohoover.client.Coordinate;
import com.yoti.robohoover.client.RoomHooverRequest;
import com.yoti.robohoover.client.RoomHooverResponse;
import com.yoti.robohoover.domain.Direction;
import com.yoti.robohoover.domain.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
public class HooveringService {

    private final RouteBuilder routeBuilder;

    @Autowired
    public HooveringService(RouteBuilder routeBuilder) {
        this.routeBuilder = routeBuilder;
    }

    public RoomHooverResponse hooverRoom(RoomHooverRequest roomHooverRequest) {
        List<Direction> instructions = adaptInstructionsFrom(roomHooverRequest);

        Route route = routeBuilder.routeFrom(
                roomHooverRequest.getStartingCoordinate(),
                roomHooverRequest.getRoomSize(),
                instructions);

        Set<Coordinate> cleanedPatches = patchesCleaned(roomHooverRequest.getDirtPatches(), route);

        return new RoomHooverResponse(route.lastCoordinate(), cleanedPatches.size());
    }

    private List<Direction> adaptInstructionsFrom(RoomHooverRequest roomHooverRequest) {
        return roomHooverRequest.getInstructions()
                .chars()
                .mapToObj(c -> Direction.from((char)c))
                .collect(toList());
    }

    private Set<Coordinate> patchesCleaned(List<Coordinate> dirtyPatches, Route route) {
        Set<Coordinate> dirtPatches = new HashSet<>(dirtyPatches);
        Set<Coordinate> cleanedPatches = new HashSet<>(dirtPatches);
        cleanedPatches.retainAll(route.getCoordinates());

        return cleanedPatches;
    }
}
