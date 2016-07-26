package com.yoti.robohoover.client;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class RobohooverRequestBuilder {
    private List<Integer> roomSize;
    private List<Integer> startingCoordinate;
    private List<List<Integer>> dirtPatches;
    private String instructions;

    public RobohooverRequestBuilder withRoomSize(int width, int height) {
        if(null==roomSize) {
            roomSize = new ArrayList<>();
        }
        this.roomSize.add(width);
        this.roomSize.add(height);
        return this;
    }

    public RobohooverRequestBuilder withStartingCoordinate(int x, int y) {
        if(null==startingCoordinate) {
            startingCoordinate = new ArrayList<>();
        }
        this.startingCoordinate.add(x);
        this.startingCoordinate.add(y);
        return this;
    }

    public RobohooverRequestBuilder withDirtPatch(int x, int y) {
        if(null==dirtPatches) {
            dirtPatches = new ArrayList<>();
        }
        dirtPatches.add(asList(x,y));
        return this;
    }

    public RobohooverRequestBuilder withInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }

    public RobohooverRequestBuilder withDefaults() {
        this.withRoomSize(10, 10);
        this.withStartingCoordinate(0, 0);
        this.withDirtPatch(3,3);
        this.withDirtPatch(6, 6);
        this.withDirtPatch(9, 9);
        this.withInstructions("NNNEEENNNEEENNNEEE");
        return this;
    }

    public RobohooverRequest build() {
        return new RobohooverRequest(roomSize, startingCoordinate, dirtPatches, instructions);
    }
}