package com.yoti.robohoover.testsupport;

import com.yoti.robohoover.client.RobohooverRequest;
import com.yoti.robohoover.client.RobohooverRequestBuilder;

public class TestFixtures {

    public static RobohooverRequest validServiceRequest() {
        return new RobohooverRequestBuilder()
                .withDefaults()
                .build();
    }

    public static RobohooverRequest robohooverRequestWithInvalidInstructions() {
        return new RobohooverRequestBuilder()
                .withDefaults()
                .withInstructions("EWSNX")
                .build();
    }
}
