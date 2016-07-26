package com.yoti.robohoover.services;

import com.yoti.robohoover.domain.Coordinate;
import com.yoti.robohoover.domain.RobohooverServiceRequest;
import com.yoti.robohoover.domain.RobohooverServiceResponse;
import com.yoti.robohoover.domain.Route;
import com.yoti.robohoover.domain.builders.RobohooverServiceRequestBuilder;
import com.yoti.robohoover.repositories.RobohooverRequestRepository;
import com.yoti.robohoover.repositories.RobohooverResponseRepository;
import org.junit.Test;

import java.util.HashSet;

import static com.yoti.robohoover.testsupport.TestFixtures.robohooverRequestWithInvalidInstructions;
import static java.util.Arrays.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HooverServiceTest {

    private final RouteBuilder routeBuilder = mock(RouteBuilder.class);
    private final RobohooverRequestRepository requestRepository = mock(RobohooverRequestRepository.class);
    private final RobohooverResponseRepository responseRepository = mock(RobohooverResponseRepository.class);
    private final HooverService hooverService = new HooverService(routeBuilder, requestRepository, responseRepository);

    @Test
    public void hooverRoomWhereInstructionsStayInRoomBounds() {
        Route route = mock(Route.class);
        Coordinate lastCoordinate = Coordinate.of(1, 1);
        RobohooverServiceRequest serviceRequest = new RobohooverServiceRequestBuilder().withDefaults().build();
        HashSet<Coordinate> cleanedPatches = new HashSet<>(asList(Coordinate.of(1, 1), Coordinate.of(2, 2)));

        when(route.lastCoordinate()).thenReturn(lastCoordinate);
        when(route.applyTo(anyList())).thenReturn(cleanedPatches);
        when(routeBuilder.routeFrom(serviceRequest.getStartingCoordinate(), serviceRequest.getRoomSize(),serviceRequest.getInstructions()))
                .thenReturn(route);

        RobohooverServiceResponse serviceResponse = hooverService.hooverRoom(serviceRequest);

        verify(requestRepository).save(serviceRequest);
        verify(route).applyTo(serviceRequest.getDirtyCoordinates());
        verify(responseRepository).save(any(RobohooverServiceResponse.class));

        assertThat(serviceResponse.cleanedPatchesCount(), is(cleanedPatches.size()));
        assertThat(serviceResponse.getCoordinate(), is(lastCoordinate));
    }

}