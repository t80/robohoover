package com.yoti.robohoover.controllers.adapters;

import com.yoti.robohoover.client.RobohooverRequest;
import com.yoti.robohoover.client.RobohooverResponse;
import com.yoti.robohoover.domain.Coordinate;
import com.yoti.robohoover.domain.Direction;
import com.yoti.robohoover.domain.RobohooverServiceRequest;
import com.yoti.robohoover.domain.RobohooverServiceResponse;
import com.yoti.robohoover.domain.RoomSize;
import com.yoti.robohoover.domain.builders.RobohooverServiceRequestBuilder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.toList;

@Service
public class HooverServiceAdapter {

    private RobohooverServiceRequestBuilder serviceRequestBuilder;

    public HooverServiceAdapter() {
        this.serviceRequestBuilder = new RobohooverServiceRequestBuilder();
    }

    public RobohooverServiceRequest adapt(RobohooverRequest robohooverRequest) {
        RobohooverServiceRequest serviceRequest = serviceRequestBuilder
                .withRoomSize(RoomSize.of(robohooverRequest.getRoomSize().get(0), robohooverRequest.getRoomSize().get(1)))
                .withStartingCoordinate(Coordinate.of(robohooverRequest.getCoords().get(0), robohooverRequest.getCoords().get(1)))
                .withDirtyCoordinates(extractDirtyPatch(robohooverRequest.getPatches()))
                .withInstructions(adaptInstructionsFrom(robohooverRequest.getInstructions()))
                .build();

        return serviceRequest;
    }

    public RobohooverResponse adapt(RobohooverServiceResponse serviceResponse) {
        List<Integer> endPosition = asList(serviceResponse.getCoordinate().getX(), serviceResponse.getCoordinate().getY());
        return new RobohooverResponse(endPosition, serviceResponse.cleanedPatchesCount());
    }

    public List<Coordinate> extractDirtyPatch(List<List<Integer>> coords) {
        return coords.stream()
                .map((coord) -> Coordinate.of(coord.get(0), coord.get(1)))
                .collect(toList());
    }

    private List<Direction> adaptInstructionsFrom(String instructions) {
        return instructions
                .chars()
                .mapToObj(c -> Direction.from((char)c))
                .collect(toList());
    }
}
