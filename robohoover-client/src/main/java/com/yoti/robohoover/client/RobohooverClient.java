package com.yoti.robohoover.client;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.yoti.robohoover.client.transport.CoordinateListSerializer;
import com.yoti.robohoover.client.transport.CoordinateSerializer;
import com.yoti.robohoover.client.transport.RoomDeserializer;
import com.yoti.robohoover.client.transport.RoomSerializer;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RobohooverClient {
    private final ObjectMapper mapper = new ObjectMapper();
    private final URL url;
    private final TestRestTemplate restTemplate;

    public RobohooverClient(String host, int port) throws MalformedURLException {
        url = new URL("http://" + host + ":" + port + "/robohoover/clean");
        restTemplate = new TestRestTemplate();
    }


    public RoomHooverResponse requestRoomHoovering(RoomHooverRequest hooverRequest) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonBody = mapper.writeValueAsString(hooverRequest);

        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
        ResponseEntity<RoomHooverResponse> response = restTemplate.exchange(
                url.toString(),
                HttpMethod.POST,
                entity,
                RoomHooverResponse.class);

        return response.getBody();
    }
}
