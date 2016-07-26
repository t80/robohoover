package com.yoti.robohoover.controllers.serviceadapters;

import com.yoti.robohoover.client.RobohooverRequest;
import com.yoti.robohoover.client.RobohooverResponse;
import com.yoti.robohoover.controllers.adapters.HooverServiceAdapter;
import com.yoti.robohoover.domain.Coordinate;
import com.yoti.robohoover.domain.RobohooverServiceRequest;
import com.yoti.robohoover.domain.RobohooverServiceResponse;
import org.junit.Test;

import static com.yoti.robohoover.testsupport.TestFixtures.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class HooverServiceAdapterTest {

    private final HooverServiceAdapter hooverServiceAdapter = new HooverServiceAdapter();

    @Test
    public void adaptsRequestToServiceRequest() throws Exception {
        RobohooverRequest robohooverRequest = validServiceRequest();

        RobohooverServiceRequest serviceRequest = hooverServiceAdapter.adapt(robohooverRequest);

        assertThat(serviceRequest.getStartingCoordinate().getX(), is(robohooverRequest.getCoords().get(0)));
        assertThat(serviceRequest.getStartingCoordinate().getY(), is(robohooverRequest.getCoords().get(1)));
        assertThat(serviceRequest.getRoomSize().getWidth(), is(robohooverRequest.getRoomSize().get(0)));
        assertThat(serviceRequest.getRoomSize().getHeight(), is(robohooverRequest.getRoomSize().get(1)));
        assertThat(serviceRequest.getInstructions().size(), is(robohooverRequest.getInstructions().length()));
        for (int i = 0; i < robohooverRequest.getPatches().size(); i++) {
            assertThat(robohooverRequest.getPatches().get(i).get(0), is(serviceRequest.getDirtyCoordinates().get(i).getX()));
            assertThat(robohooverRequest.getPatches().get(i).get(1), is(serviceRequest.getDirtyCoordinates().get(i).getY()));

        }
    }

    @Test
    public void adaptsResponse() throws Exception {
        RobohooverServiceResponse serviceResponse = new RobohooverServiceResponse(Coordinate.of(1,1), 2);

        RobohooverResponse robohooverResponse = hooverServiceAdapter.adapt(serviceResponse);

        assertThat(robohooverResponse.getCoords().get(0), is(serviceResponse.getCoordinate().getX()));
        assertThat(robohooverResponse.getCoords().get(1), is(serviceResponse.getCoordinate().getY()));
        assertThat(robohooverResponse.getPatches(), is(serviceResponse.cleanedPatchesCount()));
    }
}