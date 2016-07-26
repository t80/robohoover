package com.yoti.robohoover.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity(name = "hoover_request")
public class RobohooverServiceRequest {
    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private RoomSize roomSize;
    @Embedded
    private Coordinate startingCoordinate;
    @ElementCollection
    private List<Coordinate> dirtyCoordinates;
    @ElementCollection
    private List<Direction> instructions;

    public RobohooverServiceRequest() {
    }

    public RobohooverServiceRequest(RoomSize roomSize, Coordinate startingCoordinate, List<Coordinate> dirtyCoordinates, List<Direction> instructions) {
        this.roomSize = roomSize;
        this.startingCoordinate = startingCoordinate;
        this.dirtyCoordinates = dirtyCoordinates;
        this.instructions = instructions;
    }

    public RoomSize getRoomSize() {
        return roomSize;
    }

    public Coordinate getStartingCoordinate() {
        return startingCoordinate;
    }

    public List<Coordinate> getDirtyCoordinates() {
        return dirtyCoordinates;
    }

    public List<Direction> getInstructions() {
        return instructions;
    }
}
