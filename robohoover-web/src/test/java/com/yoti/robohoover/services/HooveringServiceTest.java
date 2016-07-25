package com.yoti.robohoover.services;

import com.yoti.robohoover.client.RoomHooverResponse;
import org.junit.Test;

import static com.yoti.robohoover.testsupport.TestFixtures.hooveringRequestWithInvalidDirections;
import static com.yoti.robohoover.testsupport.TestFixtures.validHooveringRequest;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class HooveringServiceTest {

    private final RouteBuilder routeBuilder = mock(RouteBuilder.class);
    private final HooveringService hooveringService = new HooveringService(routeBuilder);

    @Test
    public void hooverRoomWhereInstructionsStayInRoomBounds() {
        RoomHooverResponse roomHooverResponse = hooveringService.hooverRoom(validHooveringRequest());

        assertThat(roomHooverResponse.getCleanedDirtPatches(), is(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIllegalArgumentExceptionOnUnknownDirection() {
        hooveringService.hooverRoom(hooveringRequestWithInvalidDirections());
    }
}