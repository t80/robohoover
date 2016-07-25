package com.yoti.robohoover.integration;

import com.yoti.robohoover.Application;
import com.yoti.robohoover.client.Coordinate;
import com.yoti.robohoover.client.RobohooverClient;
import com.yoti.robohoover.client.RoomHooverRequestBuilder;
import com.yoti.robohoover.client.RoomHooverResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class RobohooverIT {

    @Value("${local.server.port}")
    private int port;
    private RobohooverClient robohooverClient;
    private RoomHooverRequestBuilder hooverRequest = new RoomHooverRequestBuilder();

    @Before
    public void setUp() throws Exception {
        // TODO: initialise once for test class
        robohooverClient = new RobohooverClient("localhost", port);
    }

    @Test
    public void hooversRoom() throws Exception {
        RoomHooverResponse response = robohooverClient.requestRoomHoovering(
                hooverRequest
                        .withDirtPatch(1,1)
                        .withRoomSize(2,2)
                        .withStartingCoordinate(0,0)
                        .withInstructions("NESW")
                        .build());

        assertThat(response.getCleanedDirtPatches(), is(1));
        assertThat(response.getEndPosition(), is(Coordinate.of(0,0)));
    }
}
