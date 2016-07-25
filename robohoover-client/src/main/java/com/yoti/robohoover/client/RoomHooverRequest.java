package com.yoti.robohoover.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yoti.robohoover.client.transport.CoordinateDeserializer;
import com.yoti.robohoover.client.transport.CoordinateListDeserializer;
import com.yoti.robohoover.client.transport.CoordinateListSerializer;
import com.yoti.robohoover.client.transport.CoordinateSerializer;
import com.yoti.robohoover.client.transport.RoomDeserializer;
import com.yoti.robohoover.client.transport.RoomSerializer;

import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomHooverRequest {

    private final RoomSize roomSize;
    private final Coordinate coords;
    private final List<Coordinate> patches;
    private final String instructions;

    @JsonCreator
    public RoomHooverRequest(
            @JsonProperty("roomSize") @JsonDeserialize(using = RoomDeserializer.class)
            RoomSize roomSize,
            @JsonProperty("coords") @JsonDeserialize(using = CoordinateDeserializer.class)
            Coordinate startingCoordinate,
            @JsonProperty("patches") @JsonDeserialize(using = CoordinateListDeserializer.class)
            List<Coordinate> dirtPatches,
            @JsonProperty("instructions") String instructions) {
        this.roomSize = roomSize;
        this.coords = startingCoordinate;
        this.patches = dirtPatches;
        this.instructions = instructions;
    }

    @JsonProperty("roomSize")
    @JsonSerialize(using = RoomSerializer.class)
    @NotNull(message = "RoomSize cannot be null")
    public RoomSize getRoomSize() {
        return roomSize;
    }

    @JsonProperty("coords")
    @JsonSerialize(using = CoordinateSerializer.class)
    @NotNull(message = "coords cannot be null")
    public Coordinate getStartingCoordinate() {
        return coords;
    }

    @JsonProperty("patches")
    @JsonSerialize(using = CoordinateListSerializer.class)
    @NotNull(message = "patches cannot be null")
    public List<Coordinate> getDirtPatches() {
        return patches;
    }
    @JsonProperty("instructions")
    @NotNull(message = "instructions cannot be null")
    public String getInstructions() {
        return instructions;
    }
}
