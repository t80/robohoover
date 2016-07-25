package com.yoti.robohoover.testsupport;

import com.yoti.robohoover.client.RoomHooverRequest;
import com.yoti.robohoover.client.RoomHooverRequestBuilder;

public class TestFixtures {

    public static RoomHooverRequest validHooveringRequest() {
        return new RoomHooverRequestBuilder()
                .withDefaults()
                .build();
    }

    public static RoomHooverRequest hooveringRequestWithInvalidDirections() {
        return new RoomHooverRequestBuilder()
                .withDefaults()
                .withInstructions("EWSNX")
                .build();
    }

}
