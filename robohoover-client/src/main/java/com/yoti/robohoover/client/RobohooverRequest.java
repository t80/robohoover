package com.yoti.robohoover.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RobohooverRequest {

    @NotNull @Size(min = 2, max = 2)
    private final List<Integer> roomSize;
    @NotNull @Size(min = 2, max = 2)
    private final List<Integer> coords;
    @NotNull
    private final List<List<Integer>> patches; // TODO: custom validator for nested array
    @NotNull
    private final String instructions;

    @JsonCreator
    public RobohooverRequest(
            @JsonProperty("roomSize") List<Integer> roomSize,
            @JsonProperty("coords") List<Integer> startingCoordinate,
            @JsonProperty("patches") List<List<Integer>> dirtPatches,
            @JsonProperty("instructions") String instructions) {
        this.roomSize = roomSize;
        this.coords = startingCoordinate;
        this.patches = dirtPatches;
        this.instructions = instructions;
    }

    public List<Integer> getRoomSize() {
        return roomSize;
    }

    public List<Integer> getCoords() {
        return coords;
    }

    public List<List<Integer>> getPatches() {
        return patches;
    }
    public String getInstructions() {
        return instructions;
    }
}
