package com.yoti.robohoover.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RoomHooverRequestBuilder {
    private RoomSize roomSize;
    private Coordinate startingCoordinate;
    private Set<Coordinate> dirtPatches = new HashSet<>();
    private String instructions;

    public RoomHooverRequestBuilder withRoomSize(int width, int height) {
        this.roomSize = new RoomSize(width, height);
        return this;
    }

    public RoomHooverRequestBuilder withStartingCoordinate(int x, int y) {
        this.startingCoordinate = new Coordinate(x,y);
        return this;
    }

    public RoomHooverRequestBuilder withDirtPatch(int x, int y) {
        dirtPatches.add(new Coordinate(x,y));
        return this;
    }

    public RoomHooverRequestBuilder withInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }

    public RoomHooverRequestBuilder withDefaults() {
        this.roomSize = new RoomSize(10, 10);
        this.startingCoordinate = new Coordinate(0,0);
        this.dirtPatches.add(new Coordinate(3,3));
        this.dirtPatches.add(new Coordinate(6,6));
        this.dirtPatches.add(new Coordinate(9,9));
        this.instructions = "NNNEEENNNEEENNNEEE";
        return this;
    }

    public RoomHooverRequest build() {
        return new RoomHooverRequest(roomSize, startingCoordinate, new ArrayList<>(dirtPatches), instructions);
    }
}