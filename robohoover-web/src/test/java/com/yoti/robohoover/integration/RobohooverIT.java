package com.yoti.robohoover.integration;

import com.yoti.robohoover.Application;
import com.yoti.robohoover.client.RobohooverClient;
import com.yoti.robohoover.client.RobohooverResponse;
import com.yoti.robohoover.client.RobohooverRequestBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static java.util.Arrays.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.springframework.http.HttpStatus.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class RobohooverIT {

    @Value("${local.server.port}")
    private int port;
    private RobohooverClient robohooverClient;
    private RobohooverRequestBuilder hooverRequest = new RobohooverRequestBuilder();

    @Before
    public void setUp() throws Exception {
        // TODO: initialise once for test class
        robohooverClient = new RobohooverClient("localhost", port);
    }

    @Test
    public void returnsCorrectRepsonseOnSuccessfulHoover() throws Exception {
        List<Integer> expectedEndPosition = asList(0, 0);

        ResponseEntity<RobohooverResponse> response = robohooverClient.requestRoomHoovering(
                hooverRequest
                        .withDirtPatch(1, 1)
                        .withRoomSize(2, 2)
                        .withStartingCoordinate(0, 0)
                        .withInstructions("NESW")
                        .build());

        assertThat(response.getBody().getPatches(), is(1));
        assertThat(response.getBody().getCoords(), is(expectedEndPosition));
    }

    @Test(expected = HttpClientErrorException.class)
    public void returns400OnNullRoomSize() throws Exception {
        try {
            robohooverClient.requestRoomHoovering(
                    hooverRequest.withDirtPatch(1, 1)
                            .withStartingCoordinate(0, 0)
                            .withInstructions("NESW")
                            .build());
        } catch (HttpClientErrorException e) {
            assertThat(e.getStatusCode(), is(BAD_REQUEST));
            throw e;
        }
    }

    @Test(expected = HttpClientErrorException.class)
    public void returns400OnNullCoords() throws Exception {
        try {
            robohooverClient.requestRoomHoovering(
                    hooverRequest
                            .withRoomSize(2, 2)
                            .withDirtPatch(1, 1)
                            .withInstructions("NESW")
                            .build());
        } catch (HttpClientErrorException e) {
            assertThat(e.getStatusCode(), is(BAD_REQUEST));
            throw e;
        }
    }

    @Test(expected = HttpClientErrorException.class)
    public void returns400OnNullPatches() throws Exception {
        try {
            robohooverClient.requestRoomHoovering(
                    hooverRequest
                            .withRoomSize(2, 2)
                            .withStartingCoordinate(0, 0)
                            .withInstructions("NESW")
                            .build());
        } catch (HttpClientErrorException e) {
            assertThat(e.getStatusCode(), is(BAD_REQUEST));
            throw e;
        }
    }

    @Test(expected = HttpClientErrorException.class)
    public void returns400OnInvalidDirections() throws Exception {
        try {
            robohooverClient.requestRoomHoovering(
                    hooverRequest
                            .withDirtPatch(1, 1)
                            .withRoomSize(2, 2)
                            .withStartingCoordinate(0, 0)
                            .withInstructions("NESWX")
                            .build());
        } catch (HttpClientErrorException e) {
            assertThat(e.getStatusCode(), is(BAD_REQUEST));
            throw e;
        }
    }
}
