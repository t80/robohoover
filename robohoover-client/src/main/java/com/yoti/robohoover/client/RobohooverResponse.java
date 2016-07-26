package com.yoti.robohoover.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class RobohooverResponse {
    private final List<Integer> coords;
    private final Integer patches;

    @JsonCreator
    public RobohooverResponse(
            @JsonProperty("coords") List<Integer> endPosition,
            @JsonProperty("patches") Integer cleanedDirtPatches) {
        this.coords = endPosition;
        this.patches = cleanedDirtPatches;
    }

    public List<Integer> getCoords() {
        return coords;
    }

    public Integer getPatches() {
        return patches;
    }
}
