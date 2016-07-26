package com.yoti.robohoover.services;

import com.yoti.robohoover.domain.Coordinate;
import com.yoti.robohoover.domain.RobohooverServiceRequest;
import com.yoti.robohoover.domain.RobohooverServiceResponse;
import com.yoti.robohoover.domain.Route;
import com.yoti.robohoover.repositories.RobohooverRequestRepository;
import com.yoti.robohoover.repositories.RobohooverResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HooverService {

    private final RouteBuilder routeBuilder;
    private final RobohooverRequestRepository requestRepository;
    private final RobohooverResponseRepository responseRepository;

    @Autowired
    public HooverService(RouteBuilder routeBuilder, RobohooverRequestRepository requestRepository, RobohooverResponseRepository responseRepository) {
        this.routeBuilder = routeBuilder;
        this.requestRepository = requestRepository;
        this.responseRepository = responseRepository;
    }

    public RobohooverServiceResponse hooverRoom(RobohooverServiceRequest serviceRequest) {
        requestRepository.save(serviceRequest);

        Route route = routeBuilder.routeFrom(
                serviceRequest.getStartingCoordinate(),
                serviceRequest.getRoomSize(),
                serviceRequest.getInstructions());

        Set<Coordinate> cleanedPatches = route.applyTo(serviceRequest.getDirtyCoordinates());

        RobohooverServiceResponse serviceResponse = new RobohooverServiceResponse(route.lastCoordinate(), cleanedPatches.size());
        responseRepository.save(serviceResponse);

        return serviceResponse;
    }
}
