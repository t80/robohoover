package com.yoti.robohoover.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class RoomHooverResponse {

    private final Coordinate endPosition;
    private final int cleanedDirtPatches;

    @JsonCreator
    public RoomHooverResponse(
            @JsonProperty("coords") Coordinate endPosition,
            @JsonProperty("patches") int cleanedDirtPatches) {
        this.endPosition = endPosition;
        this.cleanedDirtPatches = cleanedDirtPatches;
    }

    public Coordinate getEndPosition() {
        return endPosition;
    }

    public int getCleanedDirtPatches() {
        return cleanedDirtPatches;
    }
}
