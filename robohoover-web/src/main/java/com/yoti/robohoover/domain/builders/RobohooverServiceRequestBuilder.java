package com.yoti.robohoover.domain.builders;

import com.yoti.robohoover.domain.Coordinate;
import com.yoti.robohoover.domain.Direction;
import com.yoti.robohoover.domain.RobohooverServiceRequest;
import com.yoti.robohoover.domain.RoomSize;

import java.util.Arrays;
import java.util.List;

import static com.yoti.robohoover.domain.Direction.*;
import static java.util.Arrays.*;

public class RobohooverServiceRequestBuilder {
    private RoomSize roomSize;
    private Coordinate startingCoordinate;
    private List<Coordinate> dirtyCoordinates;
    private List<Direction> instructions;

    public RobohooverServiceRequestBuilder withRoomSize(RoomSize roomSize) {
        this.roomSize = roomSize;
        return this;
    }

    public RobohooverServiceRequestBuilder withStartingCoordinate(Coordinate startingCoordinate) {
        this.startingCoordinate = startingCoordinate;
        return this;
    }

    public RobohooverServiceRequestBuilder withDirtyCoordinates(List<Coordinate> dirtyCoordinates) {
        this.dirtyCoordinates = dirtyCoordinates;
        return this;
    }

    public RobohooverServiceRequestBuilder withInstructions(List<Direction> instructions) {
        this.instructions = instructions;
        return this;
    }

    public RobohooverServiceRequest build() {
        return new RobohooverServiceRequest(roomSize, startingCoordinate, dirtyCoordinates, instructions);
    }

    public RobohooverServiceRequestBuilder withDefaults() {
        withRoomSize(RoomSize.of(2,2));
        withStartingCoordinate(Coordinate.of(0,0));
        withDirtyCoordinates(asList(Coordinate.of(1,1)));
        withInstructions(asList(NORTH, EAST, SOUTH, WEST));
        return this;
    }

}